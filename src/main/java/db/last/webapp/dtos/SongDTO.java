package db.last.webapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SongDTO(@NotNull @NotBlank String songName, @NotBlank int songNumber) {}
