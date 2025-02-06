package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.MovieDto;
import com.javaacademy.cinema.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("cinema/api/movie")
@Tag(
        name = "Контроллер для работы с фильмами",
        description = "Содержит команды для совершения действий с фильмами"
)
public class MovieController {
    private final MovieService movieService;
//АДМИН
    @Operation(summary = "Сохранение фильма",
        description = "Сохранения фильма с названием и описанием.")
    @PostMapping
    public MovieDto saveMovie(@RequestBody MovieDto movieDto) {
        return movieService.saveMovie(movieDto);
    }
}
