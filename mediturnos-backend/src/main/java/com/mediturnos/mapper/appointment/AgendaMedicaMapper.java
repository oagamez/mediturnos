package com.mediturnos.mapper.appointment;

import com.mediturnos.dto.appointment.request.AgendaMedicaRequest;
import com.mediturnos.dto.appointment.response.AgendaMedicaResponse;
import com.mediturnos.entity.appointment.AgendaMedica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AgendaMedicaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "medico", ignore = true)
    @Mapping(target = "consultorio", ignore = true)
    AgendaMedica toEntity(AgendaMedicaRequest request);

    @Mapping(source = "medico.id", target = "medicoId")
    @Mapping(source = "medico.nombres", target = "medicoNombre")
    @Mapping(source = "consultorio.id", target = "consultorioId")
    @Mapping(source = "consultorio.nombre", target = "consultorioNombre")
    AgendaMedicaResponse toResponse(AgendaMedica agenda);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "medico", ignore = true)
    @Mapping(target = "consultorio", ignore = true)
    void updateEntity(AgendaMedicaRequest request,
                      @MappingTarget AgendaMedica agenda);

}