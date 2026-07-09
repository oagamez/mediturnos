package com.mediturnos.constant;

/**
 * Centraliza todas las rutas de la API REST.
 * Evita repetir cadenas de texto en los controladores.
 */
public final class ApiPaths {

    private ApiPaths() {
        throw new IllegalStateException("Utility class");
    }

    public static final String API = "/api/v1";

    // Seguridad
    public static final String ROLES = API + "/roles";
    public static final String USUARIOS = API + "/usuarios";

    // Pacientes
    public static final String PACIENTES = API + "/pacientes";
    public static final String EPS = API + "/eps";

    // Médicos
    public static final String MEDICOS = API + "/medicos";
    public static final String ESPECIALIDADES = API + "/especialidades";
    public static final String CONSULTORIOS = API + "/consultorios";
    public static final String AGENDAS_MEDICAS = API + "/agendas-medicas";

    // Citas
    public static final String CITAS = API + "/citas";
    public static final String RECEPCION = API + "/recepcion";
    public static final String PANTALLA_MEDICO = API + "/pantalla-medico";
    public static final String AUDITORIA_CITAS = API + "/auditoria-citas";
}