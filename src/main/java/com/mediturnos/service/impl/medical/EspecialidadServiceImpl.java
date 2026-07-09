package com.mediturnos.service.impl.medical;

import com.mediturnos.dto.medical.request.EspecialidadRequest;
import com.mediturnos.dto.medical.response.EspecialidadResponse;
import com.mediturnos.entity.medical.Especialidad;
import com.mediturnos.exception.BusinessException;
import com.mediturnos.exception.ResourceNotFoundException;
import com.mediturnos.mapper.medical.EspecialidadMapper;
import com.mediturnos.repository.medical.EspecialidadRepository;
import com.mediturnos.service.interfaces.medical.EspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository repository;
    private final EspecialidadMapper mapper;

    @Override
    public EspecialidadResponse crear(EspecialidadRequest request) {

        if (repository.existsByNombre(request.getNombre())) {
            throw new BusinessException("La especialidad ya existe.");
        }

        Especialidad especialidad = mapper.toEntity(request);

        Especialidad guardada = repository.save(especialidad);

        return mapper.toResponse(guardada);
    }

    @Override
    public EspecialidadResponse actualizar(Long id, EspecialidadRequest request) {

        Especialidad especialidad = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada."));

        if (!especialidad.getNombre().equals(request.getNombre())
                && repository.existsByNombre(request.getNombre())) {
            throw new BusinessException("La especialidad ya existe.");
        }

        mapper.updateEntity(request, especialidad);

        Especialidad actualizada = repository.save(especialidad);

        return mapper.toResponse(actualizada);
    }

    @Override
    public EspecialidadResponse obtenerPorId(Long id) {

        Especialidad especialidad = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada."));

        return mapper.toResponse(especialidad);
    }

    @Override
    public List<EspecialidadResponse> obtenerTodos() {

        return repository.findByActivoTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void eliminar(Long id) {

        Especialidad especialidad = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada."));

        especialidad.setActivo(false);

        repository.save(especialidad);
    }
}