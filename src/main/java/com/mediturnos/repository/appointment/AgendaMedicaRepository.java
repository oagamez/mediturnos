package com.mediturnos.repository.appointment;

import com.mediturnos.entity.appointment.AgendaMedica;
import com.mediturnos.entity.medical.Consultorio;
import com.mediturnos.entity.medical.Medico;
import com.mediturnos.enums.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendaMedicaRepository extends JpaRepository<AgendaMedica, Long> {

    List<AgendaMedica> findByActivoTrue();

    List<AgendaMedica> findByMedicoAndDiaSemanaAndActivoTrue(
            Medico medico,
            DiaSemana diaSemana);

    List<AgendaMedica> findByConsultorioAndDiaSemanaAndActivoTrue(
            Consultorio consultorio,
            DiaSemana diaSemana);

}