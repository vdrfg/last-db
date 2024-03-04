package db.last.webapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ArtistDTO(
    @NotBlank @NotNull String name,
    @NotBlank @NotNull String description,
    String country,
    LocalDate startDate,
    LocalDate endDate) {}
