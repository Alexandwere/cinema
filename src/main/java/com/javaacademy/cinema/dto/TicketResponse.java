package com.javaacademy.cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    @JsonProperty("ticket_id")
    private Integer id;
    @JsonProperty("place_name")
    private String placeNumber;
    @JsonProperty("movie_name")
    private String movieName;
    @JsonProperty
    private LocalDateTime date;
}
