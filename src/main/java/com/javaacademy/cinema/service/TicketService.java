package com.javaacademy.cinema.service;

import com.javaacademy.cinema.dto.TicketDto;
import com.javaacademy.cinema.entity.Ticket;
import com.javaacademy.cinema.mapper.TicketMapper;
import com.javaacademy.cinema.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    /**
     Поиск всех купленных билетов на сеанс
     */
    public List<TicketDto> findAllBuyTicket(Integer id) {
        List<Ticket> ticketList = ticketRepository.selectBuyTickets(id);
        log.info("Получены все купленные билеты на сеанс.\n");
        return ticketMapper.toDtos(ticketList);
    }

    public List<String> findFreePlaces(Integer id) {
        List<TicketDto> tickets = findAllBuyTicket(id);
        List<String> numbers = tickets.stream()
                .filter(e -> !e.getIsBuy())
                .map(e -> e.getPlace().getNumber())
                .toList();
        log.info("Получены номера свободных мест на сеанс.\n");
        return numbers;
    }

}
