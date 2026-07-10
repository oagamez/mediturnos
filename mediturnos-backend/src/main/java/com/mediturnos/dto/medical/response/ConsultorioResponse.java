package com.mediturnos.dto.medical.response;

import lombok.Data;

@Data
public class ConsultorioResponse {

    private Long id;

    private String codigo;

    private String nombre;

    private String ubicacion;

    private Integer piso;

    private Boolean activo;

}