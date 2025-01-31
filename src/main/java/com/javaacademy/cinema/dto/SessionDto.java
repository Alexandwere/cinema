package com.javaacademy.cinema.dto;

import com.javaacademy.cinema.entity.Movie;
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
    private LocalDateTime localDateTime;
    private BigDecimal price;
    private Movie movie;
}
