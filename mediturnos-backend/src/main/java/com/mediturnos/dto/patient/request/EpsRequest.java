package com.mediturnos.dto.patient.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EpsRequest {

    @NotBlank
    @Size(max = 120)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

}