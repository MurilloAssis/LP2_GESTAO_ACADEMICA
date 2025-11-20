
package com.example.gerenciadornotas.config;

import com.example.gerenciadornotas.dominio.Usuario;
import com.example.gerenciadornotas.repositorio.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario u = usuarioRepository.findByEmail(username)
      .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    List<GrantedAuthority> auth = u.getRoles().stream()
      .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
      .collect(Collectors.toList());
    return new org.springframework.security.core.userdetails.User(
      u.getEmail(), u.getSenhaHash(), auth
    );
  }
}
