package com.example.ki_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
public class AddressEntryController {

    private final AddressEntryService addressEntryService;

    public AddressEntryController(AddressEntryService addressEntryService) {
        this.addressEntryService = addressEntryService;
    }

    @GetMapping
    public List<AddressEntry> getEntries() {
        return addressEntryService.getEntries();
    }
}
