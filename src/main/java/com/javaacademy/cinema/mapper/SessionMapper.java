package com.javaacademy.cinema.mapper;

import com.javaacademy.cinema.dto.SessionDto;
import com.javaacademy.cinema.entity.Session;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SessionMapper {
    Session toEntity(SessionDto sessionDto);
    SessionDto toDto(Session session);
}
