package models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Movie {

    @Id
    private Long id;

    private String title;

    private String genre;

    private String description;

    private String releaseYear;




}
