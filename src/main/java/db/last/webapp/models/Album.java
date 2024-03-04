package db.last.webapp.models;

import db.last.webapp.dtos.AlbumDTO;
import db.last.webapp.models.idGenerator.IdPrefixGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
  @NotNull
  @GeneratedValue(generator = "albumPrefixGenerator", strategy = GenerationType.SEQUENCE)
  @GenericGenerator(
      name = "albumPrefixGenerator",
      type = IdPrefixGenerator.class,
      parameters = {
        @Parameter(name = IdPrefixGenerator.INCREMENT_PARAM, value = "1"),
        @Parameter(name = IdPrefixGenerator.PREFIX_VALUE_PARAMETER, value = "Album")
      })
  private String id;

  @NotNull @ManyToOne private Artist artist;

  @NotNull @NotBlank private String name;

  @DateTimeFormat(pattern = "dd-MMM-yyyy")
  private LocalDate releaseDate;

  @ManyToMany
  @Builder.Default
  @JoinTable(
      name = "album_songs",
      joinColumns = @JoinColumn(name = "album_id"),
      inverseJoinColumns = @JoinColumn(name = "song_id"))
  private List<Song> songs = new ArrayList<>();

  public Album(AlbumDTO albumDTO) {
    this.artist = albumDTO.artist();
    this.name = albumDTO.name();
    this.releaseDate = albumDTO.releaseDate();
  }
}
