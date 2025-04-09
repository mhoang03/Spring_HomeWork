package com.example.springweb.entities;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Movie {
    private Long id;
    private String name;
    private String genre;
    private String director;
    private int duration;
    private String description;
    private LocalDate publishedDate;
    private String category;

}