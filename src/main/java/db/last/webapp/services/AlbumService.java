package db.last.webapp.services;

import db.last.webapp.dtos.AlbumDTO;
import db.last.webapp.models.Album;
import db.last.webapp.models.Artist;
import db.last.webapp.repositories.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlbumService {

  private AlbumRepository albumRepository;

  public Album create(Artist artist, AlbumDTO albumDTO) throws Exception {
    Optional<Album> optAlbum = getAlbumByArtistNameAndAlbumName(artist.getName(), albumDTO.name());
    if (optAlbum.isPresent()) {
      throw new Exception("something");
      // TODO: create custom exceptions
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate releaseDate = LocalDate.parse(albumDTO.releaseDate(), formatter);

    return albumRepository.save(new Album(artist, albumDTO, releaseDate));
  }

  public Optional<Album> getAlbumByArtistNameAndAlbumName(String artistName, String albumName) {
    return albumRepository.findByArtist_NameAndName(artistName, albumName);
  }

  public List<AlbumDTO> getAllAlbumDTOsByArtist(Artist artist) {
    return albumRepository.findAllByArtist(artist).stream()
        .map(AlbumDTO::new)
        .collect(Collectors.toList());
  }
}
