package com.mediturnos.controller.appointment;

import com.mediturnos.constant.ApiPaths;
import com.mediturnos.dto.appointment.response.CitaResponse;
import com.mediturnos.dto.common.ApiResponse;
import com.mediturnos.service.interfaces.appointment.RecepcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.RECEPCION)
@RequiredArgsConstructor
public class RecepcionController {

    private final RecepcionService service;

    @GetMapping("/citas")
    public ResponseEntity<ApiResponse<List<CitaResponse>>> citasDelDia(
            @RequestParam LocalDate fecha) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Consulta realizada correctamente.",
                        service.obtenerCitasDelDia(fecha)
                ));
    }
}