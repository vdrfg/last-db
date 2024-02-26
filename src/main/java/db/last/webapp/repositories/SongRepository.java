package db.last.webapp.repositories;

import db.last.webapp.models.Artist;
import db.last.webapp.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findByArtistAndName(Artist artist, String name);
}
