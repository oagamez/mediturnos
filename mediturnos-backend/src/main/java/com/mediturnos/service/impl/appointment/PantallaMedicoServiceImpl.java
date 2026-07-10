/*package com.mediturnos.service.impl.appointment;

import com.mediturnos.dto.appointment.response.CitaResponse;
import com.mediturnos.entity.appointment.Cita;
import com.mediturnos.enums.EstadoCita;
import com.mediturnos.exception.BusinessException;
import com.mediturnos.exception.ResourceNotFoundException;
import com.mediturnos.mapper.appointment.CitaMapper;
import com.mediturnos.repository.appointment.CitaRepository;
import com.mediturnos.service.interfaces.appointment.PantallaMedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PantallaMedicoServiceImpl implements PantallaMedicoService {

    private final CitaRepository repository;
    private final CitaMapper mapper;

    @Override
    public List<CitaResponse> pacientesEnEspera(
            Long medicoId,
            LocalDate fecha) {

        return repository
                .findByAgendaMedicaMedicoIdAndFechaAndEstadoAndActivoTrue(
                        medicoId,
                        fecha,
                        EstadoCita.CONFIRMADA)
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

    @Override
    public CitaResponse llamarSiguiente(Long citaId) {

        Cita cita = repository.findById(citaId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cita no encontrada."));

        if (cita.getEstado() != EstadoCita.CONFIRMADA) {

            throw new BusinessException(
                    "Solo las citas CONFIRMADAS pueden ser llamadas.");

        }

        cita.setEstado(EstadoCita.EN_ATENCION);

        return mapper.toResponse(repository.save(cita));

    }

}*/