package com.javaacademy.cinema.service;

import com.javaacademy.cinema.dto.CreateSessionDto;
import com.javaacademy.cinema.dto.SessionResponse;
import com.javaacademy.cinema.entity.Movie;
import com.javaacademy.cinema.entity.Place;
import com.javaacademy.cinema.entity.Session;
import com.javaacademy.cinema.entity.Ticket;
import com.javaacademy.cinema.exception.NotFoundMovieException;
import com.javaacademy.cinema.mapper.SessionMapper;
import com.javaacademy.cinema.repository.MovieRepository;
import com.javaacademy.cinema.repository.PlaceRepository;
import com.javaacademy.cinema.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final PlaceRepository placeRepository;
    private final SessionMapper sessionMapper;
    private final MovieRepository movieRepository;

    /**
     Создание сеанса
     */
    public List<Ticket> saveSession(CreateSessionDto sessionDto) {
        Optional<Movie> movie = movieRepository.selectMovieById(sessionDto.getMovieId());
        if (movie.isEmpty()) {
            throw new NotFoundMovieException("Фильм с таким ID не существует.");
        }
        Session session = sessionRepository.save(sessionMapper.toEntity(sessionDto));
        log.info("Создан сеанс № {}.\n", session.getId());
        List<Place> allPlace = placeRepository.selectAll();
        List<Ticket> allTicket = allPlace.stream()
                .map(e-> Ticket.builder()
                    .place(e)
                    .isBuy(false)
                    .session(session)
                    .build())
                .toList();
        log.info("Созданы не проданные билеты на сеанс {}.\n", session.getId());
        return allTicket;
    }

    /**
     Показать все сеансы
     */
    public List<SessionResponse> findAll() {
        List<Session> sessions = sessionRepository.findAll();
        List<SessionResponse> sessionResponses = sessionMapper.toSessions(sessions);
        log.info("Получен список сеансов\n");
        return sessionResponses;
    }
}
