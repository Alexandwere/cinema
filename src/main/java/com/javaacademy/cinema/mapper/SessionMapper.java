package com.javaacademy.cinema.mapper;

import com.javaacademy.cinema.dto.SessionDto;
import com.javaacademy.cinema.dto.SessionResponse;
import com.javaacademy.cinema.entity.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessionMapper {

    public Session toEntity(SessionDto sessionDto) {
        Session session = new Session();
        session.setId(sessionDto.getId());
        session.setMovie(sessionDto.getMovie());
        session.setPrice(sessionDto.getPrice());
        session.setLocalDateTime(sessionDto.getLocalDateTime());
        return session;
    }

    public SessionDto toDto(Session session) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(session.getId());
        sessionDto.setMovie(session.getMovie());
        sessionDto.setPrice(session.getPrice());
        sessionDto.setLocalDateTime(session.getLocalDateTime());
        return sessionDto;
    }

    public List<SessionResponse> toSessions(List<Session> sessions) {
        return sessions.stream().map(this::toSessionResponse).toList();
    }

    private SessionResponse toSessionResponse(Session session) {
        SessionResponse sessionResponse = new SessionResponse();
        sessionResponse.setId(session.getId());
        sessionResponse.setMovieName(session.getMovie().getTitle());
        sessionResponse.setLocalDateTime(session.getLocalDateTime());
        sessionResponse.setPrice(session.getPrice());
        return sessionResponse;
    }
}
