package br.com.bancoimpossivel;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Prova de vida da F0: a aplicacao sobe, conecta num Postgres real
 * e o health check reporta o banco como UP.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
class HealthCheckTest {

    @Autowired
    TestRestTemplate rest;

    @Test
    @SuppressWarnings("unchecked")
    void aplicacaoSobeEConectaNoBanco() {
        ResponseEntity<Map> response = rest.getForEntity("/actuator/health", Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsEntry("status", "UP");

        Map<String, Object> components = (Map<String, Object>) response.getBody().get("components");
        Map<String, Object> db = (Map<String, Object>) components.get("db");
        assertThat(db).containsEntry("status", "UP");
    }
}
