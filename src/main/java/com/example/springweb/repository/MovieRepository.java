package com.example.springweb.repository;

import com.example.springweb.entities.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {
    private static final Logger logger = LoggerFactory.getLogger(MovieRepository.class);
    private final List<Movie> movies = new ArrayList<>();
    private final Gson gson;

    public MovieRepository(Gson gson) {
        this.gson = gson;
    }

    @PostConstruct
    public void init() {
        java.io.InputStream resource = getClass().getResourceAsStream("/data.json");
        if (resource == null) {
            logger.error("File data.json not found in resources");
            return;
        }

        try (Reader reader = new InputStreamReader(resource)) {
            List<Movie> loadedMovies = gson.fromJson(reader, new TypeToken<List<Movie>>(){}.getType());
            if (loadedMovies == null || loadedMovies.isEmpty()) {
                logger.warn("No data loaded from data.json or data is empty");
            } else {
                movies.addAll(loadedMovies);
                logger.info("Loaded {} movies from data.json", loadedMovies.size());
                logger.debug("First movie ID: {}", loadedMovies.get(0).getId());
            }
        } catch (Exception e) {
            logger.error("Failed to load data from data.json", e);
        }
    }

    public List<Movie> findAll() {
        return movies;
    }
}