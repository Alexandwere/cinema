package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.TicketDto;
import com.javaacademy.cinema.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cinema/api/ticket")
@Tag(
        name = "Контроллер для работы с билетами",
        description = "Содержит команды для совершения действий с билетами"
)
public class TicketController {
    private final TicketService ticketService;
//АДМИН
    @Operation(summary = "Получение списка купленных билетов",
        description = "Получение списка купленных билетов по номеру сеанса")
    @GetMapping("/saled/{id}")
    public List<TicketDto> findBuyTickets(@PathVariable Integer id) {
        return ticketService.findAllBuyTicket(id);
    }
}
