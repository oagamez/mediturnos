package com.mediturnos.mapper.security;

import com.mediturnos.dto.security.request.UsuarioRequest;
import com.mediturnos.dto.security.response.UsuarioResponse;
import com.mediturnos.entity.security.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "rol", ignore = true)
    Usuario toEntity(UsuarioRequest request);

    @Mapping(source = "rol.id", target = "rolId")
    @Mapping(source = "rol.nombre", target = "rolNombre")
    UsuarioResponse toResponse(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "rol", ignore = true)
    void updateEntity(UsuarioRequest request, @MappingTarget Usuario usuario);

}