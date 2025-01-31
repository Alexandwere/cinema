package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.MovieDto;
import com.javaacademy.cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("cinema/api/movie")
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    public MovieDto saveMovie(@RequestBody MovieDto movieDto) {
        return movieService.saveMovie(movieDto);
    }
}
