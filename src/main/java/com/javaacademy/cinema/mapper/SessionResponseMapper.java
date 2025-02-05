package com.javaacademy.cinema.mapper;

import com.javaacademy.cinema.dto.SessionResponse;
import com.javaacademy.cinema.entity.Session;

import java.util.List;

public class SessionResponseMapper {

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
