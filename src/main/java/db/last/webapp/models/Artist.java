package db.last.webapp.models;

import java.util.ArrayList;
import java.util.List;

import db.last.webapp.models.idGenerator.IdPrefixGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


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
			parameters = {
					@Parameter(name = "sequence_name", value = "artist_seq"),
					@Parameter(name = "initial_value", value = "1"),
					@Parameter(name = "increment_size", value = "1"),
					@Parameter(name = "optimizer", value = "none"),
					@Parameter(name = IdPrefixGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = IdPrefixGenerator.PREFIX_VALUE_PARAMETER, value = "Artist")
			})
	private String id;

	@NonNull @NotBlank
	private String name;

	@ElementCollection // TODO: change once albums exist
	private List<String> albums = new ArrayList<>();

	@ElementCollection // TODO: change once songs exist
	private List<String> songs = new ArrayList<>();
}