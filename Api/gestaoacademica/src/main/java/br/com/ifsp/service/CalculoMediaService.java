package br.com.ifsp.service;

import br.com.ifsp.dominio.Avaliacao;
import br.com.ifsp.dominio.Matricula;
import br.com.ifsp.dominio.Nota;
import br.com.ifsp.dominio.enums.StatusMatricula;
import br.com.ifsp.dto.MatriculaResponseDTO;
import br.com.ifsp.repositorio.AvaliacaoRepository;
import br.com.ifsp.repositorio.MatriculaRepository;
import br.com.ifsp.repositorio.NotaRepository;
import br.com.ifsp.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CalculoMediaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private NotaRepository notaRepository;

    // A média de aprovação (pode ser movida para application.properties)
    private static final BigDecimal MEDIA_APROVACAO = new BigDecimal("7.0");

    @Transactional
    public MatriculaResponseDTO calcularMediaFinal(Long matriculaId) {
        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula não encontrada com ID: " + matriculaId));

        Long turmaId = matricula.getTurma().getId();

        List<Avaliacao> avaliacoesDaTurma = avaliacaoRepository.findAllByTurmaId(turmaId);
        List<Nota> notasDoAluno = notaRepository.findAllByMatriculaId(matriculaId);

        BigDecimal somaNotasPonderadas = BigDecimal.ZERO;
        BigDecimal somaPesos = BigDecimal.ZERO;

        for (Avaliacao avaliacao : avaliacoesDaTurma) {
            BigDecimal peso = avaliacao.getPeso();
            somaPesos = somaPesos.add(peso);

            BigDecimal notaEncontrada = notasDoAluno.stream()
                    .filter(nota -> nota.getAvaliacao().getId().equals(avaliacao.getId()))
                    .map(Nota::getValorNota)
                    .findFirst()
                    .orElse(BigDecimal.ZERO); // Se não lançou nota, considera 0

            somaNotasPonderadas = somaNotasPonderadas.add(notaEncontrada.multiply(peso));
        }

        BigDecimal mediaFinal;
        if (somaPesos.compareTo(BigDecimal.ZERO) == 0) {
            mediaFinal = BigDecimal.ZERO;
        } else {
            mediaFinal = somaNotasPonderadas.divide(somaPesos, 2, RoundingMode.HALF_UP);
        }

        // Salva a média calculada na matrícula
        matricula.setMediaFinal(mediaFinal);

        // Atualiza o status do aluno (simples, pode ser mais complexo com frequência)
        if (mediaFinal.compareTo(MEDIA_APROVACAO) >= 0) {
            matricula.setStatus(StatusMatricula.APROVADO);
        } else {
            matricula.setStatus(StatusMatricula.REPROVADO);
        }

        Matricula matriculaSalva = matriculaRepository.save(matricula);
        return new MatriculaResponseDTO(matriculaSalva);
    }
}