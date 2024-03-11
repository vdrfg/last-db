package db.last.webapp.controllers;

import db.last.webapp.dtos.AlbumDTO;
import db.last.webapp.dtos.SongDTO;
import db.last.webapp.models.Album;
import db.last.webapp.models.Artist;
import db.last.webapp.services.AlbumService;
import db.last.webapp.services.ArtistService;
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

  @GetMapping("/{albumName}")
  public String album(
      @PathVariable String artistName, @PathVariable String albumName, Model model) {
    Optional<Album> optAlbum = albumService.getAlbumByArtistAndName(artistName, albumName);
    if (optAlbum.isEmpty()) {
      return "error";
    }

    model.addAttribute("album", optAlbum.get());
    return "/album/page";
  }

  @GetMapping("/new")
  public String create(@PathVariable String artistName, Model model) {
    Optional<Artist> optArtist = artistService.getArtist(artistName);
    if (optArtist.isPresent()) {
      model.addAttribute("artist", optArtist.get());
      model.addAttribute("album", new Album());
    }
    return "/album/form";
  }

  @PostMapping("/new")
  public String create(
      @PathVariable String artistName, @Valid AlbumDTO albumDTO, List<@Valid SongDTO> songDTOs) {
    Optional<Artist> optArtist = artistService.getArtist(artistName);
    if (optArtist.isPresent()) {
      try {
        albumService.create(optArtist.get(), albumDTO, songDTOs);
      } catch (Exception e) {
        // TODO: add error page
        return "error";
      }
    }
    return String.format(
        "redirect/artists/album/%s/%s", optArtist.get().getName(), albumDTO.name());
  }

  @GetMapping("/{albumName}/edit")
  private String edit(
      @PathVariable String artistName, @PathVariable String albumName, Model model) {
    Optional<Album> optAlbum = albumService.getAlbumByArtistAndName(artistName, albumName);
    if (optAlbum.isEmpty()) {
      return "error";
    }

    model.addAttribute("album", optAlbum.get());
    return "/album/form";
  }

  @PutMapping("/{albumName}")
  private String edit(
      @PathVariable String artistName,
      @PathVariable String albumName,
      AlbumDTO albumDTO,
      List<SongDTO> songDTOs) {

    return null;
  }
}
