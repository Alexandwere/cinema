package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.BookingDto;
import com.javaacademy.cinema.dto.MovieDto;
import com.javaacademy.cinema.dto.SessionResponse;
import com.javaacademy.cinema.dto.TicketResponse;
import com.javaacademy.cinema.service.MovieService;
import com.javaacademy.cinema.service.SessionService;
import com.javaacademy.cinema.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("cinema/api")
@Tag(
        name = "Контроллер для работы с кинотеатром",
        description = "Содержит команды для совершения действий в кинотеатре"
)
@ApiResponses
@RestController
@RequiredArgsConstructor
public class CinemaController {
    private final MovieService movieService;
    private final SessionService sessionService;
    private final TicketService ticketService;

    @Operation(summary = "Получение всех фильмов",
            description = "Получение названия и описания всех фильмов")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка фильмов")
    @Cacheable("movies")
    @GetMapping("/movie")
    public List<MovieDto> findMovies() {
        return movieService.findAll();
    }

    @Operation(summary = "Получение всех сеансов",
            description = "Получение всех сеансов с указанием номера сеанса, фильма, времени и цены билета")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка сеансов")
    @Cacheable("sessions")
    @GetMapping("/session")
    public List<SessionResponse> findSessions() {
        return sessionService.findAll();
    }

    @Operation(summary = "Получение свободных мест на сеанс",
            description = "Получение свободных мест на сеанс по его номеру")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешное получение списка свободных мест на сеанс."),
            @ApiResponse(responseCode = "400", description = "Сеанса не существует.")
    })
    @Cacheable("freePlaces")
    @GetMapping("/session/{id}/free-place")
    public List<String> findEmptyPlaces(@PathVariable Integer id) {
        return ticketService.findFreePlaces(id);
    }

    @Operation(summary = "Покупка билета",
            description = "Покупка билета по номеру сеанса и месту")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешная покупка билета."),
            @ApiResponse(responseCode = "400", description = "Сеанса не существует."),
            @ApiResponse(responseCode = "400", description = "Места не существует.")
    })
    @Caching(evict = {
            @CacheEvict(value = "freePlaces", allEntries = true),
            @CacheEvict(value = "buyTickets", allEntries = true)
    })
    @PostMapping("/ticket/booking")
    public TicketResponse buyTicket(@RequestBody BookingDto bookingDto) {
        return ticketService.buyTicket(bookingDto);
    }
}
