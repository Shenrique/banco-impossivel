package br.com.bancoimpossivel.adapter.output.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("local")
public class KafkaTopicLocalConfig {

    @Bean
    NewTopic transferenciasSolicitadas(TopicosProperties topicos) {
        return TopicBuilder.name(topicos.transferencias())
                .partitions(3)
                .replicas(1)
                .build();
    }
}