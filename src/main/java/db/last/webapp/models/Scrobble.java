package db.last.webapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Scrobble {

    @Id
    private Timestamp scrobbledAt;
    @ManyToOne
    private Song song;

    public Scrobble(Song song, Timestamp scrobbledAt) {
        this.song = song;
        this.scrobbledAt = scrobbledAt;
    }
}
