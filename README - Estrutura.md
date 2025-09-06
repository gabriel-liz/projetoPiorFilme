## O que usei

- Java 11+
- Spring Boot 2.7.9
- Maven
- Banco H2


## Estrutura do Projeto

Estrutura do Projeto

No projeto eu organizei os pacotes dessa forma:

api → aqui ficaram as partes ligadas à entrada e saída de dados da aplicação.

	controller: onde estão os controllers REST, no caso desse desafio, só FilmeController.

	model: usei esse pacote pros DTOs (entrada e saída).

	assembler: classes responsáveis por converter entre as entidades e os DTOs.

core → coloquei configurações globais. Para esse desafio, o ModelMapperConfig que centraliza o bean do ModelMapper.

domain → parte de domínio da aplicação.

	config: deixei aqui a classe DataInitializer, que importar o .csv ao subir a aplicação

	model: onde estão as entidades principais (Estudio, Filme e Produtor).

	repository: interfaces JPA de cada entidade.

	service: regras de negócio e importacao da planilha. 