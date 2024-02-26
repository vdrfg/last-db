package db.last.webapp.controllers;

import db.last.webapp.dtos.ScrobbleDTO;
import db.last.webapp.models.Scrobble;
import db.last.webapp.services.FileService;
import db.last.webapp.services.ScrobbleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {

    private FileService fileService;
    private ScrobbleService scrobbleService;

    @GetMapping("/")
    public String mainPage(Model model) {
        List<ScrobbleDTO> scrobbles = fileService.csvToScrobbleDTOs("src/main/resources/static/test.csv");
        scrobbleService.saveBatch(scrobbles);
        return "index";
    }
}
