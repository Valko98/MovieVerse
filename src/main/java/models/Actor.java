package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Actor {

    @Id
    private Long id;

    private String name;

    private Date birthdate;

    private String biography;


}
