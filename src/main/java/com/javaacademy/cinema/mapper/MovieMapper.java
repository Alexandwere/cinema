package com.javaacademy.cinema.mapper;

import com.javaacademy.cinema.dto.MovieDto;
import com.javaacademy.cinema.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovieMapper {
    Movie toEntity(MovieDto movieDto);
    MovieDto toDto(Movie movie);
}
