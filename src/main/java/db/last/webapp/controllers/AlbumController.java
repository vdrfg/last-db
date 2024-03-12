package db.last.webapp.controllers;

import db.last.webapp.dtos.AlbumDTO;
import db.last.webapp.dtos.ArtistOutDTO;
import db.last.webapp.dtos.SongDTO;
import db.last.webapp.models.Album;
import db.last.webapp.models.Artist;
import db.last.webapp.services.AlbumService;
import db.last.webapp.services.ArtistService;
import db.last.webapp.services.SongService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/artists/albums/{artistName}")
public class AlbumController {

  private AlbumService albumService;
  private ArtistService artistService;
  private SongService songService;

  @GetMapping("/{albumName}")
  public String album(
      @PathVariable String artistName, @PathVariable String albumName, Model model) {
    Optional<Album> album = albumService.getAlbumByArtistNameAndAlbumName(artistName, albumName);
    if (album.isEmpty()) {
      return "error";
    }

    model.addAttribute("album", album.get()); // TODO: change for DTO
    return "/album/page";
  }

  @GetMapping("/new")
  public String create(@PathVariable String artistName, Model model) {
    Optional<Artist> artist = artistService.getArtistByName(artistName);
    if (artist.isPresent()) {
      model.addAttribute("artist", new ArtistOutDTO(artist.get()));
      model.addAttribute("album", new Album());
    }
    return "/album/form";
  }

  @PostMapping("/new")
  public String create(@Valid AlbumDTO albumDTO, List<@Valid SongDTO> songDTOs) {
    Artist artist = artistService.getArtistById(albumDTO.artistId());
    try {
      Album album = albumService.create(artist, albumDTO);
      songService.createSongs(album, songDTOs);
    } catch (Exception e) {
      // TODO: add error page
      return "error";
    }
    return String.format("redirect/artists/album/%s/%s", artist.getName(), albumDTO.name());
  }

  @GetMapping("/{albumName}/edit")
  private String edit(
      @PathVariable String artistName, @PathVariable String albumName, Model model) {
    Optional<Album> album = albumService.getAlbumByArtistNameAndAlbumName(artistName, albumName);
    if (album.isEmpty()) {
      return "error";
    }

    model.addAttribute("album", album.get());
    return "/album/form";
  }

  @PutMapping("/{albumName}")
  private String edit(
      @PathVariable String artistName,
      @PathVariable String albumName,
      AlbumDTO albumDTO,
      List<SongDTO> songDTOs) {
      // TODO
    return null;
  }
}
