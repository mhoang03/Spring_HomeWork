package com.example.springweb.entities;

import java.time.LocalDate;
//EM KO DUNG DC @DATA E CX BT LY DO VI SAO E DA THU FIX ROI
public class Movie {
    private int id;
    private String name;
    private String genre;
    private String director;
    private int duration;
    private String description;
    private LocalDate publishedDate;
    private String category;

    public Movie() {}

    public Movie(int id, String name, String genre, String director, int duration, String description, LocalDate publishedDate, String category) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.director = director;
        this.duration = duration;
        this.description = description;
        this.publishedDate = publishedDate;
        this.category = category;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getPublishedDate() { return publishedDate; }
    public void setPublishedDate(LocalDate publishedDate) { this.publishedDate = publishedDate; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}