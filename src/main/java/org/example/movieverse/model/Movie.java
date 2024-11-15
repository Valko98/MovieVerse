package org.example.movieverse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "movies")
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String genre;

    @Column(nullable = false)
    private String description;

    @Column
    private String releaseYear;

    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @OneToMany
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;


    @ManyToMany(mappedBy = "movies")
    private List<MovieList> movieLists;

}
