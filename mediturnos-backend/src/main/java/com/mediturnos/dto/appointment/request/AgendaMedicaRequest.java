package com.mediturnos.dto.appointment.request;

import com.mediturnos.enums.DiaSemana;
import com.mediturnos.enums.DuracionConsulta;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
public class AgendaMedicaRequest {

    @NotNull
    private Long medicoId;

    @NotNull
    private Long consultorioId;

    @NotNull
    private DiaSemana diaSemana;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFin;

    @NotNull
    private DuracionConsulta duracionConsulta;

}