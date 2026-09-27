
import br.com.bancoimpossivel.application.port.input.SolicitarTransferenciaCommand;

import java.util.UUID;

public interface SolicitarTransferenciaUseCase {

    UUID solicitar(SolicitarTransferenciaCommand command);
}