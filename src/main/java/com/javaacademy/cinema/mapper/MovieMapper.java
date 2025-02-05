package com.javaacademy.cinema.mapper;

import com.javaacademy.cinema.dto.MovieDto;
import com.javaacademy.cinema.dto.MovieResponse;
import com.javaacademy.cinema.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovieMapper {
    Movie toEntity(MovieDto movieDto);
    MovieDto toDto(Movie movie);
    List<MovieResponse> toResponses(List<Movie> movies);
}
