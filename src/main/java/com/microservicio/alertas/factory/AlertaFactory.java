package com.microservicio.alertas.factory;

import com.microservicio.alertas.dto.AlertaDTO;
import com.microservicio.alertas.model.Alerta;
import org.springframework.stereotype.Component;

@Component
public class AlertaFactory {

    public Alerta requestToEntity(AlertaDTO request) {
        return Alerta.builder()
                .titulo(request.getTitulo())
                .descripcion(request.getDescripcion())
                .tipo(request.getTipo())
                .severidad(request.getSeveridad())
                .build();
    }
}
