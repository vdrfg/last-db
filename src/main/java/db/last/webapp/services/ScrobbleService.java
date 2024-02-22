package db.last.webapp.services;

import db.last.webapp.models.Scrobble;
import db.last.webapp.repositories.ScrobbleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScrobbleService {

    private ScrobbleRepository scrobbleRepository;

    public Scrobble save(Scrobble scrobble) {
        return scrobbleRepository.save(scrobble);
    }

    public List<Scrobble> saveBatch(List<Scrobble> scrobbles) {
        return scrobbleRepository.saveAll(scrobbles);
    }
}
