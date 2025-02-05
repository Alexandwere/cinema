package com.javaacademy.cinema.service;

import com.javaacademy.cinema.dto.BookingDto;
import com.javaacademy.cinema.dto.TicketDto;
import com.javaacademy.cinema.dto.TicketResponse;
import com.javaacademy.cinema.entity.Movie;
import com.javaacademy.cinema.entity.Place;
import com.javaacademy.cinema.entity.Session;
import com.javaacademy.cinema.entity.Ticket;
import com.javaacademy.cinema.mapper.TicketMapper;
import com.javaacademy.cinema.repository.SessionRepository;
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
    private final SessionRepository sessionRepository;

    /**
     Покупка билета
     */
    public TicketResponse buyTicket(BookingDto bookingDto) {
//        добавить проверку на существование сеанса
        Session session = sessionRepository.selectById(bookingDto.getSessionId()).get();
        Place place = ticketRepository.selectByNumber(bookingDto.getPlaceNumber()).get().getPlace();
        Ticket ticket = Ticket.builder()
                .place(place)
                .session(session)
                .build();
        Ticket currentTicket = ticketRepository.save(ticket);
        ticketRepository.buy(currentTicket.getId());
        Movie movie = ticket.getSession().getMovie();

        TicketResponse ticketResponse = new TicketResponse(ticket.getId(), ticket.getPlace().getNumber(),
                movie.getTitle(), session.getLocalDateTime());
        log.info("Получен билет для посетителя");
        return ticketResponse;
    }

    /**
     Поиск всех купленных билетов на сеанс
     */
    public List<TicketDto> findAllBuyTicket(Integer id) {
        List<Ticket> ticketList = ticketRepository.selectBuyTickets(id);
        log.info("Получены все купленные билеты на сеанс.\n");
        return ticketMapper.toDtos(ticketList);
    }

    /**
     Показать свободные места на сеанс
     */
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
