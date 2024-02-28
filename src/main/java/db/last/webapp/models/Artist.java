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
@Table(name = "artists")
public class Artist {

  @Id
  @GeneratedValue(generator = "artistPrefixGenerator", strategy = GenerationType.SEQUENCE)
  @GenericGenerator(
      name = "artistPrefixGenerator",
      type = IdPrefixGenerator.class,
      parameters = {
        @Parameter(name = IdPrefixGenerator.INCREMENT_PARAM, value = "1"),
        @Parameter(name = IdPrefixGenerator.PREFIX_VALUE_PARAMETER, value = "Artist")
      })
  private String id;

  private String name;

  @OneToMany(mappedBy = "artist")
  @Builder.Default
  private List<Album> albums = new ArrayList<>();

  @OneToMany(mappedBy = "artist")
  @Builder.Default
  private List<Song> songs = new ArrayList<>();
}
