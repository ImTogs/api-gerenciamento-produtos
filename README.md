# API REST de Gerenciamento de Produtos

API REST desenvolvida em Java utilizando Spring Boot para gerenciamento de produtos.

O projeto foi desenvolvido com o objetivo de aplicar conceitos de Programação Orientada a Objetos (POO), arquitetura em camadas e desenvolvimento de APIs REST, seguindo boas práticas de organização e manutenção de código.

---

## Tecnologias

- Java 17
- Spring Boot 3.2
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- Swagger / OpenAPI
- Maven

---

## Arquitetura

O projeto está organizado em camadas:

```
src/main/java
│
├── config
├── controller
├── exception
├── model
├── repository
└── service
```

---

## Funcionalidades

- Cadastro de produtos
- Listagem de produtos
- Busca por ID
- Atualização de produtos
- Exclusão de produtos
- Validação dos dados recebidos
- Tratamento global de exceções
- Documentação automática da API com Swagger

---

## Modelo da entidade

Produto

- id
- nome
- descricao
- preco
- quantidade
- categoria

---

## Banco de dados

Durante o desenvolvimento é utilizado o banco H2 em memória.

Console H2:

```
http://localhost:8080/h2-console
```

---

## Documentação da API

Após iniciar a aplicação, a documentação pode ser acessada em:

```
http://localhost:8080/swagger-ui.html
```

---

## Como executar

Clone o projeto:

```bash
git clone https://github.com/ImTogs/api-gerenciamento-produtos.git
```

Entre na pasta:

```bash
cd api-gerenciamento-produtos
```

Execute:

```bash
mvn spring-boot:run
```

ou execute diretamente pela sua IDE.

---

## Objetivo

Projeto desenvolvido para prática dos conceitos de:

- Programação Orientada a Objetos
- Desenvolvimento de APIs REST
- Spring Boot
- Spring Data JPA
- Persistência de dados
- Documentação de APIs com Swagger/OpenAPI

---

## Autor

**Kauã Henrique Rocha**

Estudante de Análise e Desenvolvimento de Sistemas, com foco em desenvolvimento backend utilizando Java e Spring Boot.