package com.example.ki_demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        List<AddressEntry> entries = IntStream.rangeClosed(1, 1000)
                .mapToObj(index -> new AddressEntry(
                        "Beispielsatz " + index,
                        "Musterstraße " + index,
                        "Musterstadt " + ((index % 20) + 1),
                        String.format("%05d", 10000 + index),
                        "+49 30 " + String.format("%07d", index)
                ))
                .toList();

        model.addAttribute("entries", entries);
        return "index";
    }

    public record AddressEntry(String name, String street, String city, String postalCode, String phoneNumber) {
    }
}
