package db.last.webapp.repositories;

import db.last.webapp.models.Scrobble;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface ScrobbleRepository extends JpaRepository<Scrobble, Timestamp> {}
