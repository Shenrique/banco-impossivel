package br.com.bancoimpossivel.spike.kafka;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransferenciaSolicitada(
        @NotNull UUID idEvento,
        @NotBlank String contaOrigem,
        @NotBlank String contaDestino,
        @NotNull @Positive @Digits(integer = 15, fraction = 2) BigDecimal valor,
        @NotNull Instant dataSolicitacao) {

    @AssertTrue(message = "contaOrigem e contaDestino devem ser diferentes")
    boolean isContasDiferentes() {
        return contaOrigem == null || !contaOrigem.equals(contaDestino);
    }
}