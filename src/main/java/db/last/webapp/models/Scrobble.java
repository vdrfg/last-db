package db.last.webapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class Scrobble {
    private String artistName;
    private String albumName;
    private String songName;
    private Timestamp scrobbledAt;
}
