package db.last.webapp.services;

import db.last.webapp.dtos.SongDTO;
import db.last.webapp.models.Album;
import db.last.webapp.models.Artist;
import db.last.webapp.models.Song;
import db.last.webapp.repositories.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SongService {

  private SongRepository songRepository;

  public Song save(Song song) {
    return songRepository.save(song);
  }

  public Optional<Song> getSongByArtistAndSongName(Artist artist, String name) {
    return songRepository.findByArtistAndName(artist, name);
  }

  public List<Song> createSongs(Album album, List<SongDTO> songDTOs) {
    List<Song> songs = songDTOs.stream().map(dto -> new Song(album, dto)).toList();
    return songRepository.saveAll(songs);
  }
}
