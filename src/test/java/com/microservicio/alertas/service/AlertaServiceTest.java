package com.microservicio.alertas.service;

import com.microservicio.alertas.model.Alerta;
import com.microservicio.alertas.repository.AlertaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertaServiceTest {

    @Mock
    private AlertaRepository alertaRepository;

    @InjectMocks
    private AlertaService alertaService;

    private Alerta alertaPrueba;

    @BeforeEach
    void setUp() {
        alertaPrueba = new Alerta("alta", "forestal", "humo_denso", "Av. Central 123", LocalDateTime.now());
        alertaPrueba.setId(1L);
    }

    @Test
    void findAll_SinFiltros_DebeLlamarFindAll() {
        when(alertaRepository.findAll()).thenReturn(List.of(alertaPrueba));

        List<Alerta> resultado = alertaService.findAll(null, null, null);

        assertFalse(resultado.isEmpty());
        verify(alertaRepository, times(1)).findAll();
        verify(alertaRepository, never()).findByFilters(any(), any(), any(), any());
    }

    @Test
    void findAll_ConFiltroSeveridad_DebeLlamarFindByFilters() {
        when(alertaRepository.findByFilters(eq("alta"), isNull(), isNull(), isNull()))
                .thenReturn(List.of(alertaPrueba));

        List<Alerta> resultado = alertaService.findAll("alta", null, null);

        assertFalse(resultado.isEmpty());
        verify(alertaRepository, never()).findAll();
        verify(alertaRepository, times(1)).findByFilters(eq("alta"), isNull(), isNull(), isNull());
    }

    @Test
    void findAll_ConFiltroFecha_DebeConvertirARango() {
        String fechaStr = "2026-05-06";
        when(alertaRepository.findByFilters(isNull(), isNull(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(List.of(alertaPrueba));

        List<Alerta> resultado = alertaService.findAll(null, null, fechaStr);

        assertFalse(resultado.isEmpty());
        verify(alertaRepository, times(1)).findByFilters(isNull(), isNull(), any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void findAll_ConFechaVacia_DebeIgnorarFiltro() {
        when(alertaRepository.findByFilters(eq("alta"), isNull(), isNull(), isNull()))
                .thenReturn(List.of(alertaPrueba));

        List<Alerta> resultado = alertaService.findAll("alta", null, "");

        assertFalse(resultado.isEmpty());
        verify(alertaRepository, times(1)).findByFilters(eq("alta"), isNull(), isNull(), isNull());
    }

    @Test
    void findAll_SinResultados_DebeRetornarListaVacia() {
        when(alertaRepository.findAll()).thenReturn(List.of());

        List<Alerta> resultado = alertaService.findAll(null, null, null);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void findById_CuandoExiste_DebeRetornarAlerta() {
        when(alertaRepository.findById(1L)).thenReturn(Optional.of(alertaPrueba));

        Optional<Alerta> resultado = alertaService.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("alta", resultado.get().getSeverity());
    }

    @Test
    void findById_CuandoNoExiste_DebeRetornarVacio() {
        when(alertaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Alerta> resultado = alertaService.findById(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void save_DebeGuardarYRetornar() {
        when(alertaRepository.save(any(Alerta.class))).thenReturn(alertaPrueba);

        Alerta resultado = alertaService.save(alertaPrueba);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(alertaRepository, times(1)).save(alertaPrueba);
    }

    @Test
    void save_SinTimestamp_DebeAsignarTimestamp() {
        Alerta alertaSinTimestamp = new Alerta("media", "urbano", "llamas", "Calle 456", null);

        when(alertaRepository.save(any(Alerta.class))).thenAnswer(invocation -> {
            Alerta a = invocation.getArgument(0);
            a.setId(2L);
            return a;
        });

        Alerta resultado = alertaService.save(alertaSinTimestamp);

        assertNotNull(resultado.getTimestamp());
        verify(alertaRepository, times(1)).save(alertaSinTimestamp);
    }
}
