package db.last.webapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "songNumbers")
public class SongNumber {

  @EmbeddedId @NotNull private SongNumberId id;

  @NotBlank private Integer number;

  @ManyToOne
  @MapsId("albumId")
  private Album album;

  @ManyToOne
  @MapsId("songId")
  private Song song;

  public SongNumber(Album album, Song song, int number) {
    this.id = new SongNumberId(album, song);
    this.number = number;
  }
}
