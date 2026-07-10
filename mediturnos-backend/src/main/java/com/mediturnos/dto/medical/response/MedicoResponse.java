package com.mediturnos.dto.medical.response;

import lombok.Data;

@Data
public class MedicoResponse {

    private Long id;

    private String documento;

    private String nombres;

    private String apellidos;

    private String registroProfesional;

    private String telefono;

    private String email;

    private Boolean activo;

    private Long especialidadId;

    private String especialidadNombre;

}