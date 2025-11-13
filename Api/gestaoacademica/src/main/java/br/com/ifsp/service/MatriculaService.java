package br.com.ifsp.service;

import br.com.ifsp.dominio.Aluno;
import br.com.ifsp.dominio.Matricula;
import br.com.ifsp.dominio.Turma;
import br.com.ifsp.dto.MatriculaCreateDTO;
import br.com.ifsp.dto.MatriculaResponseDTO;
import br.com.ifsp.repositorio.AlunoRepository;
import br.com.ifsp.repositorio.MatriculaRepository;
import br.com.ifsp.repositorio.TurmaRepository;
import br.com.ifsp.service.exceptions.DataIntegrityException;
import br.com.ifsp.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Transactional(readOnly = true)
    public MatriculaResponseDTO findById(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula não encontrada com ID: " + id));
        return new MatriculaResponseDTO(matricula);
    }

    @Transactional(readOnly = true)
    public List<MatriculaResponseDTO> findAll() {
        return matriculaRepository.findAll().stream()
                .map(MatriculaResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public MatriculaResponseDTO create(MatriculaCreateDTO dto) {
        if (matriculaRepository.findByAlunoIdAndTurmaId(dto.getAlunoId(), dto.getTurmaId()).isPresent()) {
            throw new DataIntegrityException("Erro: Aluno já matriculado nesta turma.");
        }

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com ID: " + dto.getAlunoId()));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com ID: " + dto.getTurmaId()));

        Matricula novaMatricula = new Matricula();
        novaMatricula.setAluno(aluno);
        novaMatricula.setTurma(turma);

        novaMatricula = matriculaRepository.save(novaMatricula);

        return new MatriculaResponseDTO(novaMatricula);
    }

    @Transactional
    public void delete(Long id) {
        if (!matriculaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Matrícula não encontrada com ID: " + id);
        }
        matriculaRepository.deleteById(id);
    }
}