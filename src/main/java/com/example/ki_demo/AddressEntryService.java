package com.example.ki_demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class AddressEntryService {

    public List<AddressEntry> getEntries() {
        return IntStream.rangeClosed(1, 1000)
                .mapToObj(index -> new AddressEntry(
                        "Beispielsatz " + index,
                        "Musterstraße " + index,
                        "Musterstadt " + ((index % 20) + 1),
                        String.format("%05d", 10000 + index),
                        "+49 30 " + String.format("%07d", index)
                ))
                .toList();
    }
}
