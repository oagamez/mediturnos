package com.mediturnos.controller;

import com.mediturnos.dto.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public ApiResponse<String> health() {

        return new ApiResponse<>(
                true,
                "API MediTurnos funcionando correctamente",
                "Bienvenido a MediTurnos"
        );
    }

}