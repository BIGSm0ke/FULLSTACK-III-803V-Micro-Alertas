package com.microservicio.alertas.service;

import com.microservicio.alertas.model.Alerta;
import com.microservicio.alertas.repository.AlertaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;

    public AlertaService(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    public List<Alerta> findAll(String severity, String fireType, String date) {
        if (severity == null && fireType == null && date == null) {
            return alertaRepository.findAll();
        }

        LocalDateTime start = null;
        LocalDateTime end = null;

        if (date != null && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date);
            start = localDate.atStartOfDay();
            end = localDate.atTime(LocalTime.MAX);
        }

        return alertaRepository.findByFilters(severity, fireType, start, end);
    }

    public Optional<Alerta> findById(Long id) {
        return alertaRepository.findById(id);
    }

    public Alerta save(Alerta alerta) {
        if (alerta.getTimestamp() == null) {
            alerta.setTimestamp(LocalDateTime.now());
        }
        return alertaRepository.save(alerta);
    }
}
