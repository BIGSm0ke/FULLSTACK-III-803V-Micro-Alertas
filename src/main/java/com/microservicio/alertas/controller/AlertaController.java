package com.microservicio.alertas.controller;


import com.microservicio.alertas.dto.AlertaDTO;
import com.microservicio.alertas.service.AlertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alertas")
@RequiredArgsConstructor
public class AlertaController {

    private final AlertaService alertaService;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarAlerta(@RequestBody AlertaDTO request) {
        return ResponseEntity.ok(alertaService.crearAlerta(request));
    }
}