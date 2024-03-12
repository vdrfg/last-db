package db.last.webapp.dtos;

import db.last.webapp.models.Album;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlbumDTO(
    @NotBlank @NotNull String artistId, @NotBlank @NotNull String name, String releaseDate) {

  public AlbumDTO(Album album) {
    this(
        album.getArtist().getId(),
        album.getName(),
        album.getReleaseDate() == null ? "" : album.getReleaseDate().toString());
  }
}
