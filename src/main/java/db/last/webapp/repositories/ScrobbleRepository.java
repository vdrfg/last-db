package db.last.webapp.repositories;

import db.last.webapp.models.Scrobble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface ScrobbleRepository extends JpaRepository<Scrobble, Timestamp> {}