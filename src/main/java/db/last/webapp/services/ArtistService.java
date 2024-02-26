package db.last.webapp.services;

import db.last.webapp.models.Artist;
import db.last.webapp.repositories.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ArtistService {

    private ArtistRepository artistRepository;

    public Artist save(Artist artist) {
        return artistRepository.save(artist);
    }

    public Optional<Artist> getArtist(String name) {
        return artistRepository.findByName(name);
    }

    public boolean existsByName(String name) {
        return artistRepository.existsByName(name);
    }
}
