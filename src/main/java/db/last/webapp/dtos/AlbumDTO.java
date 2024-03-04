package db.last.webapp.dtos;

import db.last.webapp.models.Artist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record AlbumDTO(
        @NotBlank @NotNull Artist artist, @NotBlank @NotNull String name, LocalDate releaseDate) {}
