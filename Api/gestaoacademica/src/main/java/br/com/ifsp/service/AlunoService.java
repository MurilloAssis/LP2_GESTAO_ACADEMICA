package br.com.ifsp.service;

import br.com.ifsp.dominio.Aluno;
import br.com.ifsp.dominio.Usuario;
import br.com.ifsp.dominio.enums.TipoUsuario;
import br.com.ifsp.dto.AlunoCreateDTO;
import br.com.ifsp.dto.AlunoResponseDTO;
import br.com.ifsp.dto.AlunoUpdateDTO;
import br.com.ifsp.repositorio.AlunoRepository;
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
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public AlunoResponseDTO findById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com ID: " + id));
        return new AlunoResponseDTO(aluno);
    }

    @Transactional(readOnly = true)
    public List<AlunoResponseDTO> findAll() {
        return alunoRepository.findAll().stream()
                .map(AlunoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public AlunoResponseDTO createAluno(AlunoCreateDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DataIntegrityException("Erro: Email já cadastrado.");
        }
        if (alunoRepository.findByMatricula(dto.getMatricula()).isPresent()) {
            throw new DataIntegrityException("Erro: Matrícula já cadastrada.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenhaHash(passwordEncoder.encode(dto.getSenha()));
        novoUsuario.setTipoUsuario(TipoUsuario.ALUNO);
        novoUsuario.setAtivo(true);
        novoUsuario = usuarioRepository.save(novoUsuario);

        Aluno novoAluno = new Aluno();
        novoAluno.setNomeCompleto(dto.getNomeCompleto());
        novoAluno.setMatricula(dto.getMatricula());
        novoAluno.setUsuario(novoUsuario);
        novoAluno = alunoRepository.save(novoAluno);

        return new AlunoResponseDTO(novoAluno);
    }

    @Transactional
    public AlunoResponseDTO updateAluno(Long id, AlunoUpdateDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com ID: " + id));

        Usuario usuario = aluno.getUsuario();

        Optional<Usuario> usuarioPorEmail = usuarioRepository.findByEmail(dto.getEmail());
        if (usuarioPorEmail.isPresent() && !usuarioPorEmail.get().getId().equals(usuario.getId())) {
            throw new DataIntegrityException("Erro: Email já pertence a outro usuário.");
        }

        aluno.setNomeCompleto(dto.getNomeCompleto());
        usuario.setEmail(dto.getEmail());

        usuarioRepository.save(usuario);
        aluno = alunoRepository.save(aluno);

        return new AlunoResponseDTO(aluno);
    }

    @Transactional
    public void deleteAluno(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com ID: " + id));

        usuarioRepository.delete(aluno.getUsuario());
    }
}