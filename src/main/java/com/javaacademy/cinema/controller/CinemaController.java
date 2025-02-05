package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.MovieResponse;
import com.javaacademy.cinema.dto.SessionResponse;
import com.javaacademy.cinema.service.MovieService;
import com.javaacademy.cinema.service.SessionService;
import com.javaacademy.cinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("cinema/api")
public class CinemaController {
    private final MovieService movieService;
    private final SessionService sessionService;
    private final TicketService ticketService;

    @GetMapping("/movie")
    public List<MovieResponse> findMovies() {
        return movieService.findAll();
    }

    @GetMapping("/session")
    public List<SessionResponse> findSessions() {
        return sessionService.findAll();
    }

    @GetMapping("/session/{id}/free-place")
    public List<String> findEmptyPlaces(@PathVariable Integer id) {
        return ticketService.findFreePlaces(id);
    }

    public void buyTicket(@RequestBody ) {

    }
}
