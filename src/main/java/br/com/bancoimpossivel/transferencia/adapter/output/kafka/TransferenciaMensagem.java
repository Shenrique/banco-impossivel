package br.com.bancoimpossivel.transferencia.adapter.output.kafka;

import br.com.bancoimpossivel.transferencia.domain.TransferenciaSolicitada;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransferenciaMensagem(UUID idEvento,
                                    String contaOrigem,
                                    String contaDestino,
                                    BigDecimal valor,
                                    Instant dataSolicitacao) {

    static TransferenciaMensagem de(TransferenciaSolicitada evento) {
        return new TransferenciaMensagem(
                evento.idEvento(),
                evento.contaOrigem(),
                evento.contaDestino(),
                evento.valor(),
                evento.dataSolicitacao());
    }
}