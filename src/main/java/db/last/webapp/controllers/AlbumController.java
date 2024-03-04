package db.last.webapp.controllers;

import db.last.webapp.dtos.AlbumDTO;
import db.last.webapp.dtos.SongDTO;
import db.last.webapp.models.Album;
import db.last.webapp.models.Artist;
import db.last.webapp.services.AlbumService;
import db.last.webapp.services.ArtistService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/artists/album/{artistName}")
public class AlbumController {

  private AlbumService albumService;
  private ArtistService artistService;

  @GetMapping("/{albumName]")
  public String album(@PathVariable String artistName, String albumName, Model model) {
    Optional<Artist> optArtist = artistService.getArtist(artistName);

    if (optArtist.isEmpty()) {
      return "error";
    }

    Optional<Album> optAlbum = albumService.getAlbumByArtistAndName(optArtist.get(), albumName);

    return optAlbum.isPresent() ? "/album/page" : "error";
  }

  @GetMapping("/new")
  public String create(@PathVariable String artistName, Model model) {
    model.addAttribute("album", new Album());
    return "/album/form";
  }

  @PostMapping
  public String create(@PathVariable String artistName, @Valid AlbumDTO albumDTO, List<@Valid SongDTO> songDTOs) {
    try {
      albumService.create(albumDTO, songDTOs);
    } catch (Exception e) {
      return "error";
    }
    return String.format(
        "redirect/artists/album/%s/%s", albumDTO.artist().getName(), albumDTO.name());
  }
}
