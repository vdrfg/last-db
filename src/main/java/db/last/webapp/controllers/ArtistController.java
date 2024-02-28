package db.last.webapp.controllers;

import db.last.webapp.models.Artist;
import db.last.webapp.services.ArtistService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
            model.addAttribute("error", "error");
            return "artists";
        }
        model.addAttribute("artist", optArtist.get());
        return "artistDetail";
    }
}
