package db.last.webapp.dtos;

import db.last.webapp.models.Artist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ArtistOutDTO(
    @NotNull String id,
    @NotBlank @NotNull String name,
    @NotBlank @NotNull String description,
    String country,
    LocalDate startDate,
    LocalDate endDate) {

    public ArtistOutDTO(Artist artist) {
        this(
                artist.getId(),
                artist.getName(),
                artist.getDescription(),
                artist.getCountry(),
                artist.getStartDate(),
                artist.getEndDate());
    }
}
