package com.mediturnos.mapper.medical;

import com.mediturnos.dto.medical.request.ConsultorioRequest;
import com.mediturnos.dto.medical.response.ConsultorioResponse;
import com.mediturnos.entity.medical.Consultorio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ConsultorioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Consultorio toEntity(ConsultorioRequest request);

    ConsultorioResponse toResponse(Consultorio consultorio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    void updateEntity(ConsultorioRequest request,
                      @MappingTarget Consultorio consultorio);

}