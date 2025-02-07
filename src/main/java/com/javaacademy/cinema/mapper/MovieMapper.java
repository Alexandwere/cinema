package com.javaacademy.cinema.mapper;

import com.javaacademy.cinema.dto.MovieDto;
import com.javaacademy.cinema.dto.MovieResponse;
import com.javaacademy.cinema.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapper {

    public Movie toEntity(MovieResponse movieDto) {
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

    public List<MovieResponse> toResponses(List<Movie> movies) {
        return movies.stream().map(this::toResponse).toList();
    }

    public MovieResponse toResponse(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setTitle(movie.getTitle());
        movieResponse.setDescription(movie.getDescription());
        return movieResponse;
    }
}
