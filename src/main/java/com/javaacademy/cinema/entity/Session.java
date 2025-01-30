package com.javaacademy.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Session {
    private Integer id;
    private LocalDateTime localDateTime;
    private BigDecimal price;
    private Movie movie;
}
