package com.mediturnos.service.interfaces.patient;

import com.mediturnos.dto.patient.request.PacienteRequest;
import com.mediturnos.dto.patient.response.PacienteResponse;

import java.util.List;

public interface PacienteService {

    PacienteResponse crear(PacienteRequest request);

    PacienteResponse actualizar(Long id, PacienteRequest request);

    PacienteResponse obtenerPorId(Long id);

    List<PacienteResponse> obtenerTodos();

    void eliminar(Long id);

}