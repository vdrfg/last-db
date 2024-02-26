package db.last.webapp.services;

import db.last.webapp.dtos.ScrobbleDTO;
import db.last.webapp.models.Album;
import db.last.webapp.models.Artist;
import db.last.webapp.models.Scrobble;
import db.last.webapp.models.Song;
import db.last.webapp.repositories.ArtistRepository;
import db.last.webapp.repositories.ScrobbleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScrobbleService {

    private ScrobbleRepository scrobbleRepository;
    private ArtistService artistService;
    private AlbumService albumService;
    private SongService songService;
    private final ArtistRepository artistRepository;

    public Scrobble save(Scrobble scrobble) {
        return scrobbleRepository.save(scrobble);
    }

    public void saveBatch(List<ScrobbleDTO> scrobbleDTOs) {
        List<Scrobble> scrobbles = scrobbleDTOs.stream()
                .map(scrobbleDTO -> new Scrobble(getExistingOrNewSong(scrobbleDTO.artistName(), scrobbleDTO.albumName(), scrobbleDTO.songName()), scrobbleDTO.scrobbledAt()))
                .collect(Collectors.toList());
        scrobbleRepository.saveAll(scrobbles);
    }


    private Song getExistingOrNewSong(String artistName, String albumName, String songName) {
        //TODO: method is missing checks for empty or n/a names

        Optional<Artist> optArtist = artistService.getArtist(artistName);
        if (optArtist.isPresent()) {
            Optional<Song> optSong = songService.getSongByArtistAndSongName(optArtist.get(), songName);
            if (optSong.isPresent()) {
                Optional<Album> optAlbum = albumService.getAlbumByArtistAndName(optArtist.get(), albumName);
                if (optAlbum.isPresent()) {
                    optSong.get().getAlbums().add(optAlbum.get());
                } else {
                    optSong.get().getAlbums().add(new Album(optArtist.get(), albumName));
                }
            }
        }
        Artist newArtist = new Artist(artistName);
        Album newAlbum = new Album(newArtist, albumName);
        return Song.builder().artist(newArtist).albums(List.of(newAlbum)).name(songName).build();
    }
}
