package com.javaacademy.cinema.web.controller;

import com.javaacademy.cinema.dto.CreateMovieDto;
import com.javaacademy.cinema.entity.Movie;
import com.javaacademy.cinema.repository.MovieRepository;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.RestAssured.given;

@AutoConfigureMockMvc
@DisplayName("Тесты контроллера фильмов")
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MovieControllerTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MovieRepository movieRepository;
    @Value("${app.admin_token}")
    String trueToken;
    @Value("${app.admin_password}")
    String truePassword;

    private static final String CLEAN_MOVIE_TABLE = "delete from movie;";

    private final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBasePath("cinema/movie")
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private final ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();

    @BeforeEach()
    public void cleanUpData() {
        jdbcTemplate.execute(CLEAN_MOVIE_TABLE);
    }

    @Test
    @DisplayName("Сохранение фильма - успешно")
    public void createMovieSuccess() {
        Header header1 = new Header("token", trueToken);
        Header header2 = new Header("password", truePassword);

        String expectedTitle = "Форсаж";
        String expectedDescription = "Полицейский под прикрытием Брайан О'Коннор (Пол Уокер) внедряется в команду" +
                " Доминика Торетто (Вин Дизель), чтобы раскрыть банду стритрейсеров, грабящих грузовики";
        CreateMovieDto movieDto = new CreateMovieDto(expectedTitle, expectedDescription);

        given(requestSpecification)
                .header(header1)
                .header(header2)
                .body(movieDto)
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Сохранение фильма - ошибка: Фильм уже существует")
    public void createMovieFailed() {
        Header header1 = new Header("token", trueToken);
        Header header2 = new Header("password", truePassword);

        String expectedTitle = "Форсаж";
        String expectedDescription = "Полицейский под прикрытием Брайан О'Коннор (Пол Уокер) внедряется в команду" +
                " Доминика Торетто (Вин Дизель), чтобы раскрыть банду стритрейсеров, грабящих грузовики";
        movieRepository.save(Movie.builder()
                .title(expectedTitle)
                .description(expectedDescription)
                .build());
        CreateMovieDto movieDto = new CreateMovieDto(expectedTitle, expectedDescription);

        given(requestSpecification)
                .header(header1)
                .header(header2)
                .body(movieDto)
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
