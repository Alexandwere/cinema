package com.javaacademy.cinema.repository;

import com.javaacademy.cinema.entity.Movie;
import com.javaacademy.cinema.entity.Place;
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
public class MovieRepository {
    private final JdbcTemplate jdbcTemplate;

    public Optional<Movie> selectMovieById(Integer id) {
        String sqlQuery = """
        select *
        from movie
        where id = ?
        """;
        Optional<Movie> result = Optional.ofNullable(jdbcTemplate.queryForObject(sqlQuery, this::mapToMovie, id));
        log.info("Выполнен SQL запрос: {}, по id = {}, результат: {}", sqlQuery, id, result);
        return result;
    }

    @SneakyThrows
    private Movie mapToMovie(ResultSet rs, int rowNum) {
        Movie movie = new Movie();
        movie.setId(rs.getInt("id"));
        movie.setTitle(rs.getString("title"));
        movie.setDescription(rs.getString("description"));
        return movie;
    }
}
