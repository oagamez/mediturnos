package com.mediturnos.service.interfaces.medical;

import com.mediturnos.dto.medical.request.ConsultorioRequest;
import com.mediturnos.dto.medical.response.ConsultorioResponse;

import java.util.List;

public interface ConsultorioService {

    ConsultorioResponse crear(ConsultorioRequest request);

    ConsultorioResponse actualizar(Long id, ConsultorioRequest request);

    ConsultorioResponse obtenerPorId(Long id);

    List<ConsultorioResponse> obtenerTodos();

    void eliminar(Long id);

}