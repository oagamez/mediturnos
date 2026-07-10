package com.mediturnos.dto.medical.response;

import lombok.Data;

@Data
public class EspecialidadResponse {

    private Long id;

    private String nombre;

    private String descripcion;

    private Boolean activo;

}