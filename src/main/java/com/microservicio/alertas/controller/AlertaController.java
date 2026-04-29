package com.microservicio.alertas.controller;

import com.microservicio.alertas.model.Alerta;
import com.microservicio.alertas.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @PostMapping("/enviar")
    public String emitirAlerta(@RequestBody Alerta alerta) {
        return alertaService.enviarNotificacionComunidad(alerta);
    }
}
