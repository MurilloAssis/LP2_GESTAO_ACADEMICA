package br.com.ifsp.service;

import br.com.ifsp.dominio.Avaliacao;
import br.com.ifsp.dominio.Turma;
import br.com.ifsp.dto.AvaliacaoCreateDTO;
import br.com.ifsp.dto.AvaliacaoResponseDTO;
import br.com.ifsp.repositorio.AvaliacaoRepository;
import br.com.ifsp.repositorio.TurmaRepository;
import br.com.ifsp.service.exceptions.DataIntegrityException;
import br.com.ifsp.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Transactional(readOnly = true)
    public AvaliacaoResponseDTO findById(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada com ID: " + id));
        return new AvaliacaoResponseDTO(avaliacao);
    }

    @Transactional(readOnly = true)
    public List<AvaliacaoResponseDTO> findAllByTurma(Long turmaId) {
        if (!turmaRepository.existsById(turmaId)) {
            throw new ResourceNotFoundException("Turma não encontrada com ID: " + turmaId);
        }
        return avaliacaoRepository.findAllByTurmaId(turmaId).stream()
                .map(AvaliacaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    private void validarTitulo(Long turmaId, String titulo, Long idExcluido) {
        Optional<Avaliacao> avaliacaoExistente = avaliacaoRepository.findByTurmaIdAndTitulo(turmaId, titulo);
        
        if (avaliacaoExistente.isPresent() && (idExcluido == null || !avaliacaoExistente.get().getId().equals(idExcluido))) {
            throw new DataIntegrityException("Erro: Já existe uma avaliação com este título nesta turma.");
        }
    }

    @Transactional
    public AvaliacaoResponseDTO create(AvaliacaoCreateDTO dto) {
        validarTitulo(dto.getTurmaId(), dto.getTitulo(), null);

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com ID: " + dto.getTurmaId()));

        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setTurma(turma);
        novaAvaliacao.setTitulo(dto.getTitulo());
        novaAvaliacao.setPeso(dto.getPeso());
        novaAvaliacao.setDataAvaliacao(dto.getDataAvaliacao());
        
        novaAvaliacao = avaliacaoRepository.save(novaAvaliacao);
        return new AvaliacaoResponseDTO(novaAvaliacao);
    }

    @Transactional
    public AvaliacaoResponseDTO update(Long id, AvaliacaoCreateDTO dto) {
        validarTitulo(dto.getTurmaId(), dto.getTitulo(), id);

        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada com ID: " + id));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com ID: " + dto.getTurmaId()));

        avaliacao.setTurma(turma);
        avaliacao.setTitulo(dto.getTitulo());
        avaliacao.setPeso(dto.getPeso());
        avaliacao.setDataAvaliacao(dto.getDataAvaliacao());
        
        avaliacao = avaliacaoRepository.save(avaliacao);
        return new AvaliacaoResponseDTO(avaliacao);
    }

    @Transactional
    public void delete(Long id) {
        if (!avaliacaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Avaliação não encontrada com ID: " + id);
        }
        avaliacaoRepository.deleteById(id);
    }
}