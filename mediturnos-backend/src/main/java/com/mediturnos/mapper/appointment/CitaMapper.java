package com.mediturnos.mapper.appointment;

import com.mediturnos.dto.appointment.request.CitaRequest;
import com.mediturnos.dto.appointment.response.CitaResponse;
import com.mediturnos.entity.appointment.Cita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "agendaMedica", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Cita toEntity(CitaRequest request);

    @Mapping(source = "paciente.id", target = "pacienteId")
    @Mapping(source = "paciente.nombres", target = "pacienteNombre")
    @Mapping(source = "agendaMedica.id", target = "agendaMedicaId")
    @Mapping(source = "agendaMedica.medico.nombres", target = "medicoNombre")
    @Mapping(source = "agendaMedica.consultorio.nombre", target = "consultorio")
    CitaResponse toResponse(Cita cita);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "agendaMedica", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void updateEntity(CitaRequest request,
                      @MappingTarget Cita cita);

}