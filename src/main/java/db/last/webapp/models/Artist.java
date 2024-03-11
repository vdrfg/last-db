package db.last.webapp.models;

import db.last.webapp.dtos.ArtistInDTO;
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
@Table(name = "artists")
public class Artist {

  @Id
  @NotNull
  @GeneratedValue(generator = "artistPrefixGenerator", strategy = GenerationType.SEQUENCE)
  @GenericGenerator(
      name = "artistPrefixGenerator",
      type = IdPrefixGenerator.class,
      parameters = {
        @Parameter(name = IdPrefixGenerator.INCREMENT_PARAM, value = "1"),
        @Parameter(name = IdPrefixGenerator.PREFIX_VALUE_PARAMETER, value = "Artist")
      })
  private String id;

  @NotNull @NotBlank private String name;

  @NotNull @NotBlank private String description;

  private String country;

  @DateTimeFormat(pattern = "dd-MMM-yyyy")
  private LocalDate startDate;

  @DateTimeFormat(pattern = "dd-MMM-yyyy")
  private LocalDate endDate;

  @OneToMany(mappedBy = "artist")
  @Builder.Default
  private List<Album> albums = new ArrayList<>();

  @OneToMany(mappedBy = "artist")
  @Builder.Default
  private List<Song> songs = new ArrayList<>();

  public Artist(ArtistInDTO artistInDTO) {
    this.name = artistInDTO.name();
    this.description = artistInDTO.description();
    this.country = artistInDTO.country();
    this.startDate = artistInDTO.startDate();
    this.endDate = artistInDTO.endDate();
  }
}
