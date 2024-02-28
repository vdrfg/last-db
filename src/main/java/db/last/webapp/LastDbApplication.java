package db.last.webapp;

import db.last.webapp.models.Album;
import db.last.webapp.models.Artist;
import db.last.webapp.models.Song;
import db.last.webapp.repositories.AlbumRepository;
import db.last.webapp.repositories.ArtistRepository;
import db.last.webapp.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;

@SpringBootApplication
@EnableCaching
public class LastDbApplication implements CommandLineRunner {
  public LastDbApplication(
      ArtistRepository artistRepository,
      AlbumRepository albumRepository,
      SongRepository songRepository) {
    this.artistRepository = artistRepository;
    this.albumRepository = albumRepository;
    this.songRepository = songRepository;
  }

  private ArtistRepository artistRepository;
  private AlbumRepository albumRepository;
  private SongRepository songRepository;

  public static void main(String[] args) {
    SpringApplication.run(LastDbApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Artist korn = Artist.builder().name("Korn").build();
    Artist slipknot = Artist.builder().name("Slipknot").build();

    artistRepository.saveAll(List.of(korn, slipknot));

    Album followTheLeader = Album.builder().name("Follow the Leader").artist(korn).build();
    Album iowa = Album.builder().name("Iowa").artist(slipknot).build();

    albumRepository.saveAll(List.of(followTheLeader, iowa));

    Song gotTheLife =
        Song.builder().name("Got the Life").artist(korn).albums(List.of(followTheLeader)).build();
    Song seed = Song.builder().name("Seed").artist(korn).albums(List.of(followTheLeader)).build();

    Song disasterpiece =
        Song.builder().name("Disasterpiece").artist(slipknot).albums(List.of(iowa)).build();
    Song leftBehind =
        Song.builder().name("Left Behind").artist(slipknot).albums(List.of(iowa)).build();

    songRepository.saveAll(List.of(gotTheLife, seed, disasterpiece, leftBehind));
  }
}
