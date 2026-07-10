package com.mediturnos.service.interfaces.appointment;

import com.mediturnos.dto.appointment.request.CitaRequest;
import com.mediturnos.dto.appointment.response.CitaResponse;

import java.util.List;

public interface CitaService {

    CitaResponse crear(CitaRequest request);

    CitaResponse actualizar(Long id, CitaRequest request);

    CitaResponse obtenerPorId(Long id);

    List<CitaResponse> obtenerTodos();

    void eliminar(Long id);

    // Flujo de negocio

    CitaResponse finalizarAtencion(Long id);

    CitaResponse cancelar(Long id);

}