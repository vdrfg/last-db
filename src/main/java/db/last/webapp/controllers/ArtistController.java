package db.last.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import db.last.webapp.dtos.ArtistInDTO;

@Controller
@RequestMapping("/artist")
public class ArtistController {

	@GetMapping("/create")
	public String createForm() {
		return "artistForm";
	}

	@PostMapping("/create")
	public String createArtist(@RequestBody ArtistInDTO createdArtist) {
		// TODO: incomplete
		return "redirect:/artist/list"; // TODO: to be changed
	}
}
