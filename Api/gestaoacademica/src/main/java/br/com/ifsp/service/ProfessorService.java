package br.com.ifsp.service;

import br.com.ifsp.dominio.Professor;
import br.com.ifsp.dominio.Usuario;
import br.com.ifsp.dominio.enums.TipoUsuario;
import br.com.ifsp.dto.ProfessorCreateDTO;
import br.com.ifsp.dto.ProfessorResponseDTO;
import br.com.ifsp.dto.ProfessorUpdateDTO;
import br.com.ifsp.repositorio.ProfessorRepository;
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
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public ProfessorResponseDTO findById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com ID: " + id));
        return new ProfessorResponseDTO(professor);
    }

    @Transactional(readOnly = true)
    public List<ProfessorResponseDTO> findAll() {
        return professorRepository.findAll().stream()
                .map(ProfessorResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProfessorResponseDTO createProfessor(ProfessorCreateDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DataIntegrityException("Erro: Email já cadastrado.");
        }
        if (professorRepository.findByMatricula(dto.getMatricula()).isPresent()) {
            throw new DataIntegrityException("Erro: Matrícula já cadastrada.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenhaHash(passwordEncoder.encode(dto.getSenha()));
        novoUsuario.setTipoUsuario(TipoUsuario.PROFESSOR);
        novoUsuario.setAtivo(true);
        novoUsuario = usuarioRepository.save(novoUsuario);

        Professor novoProfessor = new Professor();
        novoProfessor.setNomeCompleto(dto.getNomeCompleto());
        novoProfessor.setMatricula(dto.getMatricula());
        novoProfessor.setUsuario(novoUsuario);
        novoProfessor = professorRepository.save(novoProfessor);

        return new ProfessorResponseDTO(novoProfessor);
    }

    @Transactional
    public ProfessorResponseDTO updateProfessor(Long id, ProfessorUpdateDTO dto) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com ID: " + id));

        Usuario usuario = professor.getUsuario();

        Optional<Usuario> usuarioPorEmail = usuarioRepository.findByEmail(dto.getEmail());
        if (usuarioPorEmail.isPresent() && !usuarioPorEmail.get().getId().equals(usuario.getId())) {
            throw new DataIntegrityException("Erro: Email já pertence a outro usuário.");
        }

        Optional<Professor> professorPorMatricula = professorRepository.findByMatricula(dto.getMatricula());
        if (professorPorMatricula.isPresent() && !professorPorMatricula.get().getId().equals(professor.getId())) {
            throw new DataIntegrityException("Erro: Matrícula já pertence a outro professor.");
        }

        professor.setNomeCompleto(dto.getNomeCompleto());
        professor.setMatricula(dto.getMatricula());
        professor.setDepartamento(dto.getDepartamento());
        usuario.setEmail(dto.getEmail());

        usuarioRepository.save(usuario);
        professor = professorRepository.save(professor);

        return new ProfessorResponseDTO(professor);
    }

    @Transactional
    public void deleteProfessor(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com ID: " + id));

        usuarioRepository.delete(professor.getUsuario());
    }
}