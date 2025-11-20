
package com.example.gerenciadornotas.ui;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class ApiClient {
  private final WebClient web;
  private volatile String jwt;

  public ApiClient() {
    this.web = WebClient.builder().baseUrl("http://localhost:8080").build();
  }

  public Mono<Void> login(String email, String password) {
    return web.post().uri("/auth/login")
      .bodyValue(Map.of("email", email, "password", password))
      .retrieve().bodyToMono(Map.class)
      .doOnNext(map -> this.jwt = (String) map.get("token"))
      .then();
  }

  public Mono<Map> salvaNota(long enrollmentId, long assignmentId, BigDecimal value) {
    return web.post().uri("/notas")
      .headers(h -> h.setBearerAuth(jwt))
      .bodyValue(Map.of("enrollmentId", enrollmentId, "assignmentId", assignmentId, "value", value))
      .retrieve().bodyToMono(Map.class);
  }

  public Mono<BigDecimal> media(long enrollmentId) {
    return web.get().uri("/notas/media/" + enrollmentId)
      .headers(h -> h.setBearerAuth(jwt))
      .retrieve().bodyToMono(BigDecimal.class);
  }
}
