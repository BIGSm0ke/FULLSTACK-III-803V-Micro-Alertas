package com.microservicio.alertas.dto;

import java.io.Serializable;

public class AlertaDTO implements Serializable {
    private String mensaje;
    private String tipo; // Ejemplo: "INCENDIO FORESTAL"
    private String zona;  // Ejemplo: "Sector Norte"

    // Constructores, Getters y Setters
    public AlertaDTO() {}

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }
}
