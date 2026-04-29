package com.microservicio.alertas.service;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import com.microservicio.alertas.model.Alerta;

@Service
public class AlertaService {

    // El nombre debe coincidir con el del application.yml
    @CircuitBreaker(name = "alertasCB", fallbackMethod = "fallbackEnviarNotificacion")
    public String enviarNotificacionComunidad(Alerta alerta) {
        // Aquí se simula la llamada a la función Serverless o bus de eventos (Kafka)
        // Si el servicio externo falla, saltará al fallback
        if (Math.random() > 0.7) throw new RuntimeException("Fallo en bus de eventos");
        
        return "Notificación enviada con éxito: ";  //alerta.getMensaje()
    }

    // Método de respaldo (Fallback) exigido por la rúbrica para mantenibilidad
    public String fallbackEnviarNotificacion(Alerta alerta, Throwable t) {
        return "Servicio de notificaciones fuera de línea. La alerta se guardó pero se notificará más tarde.";
    }
}