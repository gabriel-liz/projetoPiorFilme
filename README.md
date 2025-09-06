Competição Pior Filme (Golden Raspberry Awards)

Projeto feito em **Spring Boot** pra importar os dados do Golden Raspberry Awards(categoria Pior Filme)
A ideia é importar os filmes de um CSV ao subir a aplicação e calcular quais produtores ganharam prêmios em intervalos menores e maiores de tempo.
Também haverá um end-point para que possam ser importados novos .csvs.

## O que usei

- Java 11+
- Spring Boot 2.7.9
- Maven
- Banco H2

## Requisitos

- [JDK 17](https://adoptium.net/) instalado (`java -version`)
- [Maven](https://maven.apache.org/) instalado (`mvn -version`)

## Como rodar

Clonar o repositorio e entra na pasta:

```bash
git clone https://github.com/seu-usuario/competicaoPiorFilme.git
cd competicaoPiorFilme

## Para rodar o projeto direto no maven
mvn spring-boot:run

Caso deseje acessar o banco:

Console do H2: http://localhost:8080/h2-console
Dados para acesso:
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password:

###############################################################################

Para rodar os testes:
mvn test

###############################################################################

Para acessar a documentação:
http://localhost:8080/swagger-ui/index.html#/

Documentação em JSON:
http://localhost:8080/v3/api-docs
```
