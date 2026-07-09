package com.mediturnos.controller.medical;

import com.mediturnos.constant.ApiPaths;
import com.mediturnos.dto.common.ApiResponse;
import com.mediturnos.dto.medical.request.ConsultorioRequest;
import com.mediturnos.dto.medical.response.ConsultorioResponse;
import com.mediturnos.service.interfaces.medical.ConsultorioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.CONSULTORIOS)
@RequiredArgsConstructor
public class ConsultorioController {

    private final ConsultorioService service;

    @PostMapping
    public ResponseEntity<ApiResponse<ConsultorioResponse>> crear(
            @Valid @RequestBody ConsultorioRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Consultorio creado correctamente.",
                        service.crear(request)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ConsultorioResponse>>> listar() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Consulta realizada correctamente.",
                        service.obtenerTodos()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ConsultorioResponse>> obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Consultorio encontrado.",
                        service.obtenerPorId(id)
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ConsultorioResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ConsultorioRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Consultorio actualizado correctamente.",
                        service.actualizar(id, request)
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Consultorio inactivado correctamente.",
                        null
                ));
    }

}