# 🥷 Cadastro de Ninjas & Missões - API RESTful

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

Esta é uma aplicação completa para gerenciamento de Ninjas e suas respectivas Missões, desenvolvida com o ecossistema **Spring Boot**. O projeto evoluiu de uma estrutura básica para uma arquitetura robusta, focada em padrões de mercado e containerização.

## 🖼️ Demonstração Visual

### Documentação da API (Swagger)
<div style="display: flex; gap: 10px;">
  <img src="assets/swagger-ninjas.png" width="45%" alt="Swagger Ninjas">
  <img src="assets/swagger-missoes.png" width="45%" alt="Swagger Missões">
</div>

### Interface do Usuário (Thymeleaf)
<img src="assets/ui-lista.png" width="90%" alt="Interface da Lista de Ninjas">

## 🚀 Diferenciais Técnicos (O que aprendi/apliquei)

Nesta etapa do projeto, foquei em elevar a qualidade do código através de:

- **Arquitetura RESTful Real**: Refatoração de todos os endpoints para remover verbos das URLs, utilizando os métodos HTTP (`GET`, `POST`, `PUT`, `DELETE`) de forma semântica.
- **Tratamento de Nulidade com `Optional`**: Implementação do `java.util.Optional` na camada de Service, eliminando retornos nulos e tornando o código resiliente a `NullPointerException`.
- **Persistência com PostgreSQL & Docker**: Migração do banco H2 (em memória) para um banco de dados relacional real rodando em container.
- **Documentação com Swagger (OpenAPI 3)**: Documentação interativa completa para teste dos endpoints, com descrições detalhadas de operações e respostas.
- **Padrão DTO & MapStruct**: Separação clara entre as entidades do banco de dados e os objetos de transferência de dados (DTOs).

## 🛠️ Tecnologias Utilizadas

- **Backend**: Java 17, Spring Boot 3, Spring Data JPA.
- **Banco de Dados**: PostgreSQL.
- **Containerização**: Docker e Docker Compose.
- **Documentação**: SpringDoc OpenAPI.
- **Interface**: Thymeleaf (SSR) para a camada de UI.

## 📦 Como Executar o Projeto

Certifique-se de ter o **Docker Desktop** instalado e rodando.

1. Clone o repositório:
   ```bash
   git clone [https://github.com/ivanmtr/CadastroDeNinjas.git](https://github.com/ivanmtr/CadastroDeNinjas.git)