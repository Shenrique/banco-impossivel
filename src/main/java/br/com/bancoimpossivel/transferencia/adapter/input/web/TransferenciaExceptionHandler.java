package br.com.bancoimpossivel.transferencia.adapter.input.web;

import br.com.bancoimpossivel.transferencia.application.port.output.PublicacaoTransferenciaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = TransferenciaController.class)
public class TransferenciaExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(TransferenciaExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    ProblemDetail regraViolada(IllegalArgumentException e) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problema.setTitle("Transferência inválida");
        return problema;
    }

    @ExceptionHandler(PublicacaoTransferenciaException.class)
    ProblemDetail publicacaoFalhou(PublicacaoTransferenciaException e) {
        log.error("Falha ao publicar transferência", e);
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Não foi possível registrar a transferência agora. Tente novamente.");
        problema.setTitle("Serviço indisponível");
        return problema;
    }
}