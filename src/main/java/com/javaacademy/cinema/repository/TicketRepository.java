package com.javaacademy.cinema.repository;

import com.javaacademy.cinema.entity.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TicketRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SessionRepository sessionRepository;
    private final PlaceRepository placeRepository;

    public Optional<Ticket> selectById(Integer id) {
        String sqlQuery = """
                select *
                from ticket
                where id = ?
                """;
        log.info("Выполнен SQL запрос: поиск по ID");
        return Optional.ofNullable(jdbcTemplate.queryForObject(sqlQuery, this::mapToTicket, id));
    }

    @SneakyThrows
    private Ticket mapToTicket(ResultSet rs, int rowNum) {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setIsBuy(rs.getBoolean("is_buy"));
        ticket.setSession(sessionRepository.selectById(rs.getInt("session_id")).get());
        ticket.setPlace(placeRepository.selectById(rs.getInt("place_id")).get());
        return ticket;
    }
}
