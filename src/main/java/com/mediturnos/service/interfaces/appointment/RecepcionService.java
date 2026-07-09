package com.mediturnos.service.interfaces.appointment;

import com.mediturnos.dto.appointment.response.CitaResponse;

import java.time.LocalDate;
import java.util.List;

public interface RecepcionService {

    List<CitaResponse> obtenerCitasDelDia(LocalDate fecha);

    CitaResponse confirmarLlegada(Long citaId);

}