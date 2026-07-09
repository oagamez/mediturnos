package com.mediturnos.controller.medical;

import com.mediturnos.constant.ApiPaths;
import com.mediturnos.dto.common.ApiResponse;
import com.mediturnos.dto.medical.request.MedicoRequest;
import com.mediturnos.dto.medical.response.MedicoResponse;
import com.mediturnos.service.interfaces.medical.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.MEDICOS)
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService service;

    @PostMapping
    public ResponseEntity<ApiResponse<MedicoResponse>> crear(@Valid @RequestBody MedicoRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Médico creado correctamente.", service.crear(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MedicoResponse>>> listar() {

        return ResponseEntity.ok(
                ApiResponse.success("Consulta realizada correctamente.", service.obtenerTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicoResponse>> obtener(@PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success("Médico encontrado.", service.obtenerPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicoResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody MedicoRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success("Médico actualizado correctamente.",
                        service.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok(
                ApiResponse.success("Médico inactivado correctamente.", null));
    }

}