package com.javaacademy.cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {
    private Integer id;
    @JsonProperty("date_and_time")
    private LocalDateTime localDateTime;
    private BigDecimal price;
    @JsonProperty("movie_id")
    private Integer movieId;
}
