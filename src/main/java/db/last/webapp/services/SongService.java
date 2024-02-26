package db.last.webapp.services;

import db.last.webapp.models.Artist;
import db.last.webapp.models.Song;
import db.last.webapp.repositories.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SongService {

    private SongRepository songRepository;

    public Song save(Song song) {
        return songRepository.save(song);
    }

    public Optional<Song> getSongByArtistAndSongName(Artist artist, String name) {
        return songRepository.findByArtistAndName(artist, name);
    }
}
