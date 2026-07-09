package com.mediturnos.dto.medical.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ConsultorioRequest {

    @NotBlank
    @Size(max = 20)
    private String codigo;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    @Size(max = 100)
    private String ubicacion;

    @NotNull
    private Integer piso;

}