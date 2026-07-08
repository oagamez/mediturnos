package com.mediturnos.controller.security;

import com.mediturnos.constant.ApiPaths;
import com.mediturnos.dto.common.ApiResponse;
import com.mediturnos.dto.security.request.RolRequest;
import com.mediturnos.dto.security.response.RolResponse;
import com.mediturnos.service.interfaces.security.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.ROLES)
@RequiredArgsConstructor
public class RolController {

    private final RolService service;

    @PostMapping
    public ResponseEntity<ApiResponse<RolResponse>> crear(
            @Valid @RequestBody RolRequest request) {

        RolResponse response = service.crear(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Rol creado correctamente.", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RolResponse>>> obtenerTodos() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Consulta realizada correctamente.",
                        service.obtenerTodos()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RolResponse>> obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Rol encontrado.",
                        service.obtenerPorId(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RolResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody RolRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Rol actualizado correctamente.",
                        service.actualizar(id, request)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Rol inactivado correctamente.",
                        null
                )
        );
    }

}