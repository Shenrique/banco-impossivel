package br.com.bancoimpossivel.transferencia.application.port.output;

import java.util.UUID;

public class PublicacaoTransferenciaException extends RuntimeException {

    public PublicacaoTransferenciaException(UUID idEvento, Throwable causa) {
        super("Falha ao publicar a transferência " + idEvento, causa);
    }
}