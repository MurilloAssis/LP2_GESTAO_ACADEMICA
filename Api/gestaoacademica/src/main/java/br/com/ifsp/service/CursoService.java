package br.com.ifsp.service;

import br.com.ifsp.dominio.Curso;
import br.com.ifsp.dto.CursoCreateDTO;
import br.com.ifsp.dto.CursoResponseDTO;
import br.com.ifsp.repositorio.CursoRepository;
import br.com.ifsp.service.exceptions.DataIntegrityException;
import br.com.ifsp.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional(readOnly = true)
    public CursoResponseDTO findById(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com ID: " + id));
        return new CursoResponseDTO(curso);
    }

    @Transactional(readOnly = true)
    public List<CursoResponseDTO> findAll() {
        return cursoRepository.findAll().stream()
                .map(CursoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public CursoResponseDTO create(CursoCreateDTO dto) {
        if (cursoRepository.findByCodigoCurso(dto.getCodigoCurso()).isPresent()) {
            throw new DataIntegrityException("Erro: Código de curso já cadastrado.");
        }

        Curso novoCurso = new Curso();
        novoCurso.setNome(dto.getNome());
        novoCurso.setCodigoCurso(dto.getCodigoCurso());
        novoCurso = cursoRepository.save(novoCurso);

        return new CursoResponseDTO(novoCurso);
    }

    @Transactional
    public CursoResponseDTO update(Long id, CursoCreateDTO dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com ID: " + id));

        Optional<Curso> cursoPorCodigo = cursoRepository.findByCodigoCurso(dto.getCodigoCurso());
        if (cursoPorCodigo.isPresent() && !cursoPorCodigo.get().getId().equals(id)) {
            throw new DataIntegrityException("Erro: Código de curso já pertence a outro curso.");
        }

        curso.setNome(dto.getNome());
        curso.setCodigoCurso(dto.getCodigoCurso());
        curso = cursoRepository.save(curso);

        return new CursoResponseDTO(curso);
    }

    @Transactional
    public void delete(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com ID: " + id));
        
        cursoRepository.delete(curso);
    }
}