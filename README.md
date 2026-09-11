# courseJDBC

Projeto Java com exemplos básicos de uso do JDBC para acesso e manipulação de dados em MySQL.

## O que está aqui

O código demonstra operações fundamentais de banco de dados, como:

- conexão com o banco via `DriverManager`
- leitura de dados (`SELECT`)
- inserção de registros (`INSERT`)
- atualização (`UPDATE`)
- remoção (`DELETE`)
- transações (`commit` e `rollback`)

Os exemplos estão em `src/application`, e a configuração da conexão fica em `db.properties` (baseado em `db.properties.example`).

## Pré-requisitos

- Java JDK
- MySQL em execução
- banco de dados `coursejdbc`
- usuário e senha configurados em `db.properties`

## Observação

Este projeto é um conjunto de exemplos didáticos de JDBC, com foco em operações básicas e no entendimento do fluxo de acesso a dados em Java, sem uso de frameworks ORM.
