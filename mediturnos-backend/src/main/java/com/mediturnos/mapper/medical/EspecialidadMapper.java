package com.mediturnos.mapper.medical;

import com.mediturnos.dto.medical.request.EspecialidadRequest;
import com.mediturnos.dto.medical.response.EspecialidadResponse;
import com.mediturnos.entity.medical.Especialidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EspecialidadMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Especialidad toEntity(EspecialidadRequest request);

    EspecialidadResponse toResponse(Especialidad especialidad);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    void updateEntity(EspecialidadRequest request,
                      @MappingTarget Especialidad especialidad);

}
