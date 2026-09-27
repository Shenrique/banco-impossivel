package br.com.bancoimpossivel.transferencia.application.port.output;

import br.com.bancoimpossivel.transferencia.domain.TransferenciaSolicitada;

public interface PublicarTransferenciaPort {

    void publicar(TransferenciaSolicitada evento);
}