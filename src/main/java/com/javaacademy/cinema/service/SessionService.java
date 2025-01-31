package com.javaacademy.cinema.service;

import com.javaacademy.cinema.dto.SessionDto;
import com.javaacademy.cinema.entity.Place;
import com.javaacademy.cinema.entity.Session;
import com.javaacademy.cinema.entity.Ticket;
import com.javaacademy.cinema.mapper.SessionMapper;
import com.javaacademy.cinema.repository.PlaceRepository;
import com.javaacademy.cinema.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final PlaceRepository placeRepository;
    private final SessionMapper sessionMapper;

    /**
     Создание сеанса
     */
    public void saveSession(SessionDto sessionDto) {
//        Добавить проверку на существование сеанса
        Session session = sessionRepository.save(sessionMapper.toEntity(sessionDto));
        log.info("Создан сеанс.\n");
        List<Place> allPlace = placeRepository.selectAll();
        List<Ticket> allTicket = allPlace.stream()
                .map(e-> Ticket.builder()
                    .place(e)
                    .isBuy(false)
                    .session(session)
                    .build())
                .toList();
        log.info("Созданы не купленные билеты на сеанс {}.\n", session.getId());
    }
}
