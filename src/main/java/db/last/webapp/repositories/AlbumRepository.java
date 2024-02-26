package db.last.webapp.repositories;

import db.last.webapp.models.Album;
import db.last.webapp.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findByArtistAndName(Artist artist, String name);
}
