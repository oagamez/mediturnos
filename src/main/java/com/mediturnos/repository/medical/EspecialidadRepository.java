package com.mediturnos.repository.medical;

import com.mediturnos.entity.medical.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    Optional<Especialidad> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    List<Especialidad> findByActivoTrue();

}