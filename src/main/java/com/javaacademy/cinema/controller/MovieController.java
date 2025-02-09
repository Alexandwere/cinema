package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.MovieDto;
import com.javaacademy.cinema.dto.MovieResponse;
import com.javaacademy.cinema.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("cinema/movie")
@Tag(
        name = "Контроллер для работы с фильмами",
        description = "Содержит команды для совершения действий с фильмами"
)
public class MovieController {
    private final MovieService movieService;
    private final Validator validator;

    @Operation(summary = "Сохранение фильма",
        description = "Сохранения фильма с названием и описанием.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешное сохранение фильма."),
            @ApiResponse(responseCode = "400", description = "Фильм уже существует."),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен.")
    })
    @CacheEvict(value = "movies", allEntries = true)
    @PostMapping
    public MovieDto saveMovie(@RequestHeader("token") String token,
                              @RequestHeader("password") String password,
                              @RequestBody MovieResponse movieResponse) {
        validator.checkAdmin(token, password);
        return movieService.saveMovie(movieResponse);
    }
}
