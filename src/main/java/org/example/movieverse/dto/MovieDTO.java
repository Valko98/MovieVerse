package org.example.movieverse.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed  100 characters")
    private String title;

    @Size(max = 50, message = "Genre must not exceed 50 characters")
    private String genre;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotBlank(message = "Release year is required")
    @Size(min = 4, max = 4, message = "Release year must be a 4 digit number")
    private String releaseYear;

    private String directorName;


    private List<@NotBlank(message = "Actor name cannot be blank") String> actorNames;
}
