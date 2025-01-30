package com.javaacademy.cinema.repository;

import com.javaacademy.cinema.entity.Movie;
import com.javaacademy.cinema.entity.Place;
import com.javaacademy.cinema.entity.Session;
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
public class SessionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final MovieRepository movieRepository;

    public Optional<Session> selectById(Integer id) {
        String sqlQuery = """
                select *
                from Session
                where id = ?
                """;
        Optional<Session> result = Optional.ofNullable(jdbcTemplate.queryForObject(sqlQuery, this::mapToSession, id));
        log.info("Выполнен SQL запрос: {}, по id = {}, результат: {}", sqlQuery, id, result);
        return result;
    }

    @SneakyThrows
    private Session mapToSession(ResultSet rs, int rowNum) {
        Session session = new Session();
        session.setId(rs.getInt("id"));
        session.setLocalDateTime(rs.getDate("date_and_time").toLocalDate().atTime(24, 60));
       // Возможно здесь Optional не надо
        Optional<Movie> movie = movieRepository.selectMovieById(rs.getInt("movie_id"));
        movie.ifPresent(session::setMovie);
        return session;
    }
}
