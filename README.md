# Teste back-end - API REST com Spring
## Por Pedro Augusto

Projeto em Java com framework Spring Boot para criar uma API REST, utilizando JPA, PostgreSQL, Maven.

### Informações do Projeto

Nome do Projeto: API

Descrição: Back end para eventos.

Versão do Spring Boot: 3.3.2

Versão do Java: 21

### Funções da API

Realizar a criação de um evento;

Realizar a criação de um usuário;

Realizar a operação de inscrição de um usuário em um evento;

Realizar o cancelamento de uma inscrição de um usuário em um evento;

Listar as inscrições de um usuário;

Listar os inscritos de um evento;

### Banco de Dados

Produção: A aplicação utiliza o Neon DB, um serviço de banco de dados PostgreSQL na nuvem.

Testes: Para fins de testes, a aplicação usa o H2, um banco de dados em memória.

### Testes

Os testes da aplicação são realizados utilizando:

JUnit: Para estruturar e executar os testes unitários e de integração.

Mockito: Para criar mocks e simular comportamentos durante os testes, facilitando a criação de testes isolados e a verificação das interações com as dependências.

### Execução do Projeto

Importar: Importe o projeto como um projeto Maven.

Atualizar Dependências: Baixe e atualize as dependências.

Executar: Execute a classe principal como uma aplicação Java.

### Arquivo de Configuração

Crie o arquivo resource

src/main/resources/application.properties

Edite o Arquivo application.properties

spring.application.name=api
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://ep-curly-firefly-a5531i12.us-east-2.aws.neon.tech/events?sslmode=require
spring.datasource.username=events_owner
spring.datasource.password=D6lr5GSztIqn
spring.jpa.hibernate.ddl-auto=update

### Developer

Pedro Augusto
pedroaugustolopesgomes@gmail.com
