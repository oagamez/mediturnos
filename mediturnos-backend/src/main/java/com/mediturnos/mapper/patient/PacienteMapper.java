package com.mediturnos.mapper.patient;

import com.mediturnos.dto.patient.request.PacienteRequest;
import com.mediturnos.dto.patient.response.PacienteResponse;
import com.mediturnos.entity.patient.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Paciente toEntity(PacienteRequest request);

    PacienteResponse toResponse(Paciente paciente);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    void updateEntity(PacienteRequest request,
                      @MappingTarget Paciente paciente);

}