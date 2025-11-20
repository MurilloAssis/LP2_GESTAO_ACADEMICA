
package com.example.gerenciadornotas.web;

import com.example.gerenciadornotas.service.AuthService;
import com.example.gerenciadornotas.web.dto.LoginRequest;
import com.example.gerenciadornotas.web.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
    return ResponseEntity.ok(authService.authenticate(req));
  }
}
