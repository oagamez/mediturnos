package com.mediturnos.controller.security;

import com.mediturnos.constant.ApiPaths;
import com.mediturnos.dto.common.ApiResponse;
import com.mediturnos.dto.security.request.UsuarioRequest;
import com.mediturnos.dto.security.response.UsuarioResponse;
import com.mediturnos.service.interfaces.security.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.USUARIOS)
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponse>> crear(
            @Valid @RequestBody UsuarioRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Usuario creado correctamente.",
                        usuarioService.crear(request)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponse>>> listar() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Consulta realizada correctamente.",
                        usuarioService.obtenerTodos()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponse>> obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Usuario encontrado.",
                        usuarioService.obtenerPorId(id)
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Usuario actualizado correctamente.",
                        usuarioService.actualizar(id, request)
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @PathVariable Long id) {

        usuarioService.eliminar(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Usuario inactivado correctamente.",
                        null
                ));
    }

}