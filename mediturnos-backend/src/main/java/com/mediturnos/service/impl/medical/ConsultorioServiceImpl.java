package com.mediturnos.service.impl.medical;

import com.mediturnos.dto.medical.request.ConsultorioRequest;
import com.mediturnos.dto.medical.response.ConsultorioResponse;
import com.mediturnos.entity.medical.Consultorio;
import com.mediturnos.exception.BusinessException;
import com.mediturnos.exception.ResourceNotFoundException;
import com.mediturnos.mapper.medical.ConsultorioMapper;
import com.mediturnos.repository.medical.ConsultorioRepository;
import com.mediturnos.service.interfaces.medical.ConsultorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultorioServiceImpl implements ConsultorioService {

    private final ConsultorioRepository repository;
    private final ConsultorioMapper mapper;

    @Override
    public ConsultorioResponse crear(ConsultorioRequest request) {

        if (repository.existsByCodigo(request.getCodigo())) {
            throw new BusinessException("Ya existe un consultorio con ese código.");
        }

        Consultorio consultorio = mapper.toEntity(request);

        return mapper.toResponse(repository.save(consultorio));
    }

    @Override
    public ConsultorioResponse actualizar(Long id, ConsultorioRequest request) {

        Consultorio consultorio = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultorio no encontrado."));

        if (!consultorio.getCodigo().equals(request.getCodigo())
                && repository.existsByCodigo(request.getCodigo())) {

            throw new BusinessException("Ya existe un consultorio con ese código.");
        }

        mapper.updateEntity(request, consultorio);

        return mapper.toResponse(repository.save(consultorio));
    }

    @Override
    public ConsultorioResponse obtenerPorId(Long id) {

        return mapper.toResponse(
                repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Consultorio no encontrado."))
        );
    }

    @Override
    public List<ConsultorioResponse> obtenerTodos() {

        return repository.findByActivoTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void eliminar(Long id) {

        Consultorio consultorio = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultorio no encontrado."));

        consultorio.setActivo(false);

        repository.save(consultorio);
    }
}