package db.last.webapp.models;

import db.last.webapp.models.idGenerator.IdPrefixGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "albums")
public class Album {
  @Id
  @GeneratedValue(generator = "albumPrefixGenerator", strategy = GenerationType.SEQUENCE)
  @GenericGenerator(
      name = "albumPrefixGenerator",
      type = IdPrefixGenerator.class,
      parameters = {
        @Parameter(name = IdPrefixGenerator.INCREMENT_PARAM, value = "1"),
        @Parameter(name = IdPrefixGenerator.PREFIX_VALUE_PARAMETER, value = "Album")
      })
  private String id;

  private String name;
  @ManyToOne private Artist artist;
  @ManyToMany
  @Builder.Default
  @JoinTable(
          name = "album_songs",
          joinColumns = @JoinColumn(name = "album_id"),
          inverseJoinColumns = @JoinColumn(name = "song_id"))
  private List<Song> songs = new ArrayList<>();
}
