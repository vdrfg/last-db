package db.last.webapp.models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Track {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private int trackNumber;

	private long duration; // to be in milliseconds

	@ManyToOne
	private Song song;

	@ManyToOne
	private Release release;
}
