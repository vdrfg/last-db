package db.last.webapp.models;

import db.last.webapp.dtos.SongDTO;
import db.last.webapp.models.idGenerator.IdPrefixGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
  @NotNull
  @GeneratedValue(generator = "songPrefixGenerator", strategy = GenerationType.SEQUENCE)
  @GenericGenerator(
      name = "songPrefixGenerator",
      type = IdPrefixGenerator.class,
      parameters = {
        @Parameter(name = IdPrefixGenerator.INCREMENT_PARAM, value = "1"),
        @Parameter(name = IdPrefixGenerator.PREFIX_VALUE_PARAMETER, value = "Song")
      })
  // TODO: prefix ID by Artist
  private String id;

  @NotNull @NotBlank private String name;

  @ManyToMany(mappedBy = "songs")
  @Builder.Default
  private List<Album> albums = new ArrayList<>();

  @OneToMany(mappedBy = "song")
  @Builder.Default
  private List<SongNumber> songNumbers = new ArrayList<>();

  @NotNull @ManyToOne private Artist artist;

  public Song(Album album, SongDTO songDTO) {
    this.name = songDTO.name();
    this.albums.add(album);
    this.artist = album.getArtist();
  }
}
