package com.mediturnos.service.interfaces.security;

import com.mediturnos.dto.security.request.UsuarioRequest;
import com.mediturnos.dto.security.response.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    UsuarioResponse crear(UsuarioRequest request);

    UsuarioResponse actualizar(Long id, UsuarioRequest request);

    UsuarioResponse obtenerPorId(Long id);

    List<UsuarioResponse> obtenerTodos();

    void eliminar(Long id);

}
