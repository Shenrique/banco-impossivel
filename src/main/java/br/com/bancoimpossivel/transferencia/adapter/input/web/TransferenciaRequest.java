package br.com.bancoimpossivel.transferencia.adapter.input.web;

import br.com.bancoimpossivel.transferencia.application.port.input.SolicitarTransferenciaCommand;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferenciaRequest(@NotBlank String contaOrigem,
                                   @NotBlank String contaDestino,
                                   @NotNull @Positive @Digits(integer = 15, fraction = 2) BigDecimal valor) {

    SolicitarTransferenciaCommand paraCommand() {
        return new SolicitarTransferenciaCommand(contaOrigem, contaDestino, valor);
    }
}