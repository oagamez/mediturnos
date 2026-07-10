package com.mediturnos.service.impl.appointment;

import com.mediturnos.dto.appointment.request.AgendaMedicaRequest;
import com.mediturnos.dto.appointment.response.AgendaMedicaResponse;
import com.mediturnos.entity.appointment.AgendaMedica;
import com.mediturnos.entity.medical.Consultorio;
import com.mediturnos.entity.medical.Medico;
import com.mediturnos.exception.BusinessException;
import com.mediturnos.exception.ResourceNotFoundException;
import com.mediturnos.mapper.appointment.AgendaMedicaMapper;
import com.mediturnos.repository.appointment.AgendaMedicaRepository;
import com.mediturnos.repository.medical.ConsultorioRepository;
import com.mediturnos.repository.medical.MedicoRepository;
import com.mediturnos.service.interfaces.appointment.AgendaMedicaService;
import com.mediturnos.util.HorarioUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaMedicaServiceImpl implements AgendaMedicaService {

    private final AgendaMedicaRepository repository;
    private final MedicoRepository medicoRepository;
    private final ConsultorioRepository consultorioRepository;
    private final AgendaMedicaMapper mapper;

    @Override
    public AgendaMedicaResponse crear(AgendaMedicaRequest request) {

        validarHorario(request);

        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado."));

        Consultorio consultorio = consultorioRepository.findById(request.getConsultorioId())
                .orElseThrow(() -> new ResourceNotFoundException("Consultorio no encontrado."));

        validarCruceMedico(request, medico);

        validarCruceConsultorio(request, consultorio);

        AgendaMedica agenda = mapper.toEntity(request);

        agenda.setMedico(medico);
        agenda.setConsultorio(consultorio);

        AgendaMedica guardada = repository.save(agenda);

        return mapper.toResponse(guardada);
    }

    @Override
    public AgendaMedicaResponse actualizar(Long id, AgendaMedicaRequest request) {

        validarHorario(request);

        AgendaMedica agenda = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda médica no encontrada."));

        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado."));

        Consultorio consultorio = consultorioRepository.findById(request.getConsultorioId())
                .orElseThrow(() -> new ResourceNotFoundException("Consultorio no encontrado."));

        validarCruceMedicoActualizacion(request, medico, id);

        validarCruceConsultorioActualizacion(request, consultorio, id);

        mapper.updateEntity(request, agenda);

        agenda.setMedico(medico);
        agenda.setConsultorio(consultorio);

        AgendaMedica actualizada = repository.save(agenda);

        return mapper.toResponse(actualizada);
    }

    @Override
    public AgendaMedicaResponse obtenerPorId(Long id) {

        AgendaMedica agenda = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda médica no encontrada."));

        return mapper.toResponse(agenda);
    }

    @Override
    public List<AgendaMedicaResponse> obtenerTodos() {

        return repository.findByActivoTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void eliminar(Long id) {

        AgendaMedica agenda = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda médica no encontrada."));

        agenda.setActivo(false);

        repository.save(agenda);
    }

    // ==========================================================
    // VALIDACIONES
    // ==========================================================

    private void validarHorario(AgendaMedicaRequest request) {

        if (!request.getHoraInicio().isBefore(request.getHoraFin())) {

            throw new BusinessException(
                    "La hora de inicio debe ser menor que la hora de fin.");
        }

    }

    private void validarCruceMedico(
            AgendaMedicaRequest request,
            Medico medico) {

        List<AgendaMedica> agendas = repository
                .findByMedicoAndDiaSemanaAndActivoTrue(
                        medico,
                        request.getDiaSemana());

        for (AgendaMedica agenda : agendas) {

            if (HorarioUtil.horariosSeCruzan(
                    request.getHoraInicio(),
                    request.getHoraFin(),
                    agenda.getHoraInicio(),
                    agenda.getHoraFin())) {

                throw new BusinessException(
                        "El médico ya tiene una agenda en ese horario.");
            }

        }

    }

    private void validarCruceConsultorio(
            AgendaMedicaRequest request,
            Consultorio consultorio) {

        List<AgendaMedica> agendas = repository
                .findByConsultorioAndDiaSemanaAndActivoTrue(
                        consultorio,
                        request.getDiaSemana());

        for (AgendaMedica agenda : agendas) {

            if (HorarioUtil.horariosSeCruzan(
                    request.getHoraInicio(),
                    request.getHoraFin(),
                    agenda.getHoraInicio(),
                    agenda.getHoraFin())) {

                throw new BusinessException(
                        "El consultorio ya está ocupado en ese horario.");
            }

        }

    }

    private void validarCruceMedicoActualizacion(
            AgendaMedicaRequest request,
            Medico medico,
            Long agendaId) {

        List<AgendaMedica> agendas = repository
                .findByMedicoAndDiaSemanaAndActivoTrue(
                        medico,
                        request.getDiaSemana());

        for (AgendaMedica agenda : agendas) {

            if (agenda.getId().equals(agendaId)) {
                continue;
            }

            if (HorarioUtil.horariosSeCruzan(
                    request.getHoraInicio(),
                    request.getHoraFin(),
                    agenda.getHoraInicio(),
                    agenda.getHoraFin())) {

                throw new BusinessException(
                        "El médico ya tiene una agenda en ese horario.");
            }

        }

    }

    private void validarCruceConsultorioActualizacion(
            AgendaMedicaRequest request,
            Consultorio consultorio,
            Long agendaId) {

        List<AgendaMedica> agendas = repository
                .findByConsultorioAndDiaSemanaAndActivoTrue(
                        consultorio,
                        request.getDiaSemana());

        for (AgendaMedica agenda : agendas) {

            if (agenda.getId().equals(agendaId)) {
                continue;
            }

            if (HorarioUtil.horariosSeCruzan(
                    request.getHoraInicio(),
                    request.getHoraFin(),
                    agenda.getHoraInicio(),
                    agenda.getHoraFin())) {

                throw new BusinessException(
                        "El consultorio ya está ocupado en ese horario.");
            }

        }

    }

}