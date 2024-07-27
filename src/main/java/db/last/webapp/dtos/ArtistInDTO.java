package db.last.webapp.dtos;

import java.time.LocalDate;

public record ArtistInDTO(String firstName, String lastName, LocalDate beginDate, LocalDate endDate,
						  String description) {
}