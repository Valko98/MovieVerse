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

    @NotBlank(message = "{username}")
    @Size(min = 4, max = 20,
            message = "{username}")
    private String username;

    @NotBlank(message = "{email}")
    @Email(message = "{email}")
    private String email;

    @NotBlank(message = "{password}")
    @Size(min = 6, message = "{password}")
    private String password;

    @NotBlank(message = "{confirmPassword}")
    private String confirmPassword;

    // Password matching validation
    public boolean isPasswordsMatch() {
        return this.password != null && this.password.equals(this.confirmPassword);
    }
}