package db.last.webapp.repositories;

import db.last.webapp.models.SongNumber;
import db.last.webapp.models.SongNumberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongNumberRepository extends JpaRepository<SongNumber, SongNumberId> {}
