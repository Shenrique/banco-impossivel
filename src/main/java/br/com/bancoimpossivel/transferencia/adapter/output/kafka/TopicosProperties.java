package br.com.bancoimpossivel.transferencia.adapter.output.kafka;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "banco.kafka.topicos")
public record TopicosProperties(@NotBlank String transferencias) {
}