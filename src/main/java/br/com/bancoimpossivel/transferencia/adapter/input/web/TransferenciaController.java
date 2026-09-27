package br.com.bancoimpossivel.transferencia.adapter.input.web;

import br.com.bancoimpossivel.transferencia.application.port.input.SolicitarTransferenciaUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final SolicitarTransferenciaUseCase solicitarTransferenciaUseCase;

    public TransferenciaController(SolicitarTransferenciaUseCase solicitarTransferenciaUseCase) {
        this.solicitarTransferenciaUseCase = solicitarTransferenciaUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransferenciaResponse solicitar(@Valid @RequestBody TransferenciaRequest request) {
        UUID idEvento = solicitarTransferenciaUseCase.solicitar(request.paraCommand());
        return new TransferenciaResponse(idEvento);
    }
}