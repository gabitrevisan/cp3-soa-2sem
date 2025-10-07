# API de Reserva de Equipamentos

Este projeto é uma API RESTful para gerenciar a reserva de equipamentos para professores, desenvolvida com Java e Spring Boot. A aplicação segue as melhores práticas de arquitetura de software, incluindo a separação de responsabilidades em camadas (controllers, services, repositories) e o uso de Data Transfer Objects (DTOs).

A gestão do banco de dados é automatizada através do **Flyway**, permitindo migrações de schema consistentes entre diferentes ambientes (desenvolvimento e produção).

```
Desenvolvido por Gabriela Trevisan da Silva (RM99500)
Turma 3ESPW
```

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA (Hibernate)**
- **Flyway Database Migration**
- **Maven** (Gerenciador de dependências e build)
- **H2 Database** (Banco de dados em memória para o ambiente de desenvolvimento)
- **Oracle Database** (Suporte para o ambiente de produção)
- **SpringDoc OpenAPI (Swagger)** (Para documentação da API)

## Estrutura do Projeto

O projeto está organizado na seguinte estrutura de pacotes para garantir a separação de responsabilidades:

```

com.example.ExampleFlyway
├── config/            \# Configurações globais do Spring (OpenAPI, CORS, etc.).
├── controller/        \# Camada de API (REST Controllers) que expõe os endpoints.
├── domain/            \# Entidades JPA que mapeiam as tabelas do banco de dados.
├── dto/               \# Data Transfer Objects (DTOs) para entrada e saída de dados.
├── repository/        \# Interfaces do Spring Data JPA para acesso ao banco de dados.
└── service/           \# Camada de serviço que contém a lógica de negócio.

````

## Como Executar a Aplicação

A aplicação utiliza perfis do Spring (`profiles`) para configurar o ambiente. Existem dois perfis pré-configurados: `dev` e `prod`.

### Ambiente de Desenvolvimento (`dev`)

Este perfil utiliza um banco de dados **H2 em memória**. As migrações do Flyway específicas para H2 estão na pasta `src/main/resources/db/migration-h2`.

Para iniciar a aplicação no modo de desenvolvimento, execute o seguinte comando Maven na raiz do projeto:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
````

  - **Console H2:** Para acessar o banco de dados em memória, navegue para `http://localhost:8080/h2-console`
      - **JDBC URL:** `jdbc:h2:mem:reservasdb`
      - **User Name:** `sa`
      - **Password:** (deixe em branco)

### Ambiente de Produção (`prod`)

Este perfil está configurado para conectar a um banco de dados **Oracle**. As configurações de conexão estão no arquivo `src/main/resources/application-prod.properties`. As migrações do Flyway para Oracle estão em `src/main/resources/db/migration-oracle`.

Para iniciar a aplicação no modo de produção, execute:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## Documentação da API (Swagger)

A documentação da API é gerada automaticamente pelo SpringDoc OpenAPI. Após iniciar a aplicação, você pode acessá-la em:

  - **Swagger UI:** `http://localhost:8080/swagger-ui`

Lá você encontrará todos os endpoints disponíveis, seus parâmetros e poderá testá-los diretamente.

## Endpoint Principal

### Criar uma Reserva

Cria uma nova reserva de equipamento para um professor em um determinado período.

  - **URL:** `POST /api/reservas`

  - **Headers:**

      - `Content-Type: application/json`

  - **Corpo da Requisição (JSON):**

    ```json
    {
      "equipamentoId": 1,
      "professorId": 1,
      "sala": "B101",
      "retirada": "2025-10-28T14:00:00",
      "entrega": "2025-10-28T18:00:00"
    }
    ```

  - **Resposta de Sucesso (201 Created):**

    ```json
    {
      "id": 1,
      "equipamento": "Datashow Epson X123",
      "professor": "Prof. Ana",
      "sala": "B101",
      "retirada": "28/10/2025 14:00",
      "entrega": "28/10/2025 18:00"
    }
    ```

  - **Resposta de Erro (Ex: Conflito de horário):**

      - **Código:** `400 Bad Request` (ou outro código de erro apropriado)
      - **Corpo:**
        ```json
        {
          "message": "Equipamento indisponível para o período solicitado."
        }
        ```
