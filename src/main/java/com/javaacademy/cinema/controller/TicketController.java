package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.TicketDto;
import com.javaacademy.cinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cinema/api/ticket")
public class TicketController {
    private final TicketService ticketService;
//АДМИН
    @GetMapping("/saled/{id}")
    public List<TicketDto> findBuyTickets(@PathVariable Integer id) {
        return ticketService.findAllBuyTicket(id);
    }
}
