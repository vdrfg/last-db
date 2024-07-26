package db.last.webapp.models;

import java.util.ArrayList;
import java.util.List;

import db.last.webapp.models.idGenerator.IdPrefixGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
			parameters = {
					@Parameter(name = "sequence_name", value = "song_seq"),
					@Parameter(name = "initial_value", value = "1"),
					@Parameter(name = "increment_size", value = "1"),
					@Parameter(name = "optimizer", value = "none"),
					@Parameter(name = IdPrefixGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = IdPrefixGenerator.PREFIX_VALUE_PARAMETER, value = "Song")
			})
	private String id;

	@NotNull
	@NotBlank
	private String name;

	@ManyToMany
	private List<Artist> artists = new ArrayList<>();
}