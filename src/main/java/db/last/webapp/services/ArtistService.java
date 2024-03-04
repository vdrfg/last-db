package db.last.webapp.services;

import db.last.webapp.dtos.ArtistDTO;
import db.last.webapp.models.Artist;
import db.last.webapp.repositories.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArtistService {

    private ArtistRepository artistRepository;

    public Artist save(ArtistDTO artistDTO) {
        Artist artist = new Artist(artistDTO);
        return artistRepository.save(artist);
    }

    public Optional<Artist> getArtist(String name) {
        return artistRepository.findByName(name);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public boolean existsByName(String name) {
        return artistRepository.existsByName(name);
    }
}
