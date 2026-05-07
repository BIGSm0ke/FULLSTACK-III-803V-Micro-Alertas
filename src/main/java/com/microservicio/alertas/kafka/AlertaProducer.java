package com.microservicio.alertas.kafka;

import com.microservicio.alertas.model.Alerta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
public class AlertaProducer {

    @Autowired(required = false)
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendAlerta(Alerta alerta) {
        if (kafkaTemplate != null) {
            kafkaTemplate.send("alertas-eventos", alerta.getId().toString(), alerta);
        }
    }
}
