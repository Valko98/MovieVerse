package org.example.movieverse.config;

import org.example.movieverse.service.CustomUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetails customUserDetails;

    public SecurityConfig(CustomUserDetails customUserDetails) {
        this.customUserDetails = customUserDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity (not recommended for production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/auth/login","/css/**")
                                .permitAll().
                                anyRequest().
                                authenticated()
//                        .requestMatchers("/admin/**").hasRole("ADMIN") //admin only routes
//                        .requestMatchers("/moderator/**").hasRole("MODERATOR") //moderator only routes
//                        .requestMatchers("/user/**").hasRole("USER")// user only routes

                                           // Public access
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/home", true)
//                        .failureForwardUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")   // Redirect after logout
                        .permitAll()).userDetailsService(customUserDetails);

        return http.build();
    }



}
