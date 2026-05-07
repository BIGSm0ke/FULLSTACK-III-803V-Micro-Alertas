package com.microservicio.alertas.controller;

import com.microservicio.alertas.dto.AlertaResponse;
import com.microservicio.alertas.service.AlertaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alerts")
public class AlertaController {

    private final AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    @GetMapping
    public ResponseEntity<List<AlertaResponse>> getAlerts(
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String fireType,
            @RequestParam(required = false) String date) {

        List<AlertaResponse> alerts = alertaService.findAll(severity, fireType, date)
                .stream()
                .map(AlertaResponse::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaResponse> getById(@PathVariable Long id) {
        return alertaService.findById(id)
                .map(alerta -> ResponseEntity.ok(AlertaResponse.fromEntity(alerta)))
                .orElse(ResponseEntity.notFound().build());
    }
}
