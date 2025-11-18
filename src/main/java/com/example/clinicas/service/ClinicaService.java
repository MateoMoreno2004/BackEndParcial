package com.example.clinicas.service;

import com.example.clinicas.dto.ClinicaDto;
import java.util.List;

public interface ClinicaService {
    List<ClinicaDto> findAll();
    ClinicaDto findById(Long id);
    ClinicaDto create(ClinicaDto dto);
    ClinicaDto update(Long id, ClinicaDto dto);
    void delete(Long id);
}
