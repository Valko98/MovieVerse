package org.example.movieverse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

    @NotBlank(message = "username is required")
    @Size(min = 4, max = 20,
            message = "username must be between 4 and 20 characters")
    private String username;

    @NotBlank(message = "email is required")
    @Email(message = "email is invalid!")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 6, message = "password should be at least 6 characters")
    private String password;

    @NotBlank(message = "password is required")
    private String confirmPassword;

    // Password matching validation
    public boolean isPasswordsMatch() {
        return this.password != null && this.password.equals(this.confirmPassword);
    }
}