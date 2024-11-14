package models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Director {

    @Id
    private Long id;

    private String name;

    private String birthdate;


}
