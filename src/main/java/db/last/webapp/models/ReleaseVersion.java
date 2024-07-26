package db.last.webapp.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
public class ReleaseVersion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@DateTimeFormat(pattern = "yyyy-MMM-dd")
	private LocalDate releaseDate;

	private boolean isPrimary;

	@ManyToOne
	private Release release;

	@OneToMany(mappedBy = "releaseVersion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Track> tracklist = new ArrayList<>();
}
