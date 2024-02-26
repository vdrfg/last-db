package db.last.webapp.controllers;

import db.last.webapp.models.Scrobble;
import db.last.webapp.services.ScrobbleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/scrobbles")
public class ScrobbleController {

    ScrobbleService scrobbleService;

    @GetMapping
    public String something(Model model) {
        List<Scrobble> scrobbles = scrobbleService.getScrobbles();
        model.addAttribute("scrobbles", scrobbles);
        return "scrobbles";
    }

}
