package br.com.ifsp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Define o Bean para criptografar senhas.
     * Usamos o BCrypt, que é o padrão recomendado.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Define a cadeia de filtros de segurança e as regras de autorização.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita o CSRF (útil para APIs stateless)
                .csrf(AbstractHttpConfigurer::disable)

                // Define as regras de autorização para os requests
                .authorizeHttpRequests(authz -> authz
                        // Permite acesso público ao endpoint raiz (seu HelloController)
                        .requestMatchers("/").permitAll()

                        // --- REGRAS DE ACESSO (Exemplo inicial) ---
                        // Aqui definimos o controle de permissão 
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/professor/**").hasRole("PROFESSOR")
                        .requestMatchers("/api/aluno/**").hasRole("ALUNO")

                        // Qualquer outro request exige autenticação
                        .anyRequest().authenticated()
                )

                // Habilita a autenticação HTTP Basic (bom para testes iniciais via Postman)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}