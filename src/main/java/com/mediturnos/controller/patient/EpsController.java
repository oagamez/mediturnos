package com.mediturnos.controller.patient;

import com.mediturnos.constant.ApiPaths;
import com.mediturnos.dto.common.ApiResponse;
import com.mediturnos.dto.patient.request.EpsRequest;
import com.mediturnos.dto.patient.response.EpsResponse;
import com.mediturnos.service.interfaces.patient.EpsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.EPS)
@RequiredArgsConstructor
public class EpsController {

    private final EpsService service;

    @PostMapping
    public ResponseEntity<ApiResponse<EpsResponse>> crear(
            @Valid @RequestBody EpsRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "EPS creada correctamente.",
                        service.crear(request)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EpsResponse>>> listar() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Consulta realizada correctamente.",
                        service.obtenerTodos()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EpsResponse>> obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "EPS encontrada.",
                        service.obtenerPorId(id)
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EpsResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EpsRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "EPS actualizada correctamente.",
                        service.actualizar(id, request)
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "EPS inactivada correctamente.",
                        null
                ));
    }
}