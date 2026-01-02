package com.campusmaster.config;

import com.campusmaster.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
                this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .cors(Customizer.withDefaults())
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                // Preflight CORS
                                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                                                // Public endpoints
                                                .requestMatchers(
                                                                "/api/auth/**",
                                                                "/swagger-ui.html",
                                                                "/swagger-ui/**",
                                                                "/v3/api-docs/**",
                                                                "/api-docs/**")
                                                .permitAll()
                                                // ✅ AJOUT ICI (avant /api/v1/** admin-only)
                                                .requestMatchers(HttpMethod.GET,
                                                                "/api/v1/utilisateurs",
                                                                "/api/v1/utilisateurs/**")
                                                .hasAnyRole("ENSEIGNANT", "ADMIN")

                                                // Enseignant + Admin : accès CRUD sur référentiels pédagogiques
                                                // (on garde /api/v1/** global en ADMIN-only, mais on ouvre ces
                                                // ressources spécifiques)
                                                // NOTE: AntPath "/**" ne matche pas forcément l'URL racine sans "/".
                                                // On autorise donc à la fois l'endpoint racine et ses sous-routes.
                                                .requestMatchers(
                                                                "/api/v1/semestres",
                                                                "/api/v1/semestres/**",
                                                                "/api/v1/modules",
                                                                "/api/v1/modules/**",
                                                                "/api/v1/matieres",
                                                                "/api/v1/matieres/**",
                                                                "/api/v1/cours",
                                                                "/api/v1/cours/**",
                                                                "/api/v1/supports-cours",
                                                                "/api/v1/supports-cours/**",
                                                                "/api/v1/devoirs",
                                                                "/api/v1/devoirs/**",
                                                                "/api/v1/depots-devoir",
                                                                "/api/v1/depots-devoir/**",
                                                                "/api/v1/notes-devoir",
                                                                "/api/v1/notes-devoir/**")
                                                .hasAnyRole("ENSEIGNANT", "ADMIN")

                                                // Admin only: CRUD brut + espace admin
                                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                                .requestMatchers("/api/v1/**").hasRole("ADMIN")

                                                // Enseignant
                                                .requestMatchers("/api/enseignants/**")
                                                .hasAnyRole("ENSEIGNANT", "ADMIN")

                                                // Étudiant
                                                .requestMatchers("/api/etudiants/**").hasAnyRole("ETUDIANT", "ADMIN")

                                                // Messagerie (authentifié)
                                                .requestMatchers("/api/messagerie/**").authenticated()

                                                // Everything else requires auth (safe default)
                                                .anyRequest().authenticated());

                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
                        throws Exception {
                return configuration.getAuthenticationManager();
        }
}
