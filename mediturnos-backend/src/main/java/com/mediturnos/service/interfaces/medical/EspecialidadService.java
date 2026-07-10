package com.mediturnos.service.interfaces.medical;

import com.mediturnos.dto.medical.request.EspecialidadRequest;
import com.mediturnos.dto.medical.response.EspecialidadResponse;

import java.util.List;

public interface EspecialidadService {

    EspecialidadResponse crear(EspecialidadRequest request);

    EspecialidadResponse actualizar(Long id, EspecialidadRequest request);

    EspecialidadResponse obtenerPorId(Long id);

    List<EspecialidadResponse> obtenerTodos();

    void eliminar(Long id);

}