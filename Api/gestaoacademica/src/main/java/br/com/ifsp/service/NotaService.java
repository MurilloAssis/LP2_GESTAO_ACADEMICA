package br.com.ifsp.service;

import br.com.ifsp.dominio.Avaliacao;
import br.com.ifsp.dominio.Matricula;
import br.com.ifsp.dominio.Nota;
import br.com.ifsp.dto.NotaCreateDTO;
import br.com.ifsp.dto.NotaResponseDTO;
import br.com.ifsp.repositorio.AvaliacaoRepository;
import br.com.ifsp.repositorio.MatriculaRepository;
import br.com.ifsp.repositorio.NotaRepository;
import br.com.ifsp.service.exceptions.DataIntegrityException;
import br.com.ifsp.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Transactional(readOnly = true)
    public List<NotaResponseDTO> findByAvaliacao(Long avaliacaoId) {
        if (!avaliacaoRepository.existsById(avaliacaoId)) {
            throw new ResourceNotFoundException("Avaliação não encontrada com ID: " + avaliacaoId);
        }
        return notaRepository.findAllByAvaliacaoId(avaliacaoId).stream()
                .map(NotaResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NotaResponseDTO> findByMatricula(Long matriculaId) {
        if (!matriculaRepository.existsById(matriculaId)) {
            throw new ResourceNotFoundException("Matrícula não encontrada com ID: " + matriculaId);
        }
        return notaRepository.findAllByMatriculaId(matriculaId).stream()
                .map(NotaResponseDTO::new)
                .collect(Collectors.toList());
    }

    private Nota validarLancamento(Long matriculaId, Long avaliacaoId) {
        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula não encontrada com ID: " + matriculaId));

        Avaliacao avaliacao = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada com ID: " + avaliacaoId));

        if (!Objects.equals(matricula.getTurma().getId(), avaliacao.getTurma().getId())) {
            throw new DataIntegrityException("Erro: A Matrícula e a Avaliação não pertencem à mesma Turma.");
        }

        Nota nota = new Nota();
        nota.setMatricula(matricula);
        nota.setAvaliacao(avaliacao);
        return nota;
    }

    @Transactional
    public NotaResponseDTO create(NotaCreateDTO dto) {
        if (notaRepository.findByMatriculaIdAndAvaliacaoId(dto.getMatriculaId(), dto.getAvaliacaoId()).isPresent()) {
            throw new DataIntegrityException("Erro: Este aluno já possui nota para esta avaliação.");
        }

        Nota novaNota = validarLancamento(dto.getMatriculaId(), dto.getAvaliacaoId());
        novaNota.setValorNota(dto.getValorNota());
        novaNota = notaRepository.save(novaNota);

        return new NotaResponseDTO(novaNota);
    }

    @Transactional
    public NotaResponseDTO update(Long id, NotaCreateDTO dto) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota não encontrada com ID: " + id));
        
        validarLancamento(dto.getMatriculaId(), dto.getAvaliacaoId());
        
        nota.setValorNota(dto.getValorNota());
        nota = notaRepository.save(nota);
        return new NotaResponseDTO(nota);
    }

    @Transactional
    public void delete(Long id) {
        if (!notaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nota não encontrada com ID: " + id);
        }
        notaRepository.deleteById(id);
    }
}