package com.mediturnos.dto.patient.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteRequest {

    @NotBlank
    @Size(max = 20)
    private String documento;

    @NotBlank
    @Size(max = 80)
    private String nombres;

    @NotBlank
    @Size(max = 80)
    private String apellidos;

    @NotNull
    @Past
    private LocalDate fechaNacimiento;

    @NotBlank
    @Size(max = 20)
    private String telefono;

    @NotBlank
    @Email
    @Size(max = 120)
    private String email;

    @NotBlank
    @Size(max = 50)
    private String eps;

}