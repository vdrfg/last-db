package db.last.webapp.services;

import db.last.webapp.dtos.ArtistInDTO;
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

    public Artist save(ArtistInDTO artistInDTO) {
        Artist artist = new Artist(artistInDTO);
        return artistRepository.save(artist);
    }

    public Artist getArtistById(String id) {
        return artistRepository.getReferenceById(id);
    }

    public Optional<Artist> getArtistByName(String name) {
        return artistRepository.findByName(name);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }
}
