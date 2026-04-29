package com.microservicio.alertas.dto;

import lombok.Data;

@Data
public class AlertaDTO {
    private String titulo;
    private String descripcion;
    private String tipo;
    private String severidad;
}
