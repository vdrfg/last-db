package db.last.webapp.controllers;

import db.last.webapp.dtos.ArtistInDTO;
import db.last.webapp.dtos.ArtistOutDTO;
import db.last.webapp.models.Artist;
import db.last.webapp.services.AlbumService;
import db.last.webapp.services.ArtistService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/artists")
@AllArgsConstructor
public class ArtistController {
  private ArtistService artistService;
  private AlbumService albumService;

  @GetMapping
  public String allArtists(Model model) {
    model.addAttribute("artists", artistService.getAllArtists());
    return "artists";
  }

  @GetMapping("/{name}")
  public String artist(@PathVariable String name, Model model) {
    Optional<Artist> artist = artistService.getArtistByName(name);
    if (artist.isEmpty()) {
      // TODO: create dynamic error page
      model.addAttribute("error", "error");
      return "artists";
    }
    model.addAttribute("artist", new ArtistOutDTO(artist.get()));
    model.addAttribute("albums", albumService.getAllAlbumDTOsByArtist(artist.get()));
    return "/artist/detail";
  }

  @GetMapping("/new")
  public String create(Model model) {
    model.addAttribute("artist", new Artist());
    return "artist/form";
  }

  @PostMapping
  public String create(@Valid ArtistInDTO artistInDTO) {
    Artist artist = artistService.save(artistInDTO);
    return String.format("redirect:/artists/%s", artist.getName());
  }

  @GetMapping("/{name}/edit")
  public String edit(@PathVariable String name, Model model) {
    Optional<Artist> artist = artistService.getArtistByName(name);
    if (artist.isPresent()) {
      model.addAttribute("artist", new ArtistOutDTO(artist.get()));
      return "artist/form";
    }
    return "error";
  }

  @PutMapping("/{name}")
  public String edit(@PathVariable String name, ArtistInDTO artistDTO) {
    Optional<Artist> optArtist = artistService.getArtistByName(name);
    if (optArtist.isPresent()) {
      // TODO: add editing method
      return String.format("redirect:/artists/%s", optArtist.get().getName());
    }
    return "error";
  }
}
