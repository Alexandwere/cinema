package com.javaacademy.cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaacademy.cinema.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionResponse {
    private Integer id;
    @JsonProperty("movie_name")
    private String movieName;
    @JsonProperty("date")
    private LocalDateTime localDateTime;
    private BigDecimal price;
}