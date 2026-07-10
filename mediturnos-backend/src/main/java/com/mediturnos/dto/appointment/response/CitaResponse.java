package com.mediturnos.dto.appointment.response;

import com.mediturnos.enums.EstadoCita;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CitaResponse {

    private Long id;

    private Long pacienteId;

    private String pacienteNombre;

    private Long agendaMedicaId;

    private String medicoNombre;

    private String consultorio;

    private LocalDate fecha;

    private LocalTime hora;

    private EstadoCita estado;

    private String observaciones;

    private Boolean activo;

}
