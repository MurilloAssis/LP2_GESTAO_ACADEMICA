package br.com.ifsp.service;

import br.com.ifsp.dominio.Curso;
import br.com.ifsp.dominio.Disciplina;
import br.com.ifsp.dto.DisciplinaCreateDTO;
import br.com.ifsp.dto.DisciplinaResponseDTO;
import br.com.ifsp.repositorio.CursoRepository;
import br.com.ifsp.repositorio.DisciplinaRepository;
import br.com.ifsp.service.exceptions.DataIntegrityException;
import br.com.ifsp.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional(readOnly = true)
    public DisciplinaResponseDTO findById(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com ID: " + id));
        return new DisciplinaResponseDTO(disciplina);
    }

    @Transactional(readOnly = true)
    public List<DisciplinaResponseDTO> findAll() {
        return disciplinaRepository.findAll().stream()
                .map(DisciplinaResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public DisciplinaResponseDTO create(DisciplinaCreateDTO dto) {
        if (disciplinaRepository.findByCodigoDisciplina(dto.getCodigoDisciplina()).isPresent()) {
            throw new DataIntegrityException("Erro: Código de disciplina já cadastrado.");
        }

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com ID: " + dto.getCursoId()));

        Disciplina novaDisciplina = new Disciplina();
        novaDisciplina.setNome(dto.getNome());
        novaDisciplina.setCodigoDisciplina(dto.getCodigoDisciplina());
        novaDisciplina.setCargaHoraria(dto.getCargaHoraria());
        novaDisciplina.setCurso(curso);
        novaDisciplina = disciplinaRepository.save(novaDisciplina);

        return new DisciplinaResponseDTO(novaDisciplina);
    }

    @Transactional
    public DisciplinaResponseDTO update(Long id, DisciplinaCreateDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com ID: " + id));

        Optional<Disciplina> disciplinaPorCodigo = disciplinaRepository.findByCodigoDisciplina(dto.getCodigoDisciplina());
        if (disciplinaPorCodigo.isPresent() && !disciplinaPorCodigo.get().getId().equals(id)) {
            throw new DataIntegrityException("Erro: Código de disciplina já pertence a outra disciplina.");
        }

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com ID: " + dto.getCursoId()));

        disciplina.setNome(dto.getNome());
        disciplina.setCodigoDisciplina(dto.getCodigoDisciplina());
        disciplina.setCargaHoraria(dto.getCargaHoraria());
        disciplina.setCurso(curso);
        disciplina = disciplinaRepository.save(disciplina);

        return new DisciplinaResponseDTO(disciplina);
    }

    @Transactional
    public void delete(Long id) {
        if (!disciplinaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Disciplina não encontrada com ID: " + id);
        }
        disciplinaRepository.deleteById(id);
    }
}