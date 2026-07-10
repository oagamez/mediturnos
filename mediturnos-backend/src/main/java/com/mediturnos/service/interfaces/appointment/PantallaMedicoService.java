package com.mediturnos.service.interfaces.appointment;

import com.mediturnos.dto.appointment.response.CitaResponse;

import java.time.LocalDate;
import java.util.List;

public interface PantallaMedicoService {

    List<CitaResponse> pacientesEnEspera(
            Long medicoId,
            LocalDate fecha);

    CitaResponse llamarSiguiente(Long citaId);

}