Competição Pior Filme (Golden Raspberry Awards)

Projeto feito em **Spring Boot** pra importar os dados do Golden Raspberry Awards(categoria Pior Filme)
A ideia é importar os filmes de um CSV ao subir a aplicação e calcular quais produtores ganharam prêmios em intervalos menores e maiores de tempo.
End-Points criados:
GET /filmes -> Lista todos os filmes
GET /filmes/premios/intervalo -> Busca os produtores que ganharam prêmios em intervalos menores e maiores de tempo
POST /filmes/importar -> Importar novas planilhas .csv 


## Requisitos

- [JDK 17](https://adoptium.net/) instalado (`java -version`)
- [Maven](https://maven.apache.org/) instalado (`mvn -version`)

## Como rodar

Clonar o repositório e entrar na pasta:

bash

git clone https://github.com/seu-usuario/competicaoPiorFilme.git
cd competicaoPiorFilme

## Para rodar o projeto direto no maven
mvn spring-boot:run

## Caso deseje acessar o banco:

Console do H2: http://localhost:8080/h2-console

Dados para acesso:

JDBC URL: jdbc:h2:mem:testdb

User: sa

Password:

## Para rodar os testes:
mvn test


## Para acessar a documentação e testar:
	Documentação Swagger: http://localhost:8080/swagger-ui/index.html#/

	Documentação em JSON: http://localhost:8080/v3/api-docs






