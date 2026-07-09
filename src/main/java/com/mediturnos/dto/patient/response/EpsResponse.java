package com.mediturnos.dto.patient.response;

import lombok.Data;

@Data
public class EpsResponse {

    private Long id;

    private String nombre;

    private String descripcion;

    private Boolean activo;

}