package com.mediturnos.controller.medical;

import com.mediturnos.constant.ApiPaths;
import com.mediturnos.dto.common.ApiResponse;
import com.mediturnos.dto.medical.request.EspecialidadRequest;
import com.mediturnos.dto.medical.response.EspecialidadResponse;
import com.mediturnos.service.interfaces.medical.EspecialidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.ESPECIALIDADES)
@RequiredArgsConstructor
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    @PostMapping
    public ResponseEntity<ApiResponse<EspecialidadResponse>> crear(
            @Valid @RequestBody EspecialidadRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Especialidad creada correctamente.",
                        especialidadService.crear(request)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EspecialidadResponse>>> listar() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Consulta realizada correctamente.",
                        especialidadService.obtenerTodos()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EspecialidadResponse>> obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Especialidad encontrada.",
                        especialidadService.obtenerPorId(id)
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EspecialidadResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EspecialidadRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Especialidad actualizada correctamente.",
                        especialidadService.actualizar(id, request)
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @PathVariable Long id) {

        especialidadService.eliminar(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Especialidad inactivada correctamente.",
                        null
                ));
    }
}