package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.SessionCreateDto;
import com.javaacademy.cinema.entity.Ticket;
import com.javaacademy.cinema.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cinema/session")
@Tag(
        name = "Контроллер для работы с сеансами",
        description = "Содержит команды для совершения действий с сеансами"
)
public class SessionController {
    private final SessionService sessionService;
    private final Validator validator;

    @Operation(summary = "Создание сеанса",
        description = "Создание сеанса с его номером, временем, фильмом и ценой")
    @PostMapping
    public List<Ticket> saveSession(@RequestHeader("User-token") String password,
                                    @RequestBody SessionCreateDto sessionDto) {
        validator.checkAdmin(password);
        return sessionService.saveSession(sessionDto);
    }
}
