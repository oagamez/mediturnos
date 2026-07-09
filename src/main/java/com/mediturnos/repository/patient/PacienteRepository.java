package com.mediturnos.repository.patient;

import com.mediturnos.entity.patient.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente,Long>{

    boolean existsByDocumento(String documento);

    boolean existsByEmail(String email);

    List<Paciente> findByActivoTrue();

}