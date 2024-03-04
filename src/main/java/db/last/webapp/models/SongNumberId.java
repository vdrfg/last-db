package db.last.webapp.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class SongNumberId implements Serializable {

    private String albumId;
    private String songId;

    public SongNumberId(Album album, Song song) {
        albumId = album.getId();
        songId = song.getId();
    }
}
