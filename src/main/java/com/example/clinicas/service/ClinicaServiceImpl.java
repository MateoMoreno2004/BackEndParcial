package com.example.clinicas.service;

import com.example.clinicas.dto.ClinicaDto;
import com.example.clinicas.entity.Clinica;
import com.example.clinicas.exception.ResourceNotFoundException;
import com.example.clinicas.repository.ClinicaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClinicaServiceImpl implements ClinicaService {

    private final ClinicaRepository repo;
    private final ModelMapper mapper;

    public ClinicaServiceImpl(ClinicaRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<ClinicaDto> findAll() {
        return repo.findAll().stream()
                   .map(c -> mapper.map(c, ClinicaDto.class))
                   .collect(Collectors.toList());
    }

    @Override
    public ClinicaDto findById(Long id) {
        Clinica c = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Clínica no encontrada: " + id));
        return mapper.map(c, ClinicaDto.class);
    }

    @Override
    public ClinicaDto create(ClinicaDto dto) {
        Clinica entity = mapper.map(dto, Clinica.class);
        // si fechaCreacion es null, asignar ahora
        if (entity.getFechaCreacion() == null) {
            entity.setFechaCreacion(java.time.LocalDate.now());
        }
        Clinica saved = repo.save(entity);
        return mapper.map(saved, ClinicaDto.class);
    }

    @Override
    public ClinicaDto update(Long id, ClinicaDto dto) {
        Clinica existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Clínica no encontrada: " + id));
        // map non-null properties from dto to entity (simple strategy: map then set id)
        Clinica mapped = mapper.map(dto, Clinica.class);
        mapped.setIdentificador(existing.getIdentificador());
        // keep fechaCreacion if dto null
        if (mapped.getFechaCreacion() == null) {
            mapped.setFechaCreacion(existing.getFechaCreacion());
        }
        Clinica saved = repo.save(mapped);
        return mapper.map(saved, ClinicaDto.class);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Clínica no encontrada: " + id);
        repo.deleteById(id);
    }
}
