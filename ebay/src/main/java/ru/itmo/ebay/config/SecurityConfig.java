package ru.itmo.ebay.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    @SneakyThrows
    public SecurityFilterChain getSecurityFilterChain(final HttpSecurity httpSecurity, CorsConfigurationSource corsConfigurationSource) {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(configurer ->
                        configurer.anyRequest().permitAll()
                ).requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .build();
    }
}
