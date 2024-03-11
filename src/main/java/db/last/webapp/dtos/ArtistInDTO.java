package db.last.webapp.dtos;

import db.last.webapp.models.Artist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ArtistInDTO(
    @NotBlank @NotNull String name,
    @NotBlank @NotNull String description,
    String country,
    LocalDate startDate,
    LocalDate endDate) {

  public ArtistInDTO(
      String name, String description, String country, LocalDate startDate, LocalDate endDate) {
    this.name = name;
    this.description = description;
    this.country = country;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
