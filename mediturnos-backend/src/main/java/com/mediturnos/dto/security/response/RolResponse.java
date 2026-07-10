package com.mediturnos.dto.security.response;

import lombok.Data;

@Data
public class RolResponse {

    private Long id;

    private String nombre;

    private String descripcion;

    private Boolean activo;

}