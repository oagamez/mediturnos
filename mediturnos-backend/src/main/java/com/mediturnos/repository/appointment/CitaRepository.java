package com.mediturnos.repository.appointment;

import com.mediturnos.entity.appointment.Cita;
import com.mediturnos.enums.EstadoCita;
import com.mediturnos.entity.appointment.AgendaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByActivoTrue();

    List<Cita> findByFechaAndActivoTrue(LocalDate fecha);

    boolean existsByAgendaMedicaAndFechaAndHora(
            AgendaMedica agendaMedica,
            LocalDate fecha,
            LocalTime hora);
    
    List<Cita> findByAgendaMedicaMedicoIdAndFechaAndEstadoAndActivoTrue(
        Long medicoId,
        LocalDate fecha,
        EstadoCita estado);

    List<Cita> findByAgendaMedicaMedicoIdAndFechaAndActivoTrue(
        Long medicoId,
        LocalDate fecha);

}