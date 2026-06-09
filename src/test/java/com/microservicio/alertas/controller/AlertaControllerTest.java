package com.microservicio.alertas.controller;

import com.microservicio.alertas.model.Alerta;
import com.microservicio.alertas.service.AlertaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlertaController.class)
class AlertaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlertaService alertaService;

    @Test
    void getAlerts_SinFiltros_DebeRetornar200() throws Exception {
        Alerta alerta = new Alerta("alta", "forestal", "humo", "Direccion 123", LocalDateTime.now());
        alerta.setId(1L);

        when(alertaService.findAll(isNull(), isNull(), isNull())).thenReturn(List.of(alerta));

        mockMvc.perform(get("/api/alerts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].severity").value("alta"));
    }

    @Test
    void getAlerts_ConFiltros_DebeRetornar200() throws Exception {
        Alerta alerta = new Alerta("critica", "forestal", "humo_denso", "Direccion 456", LocalDateTime.now());
        alerta.setId(2L);

        when(alertaService.findAll(eq("critica"), eq("forestal"), isNull())).thenReturn(List.of(alerta));

        mockMvc.perform(get("/api/alerts")
                        .param("severity", "critica")
                        .param("fireType", "forestal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].severity").value("critica"));
    }

    @Test
    void getAlerts_SinResultados_DebeRetornar200ListaVacia() throws Exception {
        when(alertaService.findAll(isNull(), isNull(), isNull())).thenReturn(List.of());

        mockMvc.perform(get("/api/alerts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getById_CuandoExiste_DebeRetornar200() throws Exception {
        Alerta alerta = new Alerta("media", "vehiculo", "llamas", "Calle 789", LocalDateTime.now());
        alerta.setId(5L);

        when(alertaService.findById(5L)).thenReturn(Optional.of(alerta));

        mockMvc.perform(get("/api/alerts/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.fireType").value("vehiculo"));
    }

    @Test
    void getById_CuandoNoExiste_DebeRetornar404() throws Exception {
        when(alertaService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/alerts/99"))
                .andExpect(status().isNotFound());
    }
}
