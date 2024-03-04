package db.last.webapp.controllers;

import db.last.webapp.dtos.ArtistDTO;
import db.last.webapp.models.Artist;
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

    @GetMapping
    public String allArtists(Model model) {
        model.addAttribute("artists", artistService.getAllArtists());
        return "artists";
    }

    @GetMapping("/{name}")
    public String artist(@PathVariable String name, Model model) {
        Optional<Artist> optArtist = artistService.getArtist(name);
        if (optArtist.isEmpty()) {
            //TODO: create dynamic error page
            model.addAttribute("error", "error");
            return "artists";
        }
        model.addAttribute("artist", optArtist.get());
        return "/artist/detail";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("artist", new Artist());
        return "artist/form";
    }

    @PostMapping
    public String create(@Valid ArtistDTO artistDTO) {
        Artist artist = artistService.save(artistDTO);
        return String.format("redirect:/artists/%s", artist.getName());
    }
}
