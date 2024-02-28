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
@Table(name = "songs")
public class Song {
  @Id
  @GeneratedValue(generator = "songPrefixGenerator", strategy = GenerationType.SEQUENCE)
  @GenericGenerator(
      name = "songPrefixGenerator",
      type = IdPrefixGenerator.class,
      parameters = {
        @Parameter(name = IdPrefixGenerator.INCREMENT_PARAM, value = "1"),
        @Parameter(name = IdPrefixGenerator.PREFIX_VALUE_PARAMETER, value = "Song")
      })
  //TODO: prefix ID by Artist
  private String id;

  private String name;

  @ManyToMany(mappedBy = "songs")
  @Builder.Default
  private List<Album> albums = new ArrayList<>();

  @ManyToOne private Artist artist;
}
