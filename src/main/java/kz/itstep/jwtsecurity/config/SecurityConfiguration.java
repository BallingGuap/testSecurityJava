package kz.itstep.jwtsecurity.config;

import kz.itstep.jwtsecurity.config.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->
                        auth.requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers("/test/path/**").authenticated()
                                .requestMatchers("/api/v1/book").permitAll()
                                .requestMatchers("/api/v1/book/getBooksByDetail/**").permitAll()
                                .requestMatchers("/api/v1/book/createBook").authenticated()
                                .requestMatchers("/api/v1/book/updateBook").authenticated()
                                .requestMatchers("/api/v1/book/deleteBook").authenticated()
                                .requestMatchers("/api/v1/user/getAdmins").authenticated()
                                .requestMatchers("/api/v1/user/deleteUser").authenticated()
                                )
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
