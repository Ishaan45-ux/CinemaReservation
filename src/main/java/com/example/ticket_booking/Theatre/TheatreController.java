package com.example.ticket_booking.Theatre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TheatreController {

    @Autowired
    private TheatreRepository theatreRepository;

    @GetMapping("/add-theatre")
    public String showAddTheatreForm(Model model) {
        model.addAttribute("theatre", new Theatre());
        return "add-theatre"; // Looks for add-theatre.html
    }

    @PostMapping("/add-theatre")
    public String addTheatre(@ModelAttribute Theatre theatre) {
        theatreRepository.save(theatre);
        return "redirect:/add-theatre?success";
    }
}