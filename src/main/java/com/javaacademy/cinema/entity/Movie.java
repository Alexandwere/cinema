package com.javaacademy.cinema.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class Movie {
    private Integer id;
    private String title;
    private String description;
}
