package com.mediturnos.dto.security.response;

import lombok.Data;

@Data
public class UsuarioResponse {

    private Long id;

    private String nombres;

    private String apellidos;

    private String username;

    private String email;

    private Boolean activo;

    private Long rolId;

    private String rolNombre;

}