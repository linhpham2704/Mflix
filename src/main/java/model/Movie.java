package model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data
public class Movie {
    private ObjectId id;
    private String title;
    private int year;
    private int runtime;
    private String plot;
    private String fullplot;
    private String lastupdated;
    private String rated;
    private String type;
    private String poster;
    private int num_mflix_comments;

    private List<String> cast;
    private List<String> directors;
    private List<String> writers;
    private List<String> countries;
    private List<String> genres;
    private List<String> languages;

    private Awards awards;
    private Imdb imdb;
    private Tomatoes tomatoes;
}
