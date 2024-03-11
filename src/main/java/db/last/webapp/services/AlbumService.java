package db.last.webapp.services;

import db.last.webapp.dtos.AlbumDTO;
import db.last.webapp.dtos.SongDTO;
import db.last.webapp.models.Album;
import db.last.webapp.models.Artist;
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

  public void create(Artist artist, AlbumDTO albumDTO, List<SongDTO> songDTOs) throws Exception {
    Optional<Album> optAlbum = getAlbumByArtistAndName(artist.getName(), albumDTO.name());
    if (optAlbum.isPresent()) {
      throw new Exception("something");
      //TODO: create custom exceptions
    }

    Album album = albumRepository.save(new Album(artist, albumDTO));
    songService.createSongs(album, songDTOs);
  }

  public Optional<Album> getAlbumByArtistAndName(String artistName, String albumName) {
    return albumRepository.findByArtist_NameAndName(artistName, albumName);
  }
}
