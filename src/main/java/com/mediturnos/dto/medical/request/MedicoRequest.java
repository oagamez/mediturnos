package com.mediturnos.dto.medical.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MedicoRequest {

    @NotBlank
    @Size(max = 20)
    private String documento;

    @NotBlank
    @Size(max = 80)
    private String nombres;

    @NotBlank
    @Size(max = 80)
    private String apellidos;

    @NotBlank
    @Size(max = 30)
    private String registroProfesional;

    @NotBlank
    @Size(max = 20)
    private String telefono;

    @Email
    @NotBlank
    @Size(max = 120)
    private String email;

    @NotNull
    private Long especialidadId;

}