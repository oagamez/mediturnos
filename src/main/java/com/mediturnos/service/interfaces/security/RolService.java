package com.mediturnos.service.interfaces.security;

import com.mediturnos.dto.security.request.RolRequest;
import com.mediturnos.dto.security.response.RolResponse;

import java.util.List;

public interface RolService {

    RolResponse crear(RolRequest request);

    RolResponse actualizar(Long id, RolRequest request);

    RolResponse obtenerPorId(Long id);

    List<RolResponse> obtenerTodos();

    void eliminar(Long id);

}