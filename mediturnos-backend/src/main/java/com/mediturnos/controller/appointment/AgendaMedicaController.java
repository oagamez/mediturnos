package com.mediturnos.controller.appointment;

import com.mediturnos.constant.ApiPaths;
import com.mediturnos.dto.appointment.request.AgendaMedicaRequest;
import com.mediturnos.dto.appointment.response.AgendaMedicaResponse;
import com.mediturnos.dto.common.ApiResponse;
import com.mediturnos.service.interfaces.appointment.AgendaMedicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.AGENDAS_MEDICAS)
@RequiredArgsConstructor
public class AgendaMedicaController {

    private final AgendaMedicaService service;

    @PostMapping
    public ResponseEntity<ApiResponse<AgendaMedicaResponse>> crear(
            @Valid @RequestBody AgendaMedicaRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Agenda médica creada correctamente.",
                        service.crear(request)
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AgendaMedicaResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody AgendaMedicaRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Agenda médica actualizada correctamente.",
                        service.actualizar(id, request)
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AgendaMedicaResponse>> obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Agenda médica encontrada.",
                        service.obtenerPorId(id)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AgendaMedicaResponse>>> listar() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Consulta realizada correctamente.",
                        service.obtenerTodos()
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Agenda médica inactivada correctamente.",
                        null
                ));
    }

}