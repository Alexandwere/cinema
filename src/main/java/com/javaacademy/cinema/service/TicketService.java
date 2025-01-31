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
        log.info("Получены все купленные билеты.\n");
        return ticketMapper.toDtos(ticketList);
    }
}
