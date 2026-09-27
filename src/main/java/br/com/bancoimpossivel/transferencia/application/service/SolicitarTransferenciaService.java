package br.com.bancoimpossivel.transferencia.application.service;

import br.com.bancoimpossivel.transferencia.application.port.input.SolicitarTransferenciaCommand;
import br.com.bancoimpossivel.transferencia.application.port.input.SolicitarTransferenciaUseCase;
import br.com.bancoimpossivel.transferencia.application.port.output.PublicarTransferenciaPort;
import br.com.bancoimpossivel.transferencia.domain.TransferenciaSolicitada;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.UUID;

@Service
public class SolicitarTransferenciaService implements SolicitarTransferenciaUseCase {

    private final PublicarTransferenciaPort publicarTransferenciaPort;
    private final Clock clock;

    public SolicitarTransferenciaService(PublicarTransferenciaPort publicarTransferenciaPort,
                                         Clock clock) {
        this.publicarTransferenciaPort = publicarTransferenciaPort;
        this.clock = clock;
    }

    @Override
    public UUID solicitar(SolicitarTransferenciaCommand command) {
        TransferenciaSolicitada evento = TransferenciaSolicitada.nova(
                command.contaOrigem(),
                command.contaDestino(),
                command.valor(),
                clock);

        publicarTransferenciaPort.publicar(evento);
        return evento.idEvento();
    }
}