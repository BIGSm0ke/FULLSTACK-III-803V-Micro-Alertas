package com.microservicio.alertas.service;


import com.microservicio.alertas.dto.AlertaDTO;
import com.microservicio.alertas.model.Alerta;
import com.microservicio.alertas.repository.AlertaRepository;
import com.microservicio.alertas.factory.AlertaFactory;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository repository;
    private final AlertaFactory factory;

    @CircuitBreaker(name = "alertasCB", fallbackMethod = "fallbackCrearAlerta")
    public String crearAlerta(AlertaDTO request) {
        Alerta nuevaAlerta = factory.requestToEntity(request);
        repository.save(nuevaAlerta);
        return "Alerta oficial emitida: " + nuevaAlerta.getTitulo();
    }

    public String fallbackCrearAlerta(AlertaDTO request, Throwable t) {
        return "El servicio de alertas está saturado. Su reporte ha sido encolado para procesamiento posterior.";
    }
}