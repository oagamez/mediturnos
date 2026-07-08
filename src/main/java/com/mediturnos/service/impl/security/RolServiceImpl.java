package com.mediturnos.service.impl.security;

import com.mediturnos.dto.security.request.RolRequest;
import com.mediturnos.dto.security.response.RolResponse;
import com.mediturnos.entity.security.Rol;
import com.mediturnos.exception.BusinessException;
import com.mediturnos.exception.ResourceNotFoundException;
import com.mediturnos.mapper.security.RolMapper;
import com.mediturnos.repository.security.RolRepository;
import com.mediturnos.service.interfaces.security.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository repository;
    private final RolMapper mapper;

    @Override
    public RolResponse crear(RolRequest request) {

        if (repository.existsByNombre(request.getNombre())) {
            throw new BusinessException("El rol ya existe.");
        }

        Rol rol = mapper.toEntity(request);

        return mapper.toResponse(repository.save(rol));
    }

    @Override
    public RolResponse actualizar(Long id, RolRequest request) {

        Rol rol = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado."));

        mapper.updateEntity(request, rol);

        return mapper.toResponse(repository.save(rol));
    }

    @Override
    public RolResponse obtenerPorId(Long id) {

        Rol rol = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado."));

        return mapper.toResponse(rol);
    }

    @Override
    public List<RolResponse> obtenerTodos() {

        return repository.findByActivoTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void eliminar(Long id) {

        Rol rol = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado."));

        rol.setActivo(false);
        repository.save(rol);
    }
}