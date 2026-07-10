package com.mediturnos.repository.security;

import com.mediturnos.entity.security.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

    /**
     * Verifica si existe un rol con el nombre indicado.
     */
    boolean existsByNombre(String nombre);

    /**
     * Busca un rol por nombre.
     */
    Optional<Rol> findByNombre(String nombre);

    /**
     * Obtiene únicamente los roles activos.
     */
    List<Rol> findByActivoTrue();

}