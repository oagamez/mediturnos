package com.mediturnos.mapper.patient;

import com.mediturnos.dto.patient.request.EpsRequest;
import com.mediturnos.dto.patient.response.EpsResponse;
import com.mediturnos.entity.patient.Eps;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EpsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Eps toEntity(EpsRequest request);

    EpsResponse toResponse(Eps eps);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    void updateEntity(EpsRequest request, @MappingTarget Eps eps);

}