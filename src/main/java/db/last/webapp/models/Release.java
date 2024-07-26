package db.last.webapp.models;

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
public class Release {
	@Id
	@NotNull
	@GeneratedValue(generator = "albumPrefixGenerator", strategy = GenerationType.SEQUENCE)
	@GenericGenerator(
			name = "albumPrefixGenerator",
			parameters = {
					@Parameter(name = "sequence_name", value = "album_seq"),
					@Parameter(name = "initial_value", value = "1"),
					@Parameter(name = "increment_size", value = "1"),
					@Parameter(name = "optimizer", value = "none"),
					@Parameter(name = IdPrefixGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = IdPrefixGenerator.PREFIX_VALUE_PARAMETER, value = "Album")
			})
	private String id;

	@Enumerated(EnumType.STRING)
	private ReleaseType type;

	@NotNull @NotBlank private String name;

	@DateTimeFormat(pattern = "yyyy-MMM-dd")
	private LocalDate releaseDate;

	@NotNull @ManyToMany private List<Artist> artist = new ArrayList<>();

	@OneToMany
	private List<Track> tracklist = new ArrayList<>();
}