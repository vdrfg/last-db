package db.last.webapp.dtos;

import java.sql.Timestamp;

public record ScrobbleDTO(String artistName, String albumName, String songName, Timestamp scrobbledAt) {
}
