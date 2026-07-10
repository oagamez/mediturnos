package com.mediturnos.controller.patient;

import com.mediturnos.constant.ApiPaths;
import com.mediturnos.dto.common.ApiResponse;
import com.mediturnos.dto.patient.request.PacienteRequest;
import com.mediturnos.dto.patient.response.PacienteResponse;
import com.mediturnos.service.interfaces.patient.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.PACIENTES)
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<ApiResponse<PacienteResponse>> crear(
            @Valid @RequestBody PacienteRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Paciente creado correctamente.",
                        pacienteService.crear(request)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PacienteResponse>>> listar() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Consulta realizada correctamente.",
                        pacienteService.obtenerTodos()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PacienteResponse>> obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Paciente encontrado.",
                        pacienteService.obtenerPorId(id)
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PacienteResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PacienteRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Paciente actualizado correctamente.",
                        pacienteService.actualizar(id, request)
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @PathVariable Long id) {

        pacienteService.eliminar(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Paciente inactivado correctamente.",
                        null
                ));
    }

}