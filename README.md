# Fast Food Tech Challenge

Aplicação em **Java 21** com **Spring Boot 3** para gestão de pedidos de um restaurante fast food. O projeto foi criado como parte de um desafio técnico e possui endpoints REST para cadastro de clientes, produtos, categorias, realização de pedidos e processo de checkout via Mercado Pago.

## Funcionalidades 🎯

- 👤 Cadastro e consulta de clientes
- 🍔 Cadastro, atualização e exclusão de produtos
- 📂 Listagem de categorias
- 📝 Criação e listagem de pedidos
- 💰 Geração de link de pagamento (checkout)
- 📄 Documentação interativa com Swagger/OpenAPI

## Requisitos 📋

- Java 21
- Gradle 8
- MySQL 8 (ou banco H2 para testes)
- Docker (opcional para execução via containers)

## Como executar ▶️

### Local

1. Clone o repositório e navegue até a pasta do projeto.
2. Conceda permissão de execução ao wrapper do Gradle:
   ```bash
   chmod +x gradlew
   ```
3. Execute a aplicação com o comando:
   ```bash
   ./gradlew bootRun
   ```
4. A API ficará disponível em `http://localhost:8080` e a documentação Swagger em `http://localhost:8080/swagger-ui.html`.
5. Para executar localmente deve-se inserir uma variável de ambiente:
      ```bash
   SPRING_PROFILES_ACTIVE=dev
   ```

### Docker Compose 🐳

1. Certifique‑se de ter Docker e Docker Compose instalados.
2. Rode o comando:
   ```bash
   docker compose up --build
   ```
3. A aplicação será iniciada juntamente com um container MySQL.

## Endpoints 🚀

### Clientes
- **POST /customer** – cria um novo cliente.
- **GET /customer/{documentNumber}** – busca cliente pelo documento.

### Produtos
- **GET /products** – lista todos os produtos.
- **GET /products/{id}** – detalhes de um produto.
- **GET /products/category/{categoryId}** – produtos por categoria.
- **POST /products** – cadastra um produto.
- **PUT /products/{id}** – atualiza um produto.
- **DELETE /products/{id}** – remove um produto.

### Categorias
- **GET /category** – lista categorias.

### Pedidos
- **GET /orders/all** – lista pedidos.
- **POST /orders** – cria um pedido.

### Checkout
- **POST /checkout/{orderId}** – gera link de pagamento.

## Testes 🧪

Os testes podem ser executados com:
```bash
./gradlew test
```
Caso esteja utilizando Docker, é possível rodar os testes dentro do próprio container.

## Configurações ⚙️

As credenciais do banco e do Mercado Pago podem ser alteradas em `src/main/resources/application.yaml`.

## Feito por Pedro Peçanha, Gustavo e Leonardo Fujimura
