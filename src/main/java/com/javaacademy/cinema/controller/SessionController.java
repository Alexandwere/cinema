package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.SessionDto;
import com.javaacademy.cinema.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("cinema/api/session")
@Tag(
        name = "Контроллер для работы с сеансами",
        description = "Содержит команды для совершения действий с сеансами"
)
public class SessionController {
    private final SessionService sessionService;
//АДМИН
    @Operation(summary = "Создание сеанса",
        description = "Создание сеанса с его номером, временем, фильмом и ценой")
    @PostMapping
    public void saveSession(@RequestBody SessionDto sessionDto) {
        sessionService.saveSession(sessionDto);
    }
}
