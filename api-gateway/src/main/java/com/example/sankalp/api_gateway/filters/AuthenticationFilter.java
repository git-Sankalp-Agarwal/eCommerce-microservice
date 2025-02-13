package com.example.sankalp.api_gateway.filters;

import com.example.sankalp.api_gateway.services.JwtService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtService jwtService;

    public AuthenticationFilter(JwtService jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            if(!config.enabled) return chain.filter(exchange);

            String authorizationHeader = exchange.getRequest()
                                                 .getHeaders()
                                                 .getFirst("Authorization");
            if (authorizationHeader == null) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            String token = authorizationHeader.split("Bearer ")[1];

            Long userId = jwtService.getUserIdFromToken(token);

            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                                                       .header("X-User-Id", userId.toString())
                                                       .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        });
    }

    @Data
    public static class Config {
        private boolean enabled;
    }

}
