package ru.itmo.gateway.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    @SneakyThrows
    public SecurityWebFilterChain getSecurityFilterChain(final ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .authorizeExchange(exchanges -> exchanges.anyExchange().permitAll())
                .redirectToHttps(redirect -> redirect
                        .httpsRedirectWhen(e -> true) // Все запросы перенаправлять на HTTPS
                )
                .build();
    }
}
