package br.com.bancoimpossivel.transferencia;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record TransferenciaSolicitada(UUID idEvento,
                                      String contaOrigem,
                                      String contaDestino,
                                      BigDecimal valor,
                                      Instant dataSolicitacao) {

    public TransferenciaSolicitada {
        Objects.requireNonNull(idEvento, "idEvento é obrigatório");
        Objects.requireNonNull(contaOrigem, "contaOrigem é obrigatória");
        Objects.requireNonNull(contaDestino, "contaDestino é obrigatória");
        Objects.requireNonNull(valor, "valor é obrigatório");
        Objects.requireNonNull(dataSolicitacao, "dataSolicitacao é obrigatória");

        if (contaOrigem.isBlank()) {
            throw new IllegalArgumentException("contaOrigem não pode ser vazia");
        }
        if (contaDestino.isBlank()) {
            throw new IllegalArgumentException("contaDestino não pode ser vazia");
        }
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("valor deve ser maior que zero");
        }
        if (valor.scale() > 2) {
            throw new IllegalArgumentException("valor deve ter no máximo 2 casas decimais");
        }
        if (contaOrigem.equals(contaDestino)) {
            throw new IllegalArgumentException("contaOrigem e contaDestino devem ser diferentes");
        }
    }
}