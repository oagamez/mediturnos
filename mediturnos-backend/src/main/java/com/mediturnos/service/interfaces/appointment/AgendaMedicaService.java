package com.mediturnos.service.interfaces.appointment;

import com.mediturnos.dto.appointment.request.AgendaMedicaRequest;
import com.mediturnos.dto.appointment.response.AgendaMedicaResponse;

import java.util.List;

public interface AgendaMedicaService {

    AgendaMedicaResponse crear(AgendaMedicaRequest request);

    AgendaMedicaResponse actualizar(Long id,
                                    AgendaMedicaRequest request);

    AgendaMedicaResponse obtenerPorId(Long id);

    List<AgendaMedicaResponse> obtenerTodos();

    void eliminar(Long id);

}