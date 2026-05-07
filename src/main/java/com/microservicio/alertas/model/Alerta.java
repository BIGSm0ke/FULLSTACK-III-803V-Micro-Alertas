package com.microservicio.alertas.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String severity;

    @Column(nullable = false)
    private String fireType;

    @Column(nullable = false)
    private String visible;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    private Long reporteId;

    public Alerta() {}

    public Alerta(String severity, String fireType, String visible, String address, LocalDateTime timestamp) {
        this.severity = severity;
        this.fireType = fireType;
        this.visible = visible;
        this.address = address;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getFireType() { return fireType; }
    public void setFireType(String fireType) { this.fireType = fireType; }

    public String getVisible() { return visible; }
    public void setVisible(String visible) { this.visible = visible; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Long getReporteId() { return reporteId; }
    public void setReporteId(Long reporteId) { this.reporteId = reporteId; }
}
