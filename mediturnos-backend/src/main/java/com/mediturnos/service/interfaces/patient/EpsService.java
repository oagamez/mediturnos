package com.mediturnos.service.interfaces.patient;

import com.mediturnos.dto.patient.request.EpsRequest;
import com.mediturnos.dto.patient.response.EpsResponse;

import java.util.List;

public interface EpsService {

    EpsResponse crear(EpsRequest request);

    EpsResponse actualizar(Long id, EpsRequest request);

    EpsResponse obtenerPorId(Long id);

    List<EpsResponse> obtenerTodos();

    void eliminar(Long id);

}