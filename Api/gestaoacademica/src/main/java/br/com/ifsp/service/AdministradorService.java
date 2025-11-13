package br.com.ifsp.service;

import br.com.ifsp.dominio.Administrador;
import br.com.ifsp.dominio.Usuario;
import br.com.ifsp.dominio.enums.TipoUsuario;
import br.com.ifsp.dto.AdministradorCreateDTO;
import br.com.ifsp.dto.AdministradorResponseDTO;
import br.com.ifsp.dto.AdministradorUpdateDTO;
import br.com.ifsp.repositorio.AdministradorRepository;
import br.com.ifsp.repositorio.UsuarioRepository;
import br.com.ifsp.service.exceptions.DataIntegrityException;
import br.com.ifsp.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository adminRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public AdministradorResponseDTO findById(Long id) {
        Administrador admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador não encontrado com ID: " + id));
        return new AdministradorResponseDTO(admin);
    }

    @Transactional(readOnly = true)
    public List<AdministradorResponseDTO> findAll() {
        return adminRepository.findAll().stream()
                .map(AdministradorResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public AdministradorResponseDTO createAdministrador(AdministradorCreateDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DataIntegrityException("Erro: Email já cadastrado.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenhaHash(passwordEncoder.encode(dto.getSenha()));
        novoUsuario.setTipoUsuario(TipoUsuario.ADMIN);
        novoUsuario.setAtivo(true);
        novoUsuario = usuarioRepository.save(novoUsuario);

        Administrador novoAdmin = new Administrador();
        novoAdmin.setNomeCompleto(dto.getNomeCompleto());
        novoAdmin.setCargo(dto.getCargo());
        novoAdmin.setUsuario(novoUsuario);
        novoAdmin = adminRepository.save(novoAdmin);

        return new AdministradorResponseDTO(novoAdmin);
    }

    @Transactional
    public AdministradorResponseDTO updateAdministrador(Long id, AdministradorUpdateDTO dto) {
        Administrador admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador não encontrado com ID: " + id));

        Usuario usuario = admin.getUsuario();

        Optional<Usuario> usuarioPorEmail = usuarioRepository.findByEmail(dto.getEmail());
        if (usuarioPorEmail.isPresent() && !usuarioPorEmail.get().getId().equals(usuario.getId())) {
            throw new DataIntegrityException("Erro: Email já pertence a outro usuário.");
        }

        admin.setNomeCompleto(dto.getNomeCompleto());
        admin.setCargo(dto.getCargo());
        usuario.setEmail(dto.getEmail());

        usuarioRepository.save(usuario);
        admin = adminRepository.save(admin);

        return new AdministradorResponseDTO(admin);
    }

    @Transactional
    public void deleteAdministrador(Long id) {
        Administrador admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador não encontrado com ID: " + id));

        usuarioRepository.delete(admin.getUsuario());
    }
}