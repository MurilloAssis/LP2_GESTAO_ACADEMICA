package br.com.ifsp.service;

import br.com.ifsp.dominio.Disciplina;
import br.com.ifsp.dominio.PeriodoLetivo;
import br.com.ifsp.dominio.Professor;
import br.com.ifsp.dominio.Turma;
import br.com.ifsp.dto.TurmaCreateDTO;
import br.com.ifsp.dto.TurmaResponseDTO;
import br.com.ifsp.repositorio.DisciplinaRepository;
import br.com.ifsp.repositorio.PeriodoLetivoRepository;
import br.com.ifsp.repositorio.ProfessorRepository;
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
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private PeriodoLetivoRepository periodoLetivoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional(readOnly = true)
    public TurmaResponseDTO findById(Long id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com ID: " + id));
        return new TurmaResponseDTO(turma);
    }

    @Transactional(readOnly = true)
    public List<TurmaResponseDTO> findAll() {
        return turmaRepository.findAll().stream()
                .map(TurmaResponseDTO::new)
                .collect(Collectors.toList());
    }

    private void validarTurmaUnica(Long disciplinaId, Long periodoId, String codigo, Long idExcluido) {
        Optional<Turma> turmaExistente = turmaRepository.findByDisciplinaIdAndPeriodoLetivoIdAndCodigoTurma(
                disciplinaId, periodoId, codigo);

        if (turmaExistente.isPresent() && (idExcluido == null || !turmaExistente.get().getId().equals(idExcluido))) {
            throw new DataIntegrityException("Erro: Já existe uma turma com esta disciplina, período e código.");
        }
    }

    private Turma buscarDependencias(TurmaCreateDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com ID: " + dto.getDisciplinaId()));

        PeriodoLetivo periodo = periodoLetivoRepository.findById(dto.getPeriodoLetivoId())
                .orElseThrow(() -> new ResourceNotFoundException("Período Letivo não encontrado com ID: " + dto.getPeriodoLetivoId()));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com ID: " + dto.getProfessorId()));

        Turma turma = new Turma();
        turma.setDisciplina(disciplina);
        turma.setPeriodoLetivo(periodo);
        turma.setProfessor(professor);
        turma.setCodigoTurma(dto.getCodigoTurma());
        return turma;
    }

    @Transactional
    public TurmaResponseDTO create(TurmaCreateDTO dto) {
        validarTurmaUnica(dto.getDisciplinaId(), dto.getPeriodoLetivoId(), dto.getCodigoTurma(), null);
        Turma novaTurma = buscarDependencias(dto);
        novaTurma = turmaRepository.save(novaTurma);
        return new TurmaResponseDTO(novaTurma);
    }

    @Transactional
    public TurmaResponseDTO update(Long id, TurmaCreateDTO dto) {
        validarTurmaUnica(dto.getDisciplinaId(), dto.getPeriodoLetivoId(), dto.getCodigoTurma(), id);
        
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com ID: " + id));

        Turma dadosAtualizados = buscarDependencias(dto);
        
        turma.setDisciplina(dadosAtualizados.getDisciplina());
        turma.setPeriodoLetivo(dadosAtualizados.getPeriodoLetivo());
        turma.setProfessor(dadosAtualizados.getProfessor());
        turma.setCodigoTurma(dadosAtualizados.getCodigoTurma());

        turma = turmaRepository.save(turma);
        return new TurmaResponseDTO(turma);
    }

    @Transactional
    public void delete(Long id) {
        if (!turmaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Turma não encontrada com ID: " + id);
        }
        turmaRepository.deleteById(id);
    }
}