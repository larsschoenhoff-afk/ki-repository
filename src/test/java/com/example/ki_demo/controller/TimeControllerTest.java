package com.example.ki_demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TimeControllerTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    @Test
    void shouldInitializeCurrentTimeOncePerSessionBeanInstance() {
        TimeController controller = new TimeController();

        controller.init();
        String firstRead = controller.getCurrentTime();
        String secondRead = controller.getCurrentTime();

        assertNotNull(firstRead);
        assertEquals(firstRead, secondRead);
        assertDoesNotThrow(() -> LocalDateTime.parse(firstRead, FORMATTER));
    }
}
