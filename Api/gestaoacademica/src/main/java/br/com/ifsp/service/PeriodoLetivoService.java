package br.com.ifsp.service;

import br.com.ifsp.dominio.PeriodoLetivo;
import br.com.ifsp.dto.PeriodoLetivoCreateDTO;
import br.com.ifsp.dto.PeriodoLetivoResponseDTO;
import br.com.ifsp.repositorio.PeriodoLetivoRepository;
import br.com.ifsp.service.exceptions.DataIntegrityException;
import br.com.ifsp.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeriodoLetivoService {

    @Autowired
    private PeriodoLetivoRepository periodoLetivoRepository;

    @Transactional(readOnly = true)
    public PeriodoLetivoResponseDTO findById(Long id) {
        PeriodoLetivo periodo = periodoLetivoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Período Letivo não encontrado com ID: " + id));
        return new PeriodoLetivoResponseDTO(periodo);
    }

    @Transactional(readOnly = true)
    public List<PeriodoLetivoResponseDTO> findAll() {
        return periodoLetivoRepository.findAll().stream()
                .map(PeriodoLetivoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PeriodoLetivoResponseDTO create(PeriodoLetivoCreateDTO dto) {
        if (periodoLetivoRepository.findByIdentificador(dto.getIdentificador()).isPresent()) {
            throw new DataIntegrityException("Erro: Identificador já cadastrado.");
        }

        PeriodoLetivo novoPeriodo = new PeriodoLetivo();
        novoPeriodo.setIdentificador(dto.getIdentificador());
        novoPeriodo.setDataInicio(dto.getDataInicio());
        novoPeriodo.setDataFim(dto.getDataFim());
        novoPeriodo = periodoLetivoRepository.save(novoPeriodo);

        return new PeriodoLetivoResponseDTO(novoPeriodo);
    }

    @Transactional
    public PeriodoLetivoResponseDTO update(Long id, PeriodoLetivoCreateDTO dto) {
        PeriodoLetivo periodo = periodoLetivoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Período Letivo não encontrado com ID: " + id));

        Optional<PeriodoLetivo> periodoPorId = periodoLetivoRepository.findByIdentificador(dto.getIdentificador());
        if (periodoPorId.isPresent() && !periodoPorId.get().getId().equals(id)) {
            throw new DataIntegrityException("Erro: Identificador já pertence a outro período.");
        }

        periodo.setIdentificador(dto.getIdentificador());
        periodo.setDataInicio(dto.getDataInicio());
        periodo.setDataFim(dto.getDataFim());
        periodo = periodoLetivoRepository.save(periodo);

        return new PeriodoLetivoResponseDTO(periodo);
    }

    @Transactional
    public void delete(Long id) {
        if (!periodoLetivoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Período Letivo não encontrado com ID: " + id);
        }
        periodoLetivoRepository.deleteById(id);
    }
}