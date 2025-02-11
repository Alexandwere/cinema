package com.javaacademy.cinema.service;

import com.javaacademy.cinema.dto.MovieDto;
import com.javaacademy.cinema.dto.CreateMovieDto;
import com.javaacademy.cinema.entity.Movie;
import com.javaacademy.cinema.mapper.MovieMapper;
import com.javaacademy.cinema.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    /**
     Сохранение фильма
     */
    public MovieDto saveMovie(CreateMovieDto createMovieDto) {
        Movie movie = movieRepository.save(movieMapper.toEntity(createMovieDto));
        log.info("Сохранен фильм в БД.\n");
        return movieMapper.toDto(movie);
    }

    /**
     Показать все фильмы
     */
    public List<CreateMovieDto> findAll() {
        List<Movie> movies = movieRepository.selectAll();
        log.info("Получены все фильмы.\n");
        return movieMapper.toResponses(movies);
    }
}
