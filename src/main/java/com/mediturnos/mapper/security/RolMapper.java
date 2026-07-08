package com.mediturnos.mapper.security;

import com.mediturnos.dto.security.request.RolRequest;
import com.mediturnos.dto.security.response.RolResponse;
import com.mediturnos.entity.security.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RolMapper {

    Rol toEntity(RolRequest request);

    RolResponse toResponse(Rol entity);

    void updateEntity(RolRequest request, @MappingTarget Rol entity);

}
