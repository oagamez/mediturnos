package com.mediturnos.service.impl.medical;

import com.mediturnos.dto.medical.request.MedicoRequest;
import com.mediturnos.dto.medical.response.MedicoResponse;
import com.mediturnos.entity.medical.Especialidad;
import com.mediturnos.entity.medical.Medico;
import com.mediturnos.exception.BusinessException;
import com.mediturnos.exception.ResourceNotFoundException;
import com.mediturnos.mapper.medical.MedicoMapper;
import com.mediturnos.repository.medical.EspecialidadRepository;
import com.mediturnos.repository.medical.MedicoRepository;
import com.mediturnos.service.interfaces.medical.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository repository;
    private final EspecialidadRepository especialidadRepository;
    private final MedicoMapper mapper;

    @Override
    public MedicoResponse crear(MedicoRequest request) {

        if(repository.existsByDocumento(request.getDocumento()))
            throw new BusinessException("Ya existe un médico con ese documento.");

        if(repository.existsByEmail(request.getEmail()))
            throw new BusinessException("Ya existe un médico con ese correo.");

        if(repository.existsByRegistroProfesional(request.getRegistroProfesional()))
            throw new BusinessException("El registro profesional ya existe.");

        Especialidad especialidad = especialidadRepository.findById(request.getEspecialidadId())
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada."));

        Medico medico = mapper.toEntity(request);
        medico.setEspecialidad(especialidad);

        return mapper.toResponse(repository.save(medico));
    }

    @Override
    public MedicoResponse actualizar(Long id, MedicoRequest request) {

        Medico medico = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado."));

        Especialidad especialidad = especialidadRepository.findById(request.getEspecialidadId())
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada."));

        if(!medico.getDocumento().equals(request.getDocumento())
                && repository.existsByDocumento(request.getDocumento()))
            throw new BusinessException("Ya existe un médico con ese documento.");

        if(!medico.getEmail().equals(request.getEmail())
                && repository.existsByEmail(request.getEmail()))
            throw new BusinessException("Ya existe un médico con ese correo.");

        if(!medico.getRegistroProfesional().equals(request.getRegistroProfesional())
                && repository.existsByRegistroProfesional(request.getRegistroProfesional()))
            throw new BusinessException("El registro profesional ya existe.");

        mapper.updateEntity(request, medico);
        medico.setEspecialidad(especialidad);

        return mapper.toResponse(repository.save(medico));
    }

    @Override
    public MedicoResponse obtenerPorId(Long id) {

        return mapper.toResponse(
                repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado."))
        );
    }

    @Override
    public List<MedicoResponse> obtenerTodos() {

        return repository.findByActivoTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void eliminar(Long id) {

        Medico medico = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado."));

        medico.setActivo(false);

        repository.save(medico);
    }

}