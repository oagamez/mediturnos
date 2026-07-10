package com.mediturnos.repository.medical;

import com.mediturnos.entity.medical.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    boolean existsByDocumento(String documento);

    boolean existsByEmail(String email);

    boolean existsByRegistroProfesional(String registroProfesional);

    List<Medico> findByActivoTrue();

}