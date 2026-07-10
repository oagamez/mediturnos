package com.mediturnos.service.impl.appointment;

import com.mediturnos.dto.appointment.response.CitaResponse;
import com.mediturnos.entity.appointment.Cita;
import com.mediturnos.enums.EstadoCita;
import com.mediturnos.exception.BusinessException;
import com.mediturnos.exception.ResourceNotFoundException;
import com.mediturnos.mapper.appointment.CitaMapper;
import com.mediturnos.repository.appointment.CitaRepository;
import com.mediturnos.service.interfaces.appointment.RecepcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecepcionServiceImpl implements RecepcionService {

    private final CitaRepository repository;
    private final CitaMapper mapper;

    @Override
    public List<CitaResponse> obtenerCitasDelDia(LocalDate fecha) {

        return repository.findByFechaAndActivoTrue(fecha)
                .stream()
                .map(mapper::toResponse)
                .toList();

    }
}
