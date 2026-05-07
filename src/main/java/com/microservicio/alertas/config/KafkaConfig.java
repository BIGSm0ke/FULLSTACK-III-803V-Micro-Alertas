package com.microservicio.alertas.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
public class KafkaConfig {

    public static final String ALERTA_TOPIC = "alertas-eventos";

    @Bean
    public NewTopic alertaTopic() {
        return TopicBuilder.name(ALERTA_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
