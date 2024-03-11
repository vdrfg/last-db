package db.last.webapp.dtos;

import db.last.webapp.models.Artist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AlbumDTO(
        @NotBlank @NotNull String artistId, @NotBlank @NotNull String name, LocalDate releaseDate) {}
