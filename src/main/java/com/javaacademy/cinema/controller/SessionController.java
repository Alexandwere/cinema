package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.SessionDto;
import com.javaacademy.cinema.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("cinema/api/session")
public class SessionController {
    private final SessionService sessionService;

    @PostMapping
    public void saveSession(@RequestBody SessionDto sessionDto) {
        sessionService.saveSession(sessionDto);
    }
}
