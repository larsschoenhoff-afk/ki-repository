package com.example.ki_demo.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component("timeController")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TimeController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private String currentTime;

    @PostConstruct
    public void init() {
        this.currentTime = LocalDateTime.now().format(FORMATTER);
    }

    public String getCurrentTime() {
        return currentTime;
    }
}
