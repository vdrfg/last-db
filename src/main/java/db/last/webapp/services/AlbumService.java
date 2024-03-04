package db.last.webapp.services;

import db.last.webapp.dtos.AlbumDTO;
import db.last.webapp.dtos.SongDTO;
import db.last.webapp.models.Album;
import db.last.webapp.models.Artist;
import db.last.webapp.models.Song;
import db.last.webapp.models.SongNumber;
import db.last.webapp.repositories.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlbumService {

  private AlbumRepository albumRepository;
  private SongService songService;

  public void create(AlbumDTO albumDTO, List<SongDTO> songDTOs) throws Exception {
    Optional<Album> optAlbum = getAlbumByArtistAndName(albumDTO.artist(), albumDTO.name());
    if (optAlbum.isPresent()) {
      throw new Exception("something");
      //TODO: create custom exceptions
    }

    Album album = albumRepository.save(new Album(albumDTO));

    songService.createSongs(album, songDTOs);
    //TODO: add and save song numbers

  }

  public Optional<Album> getAlbumByArtistAndName(Artist artist, String name) {
    return albumRepository.findByArtistAndName(artist, name);
  }
}
