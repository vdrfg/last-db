package db.last.webapp.services;

import db.last.webapp.models.Album;
import db.last.webapp.models.Artist;
import db.last.webapp.repositories.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AlbumService {

    private AlbumRepository albumRepository;

    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public Optional<Album> getAlbumByArtistAndName(Artist artist, String name) {
        return albumRepository.findByArtistAndName(artist, name);
    }
}
