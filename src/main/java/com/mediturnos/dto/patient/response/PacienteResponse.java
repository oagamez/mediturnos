package com.mediturnos.dto.patient.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteResponse {

    private Long id;

    private String documento;

    private String nombres;

    private String apellidos;

    private LocalDate fechaNacimiento;

    private String telefono;

    private String email;

    private Boolean activo;

    private Long epsId;

    private String epsNombre;

}