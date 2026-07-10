package com.mediturnos.controller.appointment;

import com.mediturnos.constant.ApiPaths;
import com.mediturnos.dto.appointment.response.CitaResponse;
import com.mediturnos.dto.common.ApiResponse;
import com.mediturnos.service.interfaces.appointment.PantallaMedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.PANTALLA_MEDICO)
@RequiredArgsConstructor
public class PantallaMedicoController {

    private final PantallaMedicoService service;

    @GetMapping("/espera")
    public ResponseEntity<ApiResponse<List<CitaResponse>>> pacientesEnEspera(
            @RequestParam Long medicoId,
            @RequestParam LocalDate fecha) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Pacientes en espera.",
                        service.pacientesEnEspera(
                                medicoId,
                                fecha)));

    }

    @PatchMapping("/{id}/llamar")
    public ResponseEntity<ApiResponse<CitaResponse>> llamar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Paciente llamado.",
                        service.llamarSiguiente(id)));

    }

}