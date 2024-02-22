package db.last.webapp.controllers;

import db.last.webapp.models.Scrobble;
import db.last.webapp.services.FileService;
import db.last.webapp.services.ScrobbleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {

    private FileService fileService;
    private ScrobbleService scrobbleService;

    @GetMapping("/")
    public ResponseEntity<?> mainPage() {
        List<Scrobble> scrobbles = fileService.csvToScrobbles("src/main/resources/static/test.csv");
        return ResponseEntity.ok(scrobbles);
    }
}
