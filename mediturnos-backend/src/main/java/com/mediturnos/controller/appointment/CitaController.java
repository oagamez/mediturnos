package com.mediturnos.controller.appointment;

import com.mediturnos.constant.ApiPaths;
import com.mediturnos.dto.appointment.request.CitaRequest;
import com.mediturnos.dto.appointment.response.CitaResponse;
import com.mediturnos.dto.common.ApiResponse;
import com.mediturnos.service.interfaces.appointment.CitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.CITAS)
@RequiredArgsConstructor
public class CitaController {

    private final CitaService service;

    @PostMapping
    public ResponseEntity<ApiResponse<CitaResponse>> crear(
            @Valid @RequestBody CitaRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Cita creada correctamente.",
                        service.crear(request)
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CitaResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CitaRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Cita actualizada correctamente.",
                        service.actualizar(id, request)
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CitaResponse>> obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Consulta realizada correctamente.",
                        service.obtenerPorId(id)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CitaResponse>>> listar() {

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
                        "Cita eliminada correctamente.",
                        null
                ));
    }

    /*@PatchMapping("/{id}/confirmar")
    public ResponseEntity<ApiResponse<CitaResponse>> confirmar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Cita confirmada correctamente.",
                        service.confirmar(id)
                ));
    }*/

    /*@PatchMapping("/{id}/iniciar")
    public ResponseEntity<ApiResponse<CitaResponse>> iniciarAtencion(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Atención iniciada correctamente.",
                        service.iniciarAtencion(id)
                ));
    }*/

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<ApiResponse<CitaResponse>> finalizarAtencion(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Atención finalizada correctamente.",
                        service.finalizarAtencion(id)
                ));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ApiResponse<CitaResponse>> cancelar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Cita cancelada correctamente.",
                        service.cancelar(id)
                ));
    }

}