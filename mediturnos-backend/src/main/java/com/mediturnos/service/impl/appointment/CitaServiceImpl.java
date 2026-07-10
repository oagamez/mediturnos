package com.mediturnos.service.impl.appointment;

import com.mediturnos.dto.appointment.request.CitaRequest;
import com.mediturnos.dto.appointment.response.CitaResponse;
import com.mediturnos.entity.appointment.AgendaMedica;
import com.mediturnos.entity.appointment.Cita;
import com.mediturnos.entity.patient.Paciente;
import com.mediturnos.enums.EstadoCita;
import com.mediturnos.exception.ResourceNotFoundException;
import com.mediturnos.mapper.appointment.CitaMapper;
import com.mediturnos.repository.appointment.AgendaMedicaRepository;
import com.mediturnos.repository.appointment.CitaRepository;
import com.mediturnos.repository.patient.PacienteRepository;
import com.mediturnos.service.interfaces.appointment.CitaService;
import com.mediturnos.validation.appointment.CitaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository repository;
    private final PacienteRepository pacienteRepository;
    private final AgendaMedicaRepository agendaRepository;
    private final CitaMapper mapper;
    private final CitaValidator validator;

    @Override
    public CitaResponse crear(CitaRequest request) {

        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Paciente no encontrado."));

        AgendaMedica agenda = agendaRepository.findById(request.getAgendaMedicaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agenda médica no encontrada."));

        validator.validarPaciente(paciente);
        validator.validarFecha(request);
        validator.validarHorarioAgenda(request, agenda);
        validator.validarDisponibilidad(request, agenda);

        Cita cita = mapper.toEntity(request);

        cita.setPaciente(paciente);
        cita.setAgendaMedica(agenda);
        cita.setEstado(EstadoCita.PROGRAMADA);

        Cita guardada = repository.save(cita);

        return mapper.toResponse(guardada);
    }

    @Override
    public CitaResponse actualizar(Long id, CitaRequest request) {

        Cita cita = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cita no encontrada."));

        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Paciente no encontrado."));

        AgendaMedica agenda = agendaRepository.findById(request.getAgendaMedicaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agenda médica no encontrada."));

        validator.validarPaciente(paciente);
        validator.validarFecha(request);
        validator.validarHorarioAgenda(request, agenda);

        mapper.updateEntity(request, cita);

        cita.setPaciente(paciente);
        cita.setAgendaMedica(agenda);

        return mapper.toResponse(repository.save(cita));
    }

    @Override
    public CitaResponse obtenerPorId(Long id) {

        Cita cita = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cita no encontrada."));

        return mapper.toResponse(cita);
    }

    @Override
    public List<CitaResponse> obtenerTodos() {

        return repository.findByActivoTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
        @Override
    public void eliminar(Long id) {

        Cita cita = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cita no encontrada."));

        cita.setActivo(false);

        repository.save(cita);
    }

    /*@Override
    public CitaResponse confirmar(Long id) {

        Cita cita = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cita no encontrada."));

        if (cita.getEstado() != EstadoCita.PROGRAMADA) {
            throw new IllegalStateException(
                    "Solo las citas PROGRAMADAS pueden confirmarse.");
        }

        cita.setEstado(EstadoCita.CONFIRMADA);

        return mapper.toResponse(repository.save(cita));
    }

    @Override
    public CitaResponse iniciarAtencion(Long id) {

        Cita cita = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cita no encontrada."));

        if (cita.getEstado() != EstadoCita.CONFIRMADA) {
            throw new IllegalStateException(
                    "Solo las citas CONFIRMADAS pueden iniciar atención.");
        }

        cita.setEstado(EstadoCita.EN_ATENCION);

        return mapper.toResponse(repository.save(cita));
    }*/

    @Override
    public CitaResponse finalizarAtencion(Long id) {

        Cita cita = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cita no encontrada."));

        /*if (cita.getEstado() != EstadoCita.EN_ATENCION) {
            throw new IllegalStateException(
                    "Solo las citas EN_ATENCION pueden finalizar.");
        }*/

        cita.setEstado(EstadoCita.ATENDIDA);

        return mapper.toResponse(repository.save(cita));
    }

    @Override
    public CitaResponse cancelar(Long id) {

        Cita cita = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cita no encontrada."));

        if (cita.getEstado() == EstadoCita.ATENDIDA) {
            throw new IllegalStateException(
                    "No es posible cancelar una cita finalizada.");
        }

        cita.setEstado(EstadoCita.CANCELADA);

        return mapper.toResponse(repository.save(cita));
    }

}