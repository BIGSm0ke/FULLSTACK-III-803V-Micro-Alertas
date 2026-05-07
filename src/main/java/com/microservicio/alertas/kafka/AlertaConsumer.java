package com.microservicio.alertas.kafka;

import com.microservicio.alertas.model.Alerta;
import com.microservicio.alertas.service.AlertaService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
public class AlertaConsumer {

    private final AlertaService alertaService;

    public AlertaConsumer(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    public void consumeAlerta(Alerta alerta) {
        alertaService.save(alerta);
    }
}
