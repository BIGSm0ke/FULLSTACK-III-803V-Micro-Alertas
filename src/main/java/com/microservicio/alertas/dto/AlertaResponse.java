package com.microservicio.alertas.dto;

import com.microservicio.alertas.model.Alerta;
import java.time.format.DateTimeFormatter;

public class AlertaResponse {

    private Long id;
    private String severity;
    private String fireType;
    private String visible;
    private String address;
    private String timestamp;

    public AlertaResponse() {}

    public static AlertaResponse fromEntity(Alerta alerta) {
        AlertaResponse dto = new AlertaResponse();
        dto.id = alerta.getId();
        dto.severity = alerta.getSeverity();
        dto.fireType = alerta.getFireType();
        dto.visible = alerta.getVisible();
        dto.address = alerta.getAddress();
        dto.timestamp = alerta.getTimestamp() != null
                ? alerta.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                : null;
        return dto;
    }

    public Long getId() { return id; }
    public String getSeverity() { return severity; }
    public String getFireType() { return fireType; }
    public String getVisible() { return visible; }
    public String getAddress() { return address; }
    public String getTimestamp() { return timestamp; }
}
