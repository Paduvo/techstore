# TechStore

E-commerce full stack desenvolvido como projeto de portfólio, com frontend em React, backend em Spring Boot e banco de dados PostgreSQL.

## Sobre o projeto

A TechStore simula uma loja de produtos de tecnologia com fluxo completo de compra: autenticação de usuários, listagem de produtos, carrinho, checkout, criação de pedidos e controle de estoque.

O projeto foi desenvolvido com foco em praticar integração entre frontend e backend, autenticação com JWT, regras de negócio e persistência de dados.

## Tecnologias

### Backend
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- Bean Validation
- PostgreSQL
- Maven
- Swagger / OpenAPI

### Frontend
- React
- Vite
- JavaScript
- CSS
- Fetch API

## Funcionalidades

- Cadastro e autenticação de usuários
- Senhas protegidas com BCrypt
- Login com geração de token JWT
- Proteção de rotas no backend
- CRUD de produtos
- Validação de dados
- Tratamento global de erros
- Carrinho de compras
- Alteração de quantidade e remoção de itens
- Checkout autenticado
- Criação de pedidos
- Associação do pedido ao usuário autenticado
- Cálculo automático do valor total
- Registro do preço do produto no momento da compra
- Atualização automática do estoque
- Consulta de pedidos do usuário
- Documentação da API com Swagger

## Arquitetura

```text
React
  ↓
API REST
  ↓
Spring Boot
  ↓
JPA / Hibernate
  ↓
PostgreSQL

Estrutura principal do backend:

controller
dto
entity
exception
repository
security
service
Como executar
Pré-requisitos
Java 21
PostgreSQL
Node.js
npm
Backend

Crie um banco PostgreSQL chamado:

techstore

Configure as variáveis de ambiente:

DB_PASSWORD
JWT_SECRET

Depois execute:

.\mvnw.cmd spring-boot:run

Backend:

http://localhost:8080
Frontend

Entre na pasta:

cd frontend

Instale as dependências:

npm install

Inicie:

npm run dev

Frontend:

http://localhost:5173
Fluxo de compra
O usuário realiza login.
O backend gera um JWT.
O token é armazenado no navegador.
O usuário adiciona produtos ao carrinho.
No checkout, o frontend envia os itens e o JWT.
O backend identifica o usuário pelo token.
O pedido é criado e associado à conta autenticada.
O estoque é atualizado.
O frontend limpa o carrinho e exibe a confirmação da compra.
API

Com o backend em execução, a documentação Swagger pode ser acessada em:

http://localhost:8080/swagger-ui.html
Status

Projeto concluído como parte do meu portfólio de desenvolvimento de software.

Desenvolvido por Gustavo Gonçalves do Prado.