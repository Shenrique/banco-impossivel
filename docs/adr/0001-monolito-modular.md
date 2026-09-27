# ADR-0001: Começar com um monolito modular

- **Status:** aceito
- **Data:** 2026-09-27
- **Fase:** F0

## Contexto

O Banco Impossível vai terminar distribuído (microsserviços, Kafka, saga). No início, porém, as fronteiras entre os domínios (Contas, Ledger, Transferências, Extrato) ainda são hipóteses. Errar uma fronteira em microsserviços custa caro: contratos de API, consistência distribuída e deploy separado para corrigir.

## Decisão

Começar com uma única aplicação Spring Boot, organizada em módulos por domínio (um pacote por bounded context), com dependências entre módulos explícitas e verificadas por teste (ArchUnit, a partir da F1).

## Alternativas consideradas

- **Microsserviços desde o dia 1:** alto custo operacional antes de existir regra de negócio; fronteiras seriam chutes.
- **Monolito sem módulos:** rápido no começo, mas a extração na F4 viraria reescrita.

## Consequências

- Transações ACID locais na F1, o que simplifica o ledger em partida dobrada.
- A F4 (microsserviços) vira uma extração guiada por fronteiras já testadas, não um redesenho.
- Exige disciplina: nenhum módulo acessa tabela ou classe interna de outro módulo.
- Revisar esta decisão quando um módulo precisar escalar ou fazer deploy de forma independente.
