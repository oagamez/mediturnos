package com.mediturnos.repository.medical;

import com.mediturnos.entity.medical.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {

    boolean existsByCodigo(String codigo);

    List<Consultorio> findByActivoTrue();

}