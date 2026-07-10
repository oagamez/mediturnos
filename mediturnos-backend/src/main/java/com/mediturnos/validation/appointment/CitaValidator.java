package com.mediturnos.validation.appointment;

import com.mediturnos.dto.appointment.request.CitaRequest;
import com.mediturnos.entity.appointment.AgendaMedica;
import com.mediturnos.entity.patient.Paciente;
import com.mediturnos.exception.BusinessException;
import com.mediturnos.repository.appointment.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CitaValidator {

    private final CitaRepository citaRepository;

    public void validarHorarioAgenda(
            CitaRequest request,
            AgendaMedica agenda) {

        if (request.getHora().isBefore(agenda.getHoraInicio())
                || request.getHora().isAfter(agenda.getHoraFin())) {

            throw new BusinessException(
                    "La hora no pertenece al horario de la agenda médica.");
        }

    }

    public void validarDisponibilidad(
            CitaRequest request,
            AgendaMedica agenda) {

        if (citaRepository.existsByAgendaMedicaAndFechaAndHora(
                agenda,
                request.getFecha(),
                request.getHora())) {

            throw new BusinessException(
                    "Ya existe una cita asignada para esa fecha y hora.");
        }

    }

    public void validarPaciente(Paciente paciente) {

        if (!paciente.getActivo()) {

            throw new BusinessException(
                    "El paciente se encuentra inactivo.");
        }

    }

    public void validarFecha(CitaRequest request) {

        if (request.getFecha().isBefore(java.time.LocalDate.now())) {

            throw new BusinessException(
                    "No es posible registrar citas en fechas pasadas.");
        }

    }

}