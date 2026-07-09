package com.mediturnos.repository.patient;

import com.mediturnos.entity.patient.Eps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpsRepository extends JpaRepository<Eps, Long> {

    boolean existsByNombre(String nombre);

    List<Eps> findByActivoTrue();

}