package com.mediturnos.mapper.medical;

import com.mediturnos.dto.medical.request.MedicoRequest;
import com.mediturnos.dto.medical.response.MedicoResponse;
import com.mediturnos.entity.medical.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "especialidad", ignore = true)
    Medico toEntity(MedicoRequest request);

    @Mapping(source = "especialidad.id", target = "especialidadId")
    @Mapping(source = "especialidad.nombre", target = "especialidadNombre")
    MedicoResponse toResponse(Medico medico);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "especialidad", ignore = true)
    void updateEntity(MedicoRequest request,
                      @MappingTarget Medico medico);

}
