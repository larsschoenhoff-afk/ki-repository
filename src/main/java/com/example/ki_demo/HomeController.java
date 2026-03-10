package com.example.ki_demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        List<AddressEntry> entries = List.of(
                new AddressEntry("Max Mustermann", "Hauptstraße 1", "Berlin", "10115", "+49 30 1234567"),
                new AddressEntry("Erika Musterfrau", "Bahnhofstraße 23", "Hamburg", "20095", "+49 40 2345678"),
                new AddressEntry("Lena Schmidt", "Rosenweg 5", "München", "80331", "+49 89 3456789"),
                new AddressEntry("Paul Keller", "Lindenallee 14", "Köln", "50667", "+49 221 4567890"),
                new AddressEntry("Sarah Neumann", "Marktplatz 9", "Frankfurt am Main", "60311", "+49 69 5678901")
        );

        model.addAttribute("entries", entries);
        return "index";
    }

    public record AddressEntry(String name, String street, String city, String postalCode, String phoneNumber) {
    }
}
