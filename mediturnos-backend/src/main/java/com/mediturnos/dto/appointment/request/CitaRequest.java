package com.mediturnos.dto.appointment.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CitaRequest {

    @NotNull
    private Long pacienteId;

    @NotNull
    private Long agendaMedicaId;

    @NotNull
    @FutureOrPresent
    private LocalDate fecha;

    @NotNull
    private LocalTime hora;

    @Size(max = 500)
    private String observaciones;

}