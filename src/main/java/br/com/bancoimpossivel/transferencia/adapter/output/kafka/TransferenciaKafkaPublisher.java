package br.com.bancoimpossivel.transferencia.adapter.output.kafka;

import br.com.bancoimpossivel.transferencia.application.port.output.PublicacaoTransferenciaException;
import br.com.bancoimpossivel.transferencia.application.port.output.PublicarTransferenciaPort;
import br.com.bancoimpossivel.transferencia.domain.TransferenciaSolicitada;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class TransferenciaKafkaPublisher implements PublicarTransferenciaPort {

    private static final Logger log = LoggerFactory.getLogger(TransferenciaKafkaPublisher.class);
    private static final long TIMEOUT_SEGUNDOS = 10;

    private final KafkaTemplate<String, TransferenciaMensagem> kafkaTemplate;
    private final TopicosProperties topicos;

    public TransferenciaKafkaPublisher(KafkaTemplate<String, TransferenciaMensagem> kafkaTemplate,
                                       TopicosProperties topicos) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicos = topicos;
    }

    @Override
    public void publicar(TransferenciaSolicitada evento) {
        var topico = topicos.transferencias();
        var chave = evento.contaOrigem();
        var mensagem = TransferenciaMensagem.de(evento);

        log.info("Publicando no tópico '{}' | chave={} | idEvento={} | origem={} | destino={} | valor={} | dataSolicitacao={}",
                topico, chave, mensagem.idEvento(), mensagem.contaOrigem(),
                mensagem.contaDestino(), mensagem.valor(), mensagem.dataSolicitacao());

        try {
            SendResult<String, TransferenciaMensagem> resultado = kafkaTemplate
                    .send(topico, chave, mensagem)
                    .get(TIMEOUT_SEGUNDOS, TimeUnit.SECONDS);

            RecordMetadata metadata = resultado.getRecordMetadata();
            log.info("Publicado no tópico '{}' | idEvento={} | partição={} | offset={}",
                    metadata.topic(), evento.idEvento(), metadata.partition(), metadata.offset());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new PublicacaoTransferenciaException(evento.idEvento(), e);
        } catch (ExecutionException | TimeoutException e) {
            throw new PublicacaoTransferenciaException(evento.idEvento(), e);
        }
    }
}