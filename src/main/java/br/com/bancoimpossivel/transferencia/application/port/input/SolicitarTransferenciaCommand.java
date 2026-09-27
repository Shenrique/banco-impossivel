package br.com.bancoimpossivel.transferencia.application.port.input;

import java.math.BigDecimal;

public record SolicitarTransferenciaCommand(String contaOrigem,
                                            String contaDestino,
                                            BigDecimal valor) {
}