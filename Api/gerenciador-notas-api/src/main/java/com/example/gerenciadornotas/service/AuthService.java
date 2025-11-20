
package com.example.gerenciadornotas.service;

import com.example.gerenciadornotas.dominio.Usuario;
import com.example.gerenciadornotas.repositorio.UsuarioRepository;
import com.example.gerenciadornotas.web.dto.LoginRequest;
import com.example.gerenciadornotas.web.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;

  public LoginResponse authenticate(LoginRequest req) {
    Authentication auth = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(req.email(), req.senha())
    );
    Usuario usuario = usuarioRepository.findByEmail(req.email()).orElseThrow();
    List<String> roles = auth.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .map(r -> r.replace("ROLE_", ""))
      .collect(Collectors.toList());
    String token = jwtService.generateToken(
      new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getSenhaHash(), auth.getAuthorities()), roles);
    return new LoginResponse(token, usuario.getNomeCompleto(), roles);
  }
}
