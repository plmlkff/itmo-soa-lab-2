package ru.itmo.product.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    @SneakyThrows
    public SecurityFilterChain getSecurityFilterChain(final HttpSecurity httpSecurity) {
        return httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(configurer ->
                        configurer.anyRequest().permitAll()
                ).requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .build();
    }
}
