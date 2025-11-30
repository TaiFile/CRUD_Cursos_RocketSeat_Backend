# Gemini Code Companion: CRUD Courses API

Este documento serve como a documentação central para a **CRUD Courses API**, uma aplicação Spring Boot para gerenciar cursos educacionais. Ele é mantido pelo Gemini para refletir o estado atual do código.

## 1. Visão Geral do Projeto

A **CRUD Courses API** é um serviço de backend que fornece endpoints RESTful para realizar operações de Criar, Ler, Atualizar e Deletar (CRUD) em uma entidade `Course`. A aplicação é construída com as melhores práticas, incluindo uma arquitetura limpa, versionamento de banco de dados e tratamento de erros robusto.

## 2. Tecnologias e Ferramentas

*   **Java 17**: Linguagem de programação principal.
*   **Spring Boot 3.2.5**: Framework da aplicação.
    *   **Spring Web**: Para construir APIs RESTful.
    *   **Spring Data JPA**: Para a camada de persistência de dados.
    *   **Spring Boot Validation**: Para validação de dados de entrada.
*   **Maven**: Gerenciador de dependências e build.
*   **PostgreSQL**: Banco de dados relacional.
*   **Docker**: Para containerizar e gerenciar o banco de dados.
*   **Flyway**: Para versionamento e migração do esquema do banco de dados.
*   **Lombok**: Para reduzir código boilerplate (getters, setters, etc.).

## 3. Arquitetura do Sistema

A aplicação utiliza uma **arquitetura limpa em camadas (slices)**, onde cada funcionalidade principal (`feature`) é um módulo autônomo. Isso promove alta coesão, baixo acoplamento e manutenibilidade.

### Estrutura da Feature `course`

```
/src/main/java/com/app/
├── CrudCoursesApplication.java  // Ponto de entrada
├── exception
│   └── ApplicationExceptionHandler.java // Handler global de exceções
└── features/course
    ├── controller  // Camada de API (REST)
    │   └── CourseController.java
    ├── dtos        // Objetos de Transferência de Dados
    │   ├── CourseRequestDTO.java
    │   └── CourseResponseDTO.java
    ├── exception   // Exceções específicas da feature
    │   └── CourseNotFoundException.java
    ├── mapper      // Mapeamento entre Entidade e DTOs
    │   └── CourseMapper.java
    ├── model       // Entidade do banco de dados (JPA)
    │   └── Course.java
    ├── repository  // Camada de acesso a dados
    │   └── CourseRepository.java
    └── service     // Camada de lógica de negócio
        └── CourseService.java
```

## 4. Gerenciamento do Banco de Dados (SQL)

O SQL na aplicação é gerenciado por duas ferramentas distintas:

1.  **Flyway (Estrutura - DDL)**:
    *   **Onde:** `src/main/resources/db/migration`
    *   **O que faz:** Gerencia a criação e alteração da estrutura do banco de dados (tabelas, colunas) através de scripts SQL versionados (ex: `V1__Create_course_table.sql`). É executado na inicialização da aplicação.

2.  **Hibernate / Spring Data JPA (Dados - DML)**:
    *   **Onde:** Gerado em tempo de execução.
    *   **O que faz:** Cria os comandos `INSERT`, `SELECT`, `UPDATE`, `DELETE` com base nos métodos chamados no `CourseRepository` (ex: `repository.save()`, `repository.findById()`).
    *   **Visualização:** O SQL gerado é impresso no console porque a propriedade `spring.jpa.show-sql=true` está ativa no `application.properties`.

## 5. Setup e Instalação

1.  **Pré-requisitos**:
    *   Java 17 (ou superior)
    *   Maven
    *   Docker e Docker Compose (com o Docker Desktop em execução)

2.  **Iniciar o Banco de Dados**:
    Execute o comando na raiz do projeto para iniciar o PostgreSQL.
    ```sh
    docker-compose up -d
    ```

3.  **Executar a Aplicação**:
    Use o wrapper do Maven para compilar e executar o projeto.
    ```sh
    mvn spring-boot:run
    ```
    A API estará disponível em `http://localhost:8080`.

## 6. Documentação da API

Todos os endpoints estão sob o prefixo `/courses`.

---

### `POST /`
Cria um novo curso.

*   **Request Body**:
    ```json
    {
      "name": "API com Spring Boot",
      "category": "Backend"
    }
    ```
*   **Response (201 Created)**:
    ```json
    {
      "id": "...",
      "name": "API com Spring Boot",
      "category": "Backend",
      "createdAt": "2023-11-30 22:00:00"
    }
    ```

---

### `GET /`
Lista todos os cursos.

*   **Response (200 OK)**:
    ```json
    [
      {
        "id": "...",
        "name": "API com Spring Boot",
        "category": "Backend",
        "createdAt": "..."
      }
    ]
    ```

---

### `GET /{id}`
Busca um curso pelo seu ID (UUID).

*   **Response (200 OK)**: O objeto do curso.
*   **Response (404 Not Found)**: Se o curso não for encontrado.

---

### `PUT /{id}`
Atualiza um curso existente.

*   **Request Body**:
    ```json
    {
      "name": "API com Spring Boot e Testes",
      "category": "Backend Avançado"
    }
    ```
*   **Response (200 OK)**: O objeto do curso atualizado.
*   **Response (404 Not Found)**: Se o curso não for encontrado.

---

### `DELETE /{id}`
Deleta um curso.

*   **Response (204 No Content)**: Em caso de sucesso.
*   **Response (404 Not Found)**: Se o curso não for encontrado.

## 7. Tratamento de Erros

*   **`ApplicationExceptionHandler`**: Intercepta todas as exceções não tratadas e retorna uma resposta JSON padronizada, evitando o vazamento de detalhes internos e facilitando o debug.
*   **`CourseNotFoundException`**: Exceção customizada que retorna um status `404 Not Found` quando um curso não é encontrado.
*   **Validação**: O Spring Boot Validation retorna um status `400 Bad Request` automaticamente se os campos no `CourseRequestDTO` (`name`, `category`) forem inválidos.
```