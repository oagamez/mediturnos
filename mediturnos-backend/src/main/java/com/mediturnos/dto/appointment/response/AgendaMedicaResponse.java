package com.mediturnos.dto.appointment.response;

import com.mediturnos.enums.DiaSemana;
import com.mediturnos.enums.DuracionConsulta;
import lombok.Data;

import java.time.LocalTime;

@Data
public class AgendaMedicaResponse {

    private Long id;

    private Long medicoId;

    private String medicoNombre;

    private Long consultorioId;

    private String consultorioNombre;

    private DiaSemana diaSemana;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private DuracionConsulta duracionConsulta;

    private Boolean activo;

}