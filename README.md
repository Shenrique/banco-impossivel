# Banco Impossível

![CI](https://github.com/Shenrique/banco-impossivel/actions/workflows/ci.yml/badge.svg)

Banco digital fictício usado como laboratório permanente de engenharia de software. O projeto nunca fica "pronto": cada fase acrescenta um conceito novo, sempre com uma entrega pequena, testada e documentada.

## Stack atual (F0)

Java 21 · Spring Boot 3.5 · PostgreSQL 16 · Testcontainers · Docker Compose · GitHub Actions

## Como rodar

Pré-requisitos: Docker e Java 21.

```bash
# Tudo em container (Postgres + aplicação)
docker compose up --build

# Verificar
curl http://localhost:8080/actuator/health
```

Para desenvolver pela IDE, suba só o banco e rode a aplicação localmente:

```bash
docker compose up -d postgres
mvn spring-boot:run
```

## Testes

```bash
mvn verify
```

Os testes sobem um PostgreSQL real via Testcontainers (é preciso ter o Docker rodando).

## Roadmap

| Fase | Tema | Status |
| --- | --- | --- |
| F0 | Setup e fundações | em andamento |
| F1 | Núcleo bancário: contas, ledger, transferências | — |
| F2 | Segurança: Keycloak, OAuth2, auditoria | — |
| F3 | Eventos: Kafka, outbox | — |
| F4 | Microsserviços: saga, gateway | — |
| F5 | Observabilidade: OpenTelemetry, Grafana, k6 | — |
| F6 | AWS com Terraform | — |
| F7 | Kubernetes e GitOps | — |
| F8 | Agente de IA | — |

## Decisões de arquitetura

As decisões ficam em [`docs/adr`](docs/adr). Comece pela [ADR-0001](docs/adr/0001-monolito-modular.md).
