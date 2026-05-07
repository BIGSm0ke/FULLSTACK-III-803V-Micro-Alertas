package com.microservicio.alertas.repository;

import com.microservicio.alertas.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    List<Alerta> findBySeverity(String severity);

    List<Alerta> findByFireType(String fireType);

    List<Alerta> findByTimestampBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT a FROM Alerta a WHERE " +
           "(:severity IS NULL OR a.severity = :severity) AND " +
           "(:fireType IS NULL OR a.fireType = :fireType) AND " +
           "(:start IS NULL OR a.timestamp >= :start) AND " +
           "(:end IS NULL OR a.timestamp <= :end) " +
           "ORDER BY a.timestamp DESC")
    List<Alerta> findByFilters(
            @Param("severity") String severity,
            @Param("fireType") String fireType,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);
}
