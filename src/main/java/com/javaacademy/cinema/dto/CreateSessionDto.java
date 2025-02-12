package com.javaacademy.cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CreateSessionDto {
    @JsonProperty("date_and_time")
    private LocalDateTime localDateTime;
    private BigDecimal price;
    @JsonProperty("movie_id")
    private Integer movieId;
}
