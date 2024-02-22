package db.last.webapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Scrobble {
    private String artistName;
    private String albumName;
    private String songName;
    @Id
    private Timestamp scrobbledAt;

    public Scrobble(String artistName, String albumName, String songName, Timestamp scrobbledAt) {
        this.artistName = artistName;
        this.albumName = albumName;
        this.songName = songName;
        this.scrobbledAt = scrobbledAt;
    }
}
