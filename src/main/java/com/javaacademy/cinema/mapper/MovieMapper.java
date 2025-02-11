package com.javaacademy.cinema.mapper;

import com.javaacademy.cinema.dto.MovieDto;
import com.javaacademy.cinema.dto.CreateMovieDto;
import com.javaacademy.cinema.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapper {

    public Movie toEntity(CreateMovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        return movie;
    }

    public MovieDto toDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        return movieDto;
    }

    public List<CreateMovieDto> toResponses(List<Movie> movies) {
        return movies.stream().map(this::toResponse).toList();
    }

    public CreateMovieDto toResponse(Movie movie) {
        CreateMovieDto createMovieDto = new CreateMovieDto();
        createMovieDto.setTitle(movie.getTitle());
        createMovieDto.setDescription(movie.getDescription());
        return createMovieDto;
    }
}
