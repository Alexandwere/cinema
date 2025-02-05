package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.SessionDto;
import com.javaacademy.cinema.dto.SessionResponse;
import com.javaacademy.cinema.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cinema/api/session")
public class SessionController {
    private final SessionService sessionService;
//АДМИН
    @PostMapping
    public void saveSession(@RequestBody SessionDto sessionDto) {
        sessionService.saveSession(sessionDto);
    }
}
