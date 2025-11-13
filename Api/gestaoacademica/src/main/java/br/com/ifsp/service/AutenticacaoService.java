package br.com.ifsp.service;

import br.com.ifsp.dominio.Usuario;
import br.com.ifsp.dominio.enums.TipoUsuario;
import br.com.ifsp.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));


        return new User(
                usuario.getEmail(),
                usuario.getSenhaHash(),
                usuario.isAtivo(),
                true,
                true,
                true,
                getAuthorities(usuario.getTipoUsuario())
        );
    }


    private Collection<? extends GrantedAuthority> getAuthorities(TipoUsuario tipoUsuario) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + tipoUsuario.name()));
    }
}