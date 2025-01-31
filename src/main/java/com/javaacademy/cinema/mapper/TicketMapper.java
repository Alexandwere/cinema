package com.javaacademy.cinema.mapper;

import com.javaacademy.cinema.dto.TicketDto;
import com.javaacademy.cinema.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketMapper {
    Ticket toEntity(TicketDto ticketDto);
    TicketDto toDto(Ticket ticket);
    List<TicketDto> toDtos(List<Ticket> tickets);
}
