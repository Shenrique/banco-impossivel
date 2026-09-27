package br.com.bancoimpossivel.transferencia.application.port.input;

import java.util.UUID;

public interface SolicitarTransferenciaUseCase {

    UUID solicitar(SolicitarTransferenciaCommand command);
}