package models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Review {

    @Id
    private Long id;

    private String content;

    private int rating;

    private Date datePosted;

}
