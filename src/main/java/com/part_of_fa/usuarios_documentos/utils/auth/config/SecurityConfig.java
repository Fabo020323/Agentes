// com.part_of_fa.usuarios_documentos.auth.config.SecurityConfig.java
package com.part_of_fa.usuarios_documentos.utils.auth.config;
import com.part_of_fa.usuarios_documentos.utils.auth.service.JwtRequestFilter;
import com.part_of_fa.usuarios_documentos.user.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final CustomUserDetailsService userDetailsService;

    // Define el bean para el PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Definir la configuración de seguridad a través de SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF de manera moderna
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/users/register").permitAll()  // Rutas públicas
                        .anyRequest().authenticated()  // Rutas protegidas
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // API sin estado
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);  // Añadir el filtro JWT

        return http.build();
    }

    // Definir el bean para el AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}