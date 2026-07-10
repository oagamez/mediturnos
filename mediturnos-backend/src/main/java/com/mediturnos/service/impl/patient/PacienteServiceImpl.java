package com.mediturnos.service.impl.patient;

import com.mediturnos.dto.patient.request.PacienteRequest;
import com.mediturnos.dto.patient.response.PacienteResponse;
import com.mediturnos.entity.patient.Eps;
import com.mediturnos.entity.patient.Paciente;
import com.mediturnos.exception.BusinessException;
import com.mediturnos.exception.ResourceNotFoundException;
import com.mediturnos.mapper.patient.PacienteMapper;
import com.mediturnos.repository.patient.PacienteRepository;
import com.mediturnos.service.interfaces.patient.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    @Override
    public PacienteResponse crear(PacienteRequest request) {

        if (pacienteRepository.existsByDocumento(request.getDocumento())) {
            throw new BusinessException("Ya existe un paciente con ese documento.");
        }

        if (pacienteRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Ya existe un paciente con ese correo.");
        }

        Paciente paciente = pacienteMapper.toEntity(request);
paciente.setEps(request.getEps());

        Paciente guardado = pacienteRepository.save(paciente);

        return pacienteMapper.toResponse(guardado);
    }

    @Override
    public PacienteResponse actualizar(Long id, PacienteRequest request) {

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado."));

        if (!paciente.getDocumento().equals(request.getDocumento())
                && pacienteRepository.existsByDocumento(request.getDocumento())) {

            throw new BusinessException("Ya existe un paciente con ese documento.");
        }

        if (!paciente.getEmail().equals(request.getEmail())
                && pacienteRepository.existsByEmail(request.getEmail())) {

            throw new BusinessException("Ya existe un paciente con ese correo.");
        }

        pacienteMapper.updateEntity(request, paciente);
        paciente.setEps(request.getEps());

        Paciente actualizado = pacienteRepository.save(paciente);

        return pacienteMapper.toResponse(actualizado);
    }

    @Override
    public PacienteResponse obtenerPorId(Long id) {

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado."));

        return pacienteMapper.toResponse(paciente);
    }

    @Override
    public List<PacienteResponse> obtenerTodos() {

        return pacienteRepository.findByActivoTrue()
                .stream()
                .map(pacienteMapper::toResponse)
                .toList();
    }

    @Override
    public void eliminar(Long id) {

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado."));

        paciente.setActivo(false);

        pacienteRepository.save(paciente);
    }

}