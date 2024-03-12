package db.last.webapp.services;

import db.last.webapp.dtos.SongDTO;
import db.last.webapp.models.Album;
import db.last.webapp.models.Song;
import db.last.webapp.models.SongNumber;
import db.last.webapp.repositories.SongNumberRepository;
import db.last.webapp.repositories.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SongService {

  private SongRepository songRepository;
  private SongNumberRepository songNumberRepository;

  public void createSongs(Album album, List<SongDTO> songDTOs) {
    List<Song> existingSongs = getSongsByArtistAndAlbum(album);
    List<Song> songs = new ArrayList<>();
    List<SongNumber> songNumbers = new ArrayList<>();

    songDTOs.forEach(
        dto ->
            existingSongs.stream()
                .filter(existingSong -> existingSong.getName().equals(dto.songName()))
                .findFirst()
                .ifPresentOrElse(
                    existingSong -> {
                      songs.add(existingSong);
                      SongNumber number = new SongNumber(album, existingSong, dto.songNumber());
                      songNumbers.add(number);
                    },
                    () -> {
                      Song song = new Song(album, dto);
                      SongNumber number = new SongNumber(album, song, dto.songNumber());
                      songs.add(song);
                      songNumbers.add(number);
                    }));

    songRepository.saveAll(songs);
    songNumberRepository.saveAll(songNumbers);
  }

  public List<Song> getSongsByArtistAndAlbum(Album album) {
    return songRepository.findAllByArtistAndAlbumsContaining(album.getArtist(), album);
  }
}
