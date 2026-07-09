package com.mediturnos.service.impl.patient;

import com.mediturnos.dto.patient.request.EpsRequest;
import com.mediturnos.dto.patient.response.EpsResponse;
import com.mediturnos.entity.patient.Eps;
import com.mediturnos.exception.BusinessException;
import com.mediturnos.exception.ResourceNotFoundException;
import com.mediturnos.mapper.patient.EpsMapper;
import com.mediturnos.repository.patient.EpsRepository;
import com.mediturnos.service.interfaces.patient.EpsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpsServiceImpl implements EpsService {

    private final EpsRepository repository;
    private final EpsMapper mapper;

    @Override
    public EpsResponse crear(EpsRequest request) {

        if (repository.existsByNombre(request.getNombre())) {
            throw new BusinessException("La EPS ya existe.");
        }

        Eps eps = mapper.toEntity(request);

        Eps guardada = repository.save(eps);

        return mapper.toResponse(guardada);
    }

    @Override
    public EpsResponse actualizar(Long id, EpsRequest request) {

        Eps eps = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EPS no encontrada."));

        if (!eps.getNombre().equals(request.getNombre())
                && repository.existsByNombre(request.getNombre())) {

            throw new BusinessException("La EPS ya existe.");
        }

        mapper.updateEntity(request, eps);

        Eps actualizada = repository.save(eps);

        return mapper.toResponse(actualizada);
    }

    @Override
    public EpsResponse obtenerPorId(Long id) {

        Eps eps = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EPS no encontrada."));

        return mapper.toResponse(eps);
    }

    @Override
    public List<EpsResponse> obtenerTodos() {

        return repository.findByActivoTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void eliminar(Long id) {

        Eps eps = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EPS no encontrada."));

        eps.setActivo(false);

        repository.save(eps);
    }
}