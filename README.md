# 🍔 Tech Challenge - Fast Food

Sistema de autoatendimento para restaurantes fast food, desenvolvido como parte do desafio da Pós Tech FIAP. Esta aplicação adota a **Arquitetura Hexagonal (Ports and Adapters)** e oferece APIs RESTful para gerenciar produtos, pedidos, clientes e pagamentos com integração ao Mercado Pago.

---

## 🔧 Tecnologias Utilizadas

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- MySQL
- Gradle
- Docker / Docker Compose
- Swagger (OpenAPI 3)

---

## 🧱 Arquitetura Hexagonal

📁 Estrutura de Pastas

O projeto segue a arquitetura hexagonal (Ports & Adapters), que isola o núcleo do domínio das dependências externas, facilitando a manutenção, testabilidade e evolução do sistema.
Essa organização reflete os princípios da arquitetura hexagonal, promovendo o desacoplamento entre o domínio e as tecnologias externas.

```
src/main/java/com/fiap/fast_food_tc/
├── adapter/           # Adaptadores de entrada e saída
│   ├── config/        # Configurações externas (Swagger, WebClient, etc)
│   ├── controller/    # Portas de entrada (HTTP Controllers)
│   └── db/            # Modelos e persistência (JPA)
├── app/
│   └── service/       # Serviços de aplicação (implementação dos casos de uso)
├── cross/
│   ├── enums/         # Enumerações compartilhadas
│   ├── exception/     # Exceções customizadas
│   └── mapper/        # Mapeadores de DTOs e entidades
├── domain/            # Núcleo do domínio
│   ├── entity/        # Entidades do domínio
│   ├── gateway/       # Interfaces (ports) para comunicação externa
│   └── usecase/       # Casos de uso do domínio
```

- **adapter/**: Implementa os adaptadores de entrada (controllers REST) e saída (persistência, integrações externas).
- **app/**: Serviços de aplicação, responsáveis por orquestrar os casos de uso.
- **cross/**: Componentes compartilhados, como enums, mapeadores e exceções.
- **domain/**: Núcleo do domínio, contendo entidades, interfaces (ports) e casos de uso.


**Descrição das pastas:**

- `adapter/`: Adaptadores de entrada (ex: controllers REST) e saída (ex: persistência, integrações externas).
    - `config/`: Configurações externas (Swagger, WebClient, etc).
    - `controller/`: Portas de entrada, como controllers HTTP.
    - `db/`: Modelos e lógica de persistência (JPA).

- `app/`: Serviços de aplicação, responsáveis por orquestrar os casos de uso.
    - `service/`: Implementação dos serviços de aplicação.

- `cross/`: Componentes compartilhados entre camadas.
    - `enums/`: Enumerações usadas no projeto.
    - `exception/`: Exceções customizadas.
    - `mapper/`: Mapeadores de DTOs e entidades.

- `domain/`: Núcleo do domínio, isolado das dependências externas.
    - `entity/`: Entidades do domínio.
    - `gateway/`: Interfaces (ports) para comunicação externa.
    - `usecase/`: Casos de uso do domínio.
---

## 📦 Funcionalidades

- 👤 Cadastro e consulta de clientes via CPF
- 🍔 CRUD de produtos com categorias
- 🧾 Listagem de categorias
- 🧾 Criação e listagem de pedidos por cliente
- 💳 Checkout com geração de link de pagamento Mercado Pago
- 🔍 Documentação interativa com Swagger

---
## 🗄️ Banco de Dados (MySQL)

### 📦 Estrutura do Banco de Dados (MySQL)

O banco de dados foi estruturado para suportar um sistema de pedidos de fast food, contemplando informações sobre produtos, categorias, clientes, pedidos, pagamentos, funcionários e itens dos pedidos. Abaixo estão as principais tabelas e seus relacionamentos:

### 🔗 Relacionamentos Principais

- Um **cliente** pode realizar vários **pedidos**.
- Um **pedido** pode conter múltiplos **produtos** (tabela intermediária `order_product`).
- Cada **produto** pertence a uma **categoria**.
- Um **pedido** pode ter um ou mais **pagamentos** associados.
- **Funcionários** são armazenados separadamente para controle administrativo.

### 🗃️ Tabelas e Campos

#### `customer`
- `customer_id` (PK)
- `document_number`
- `email`
- `first_name`
- `last_name`

#### `orders`
- `order_id` (PK)
- `order_code`
- `order_datetime`
- `status_order`
- `total_amount`
- `customer_id` (FK → customer)

#### `order_product`
- `order_id` (FK → orders)
- `product_id` (FK → product)
- `product_quantity`
- `product_total_amount`

#### `product`
- `product_id` (PK)
- `name`
- `description`
- `image_path`
- `available_indicator`
- `product_value`
- `quantity`
- `category_id` (FK → category)

#### `category`
- `category_id` (PK)
- `category_name`
- `category_description`

#### `payment`
- `payment_id` (PK)
- `created_at`
- `customer_id` (FK → customer)
- `mercado_pago_id`
- `payment_method`
- `payment_status`
- `payment_value`
- `order_id` (FK → orders)

#### `employee`
- `employee_id` (PK)
- `document_number`
- `manager_indicator`
- `name`
- `password`

---

### ✅ Inserts de Exemplo

```sql
-- Categorias
INSERT INTO category (category_name, category_description) VALUES 
('Lanches', 'Hambúrgueres, sanduíches e wraps'),
('Bebidas', 'Refrigerantes, sucos e água'),
('Sobremesas', 'Doces e sobremesas variadas');

-- Produtos
INSERT INTO product (name, description, product_value, quantity, category_id) VALUES 
('Hambúrguer Clássico', 'Pão, carne, queijo e salada', 18.90, 50, 1),
('Cheeseburger Duplo', 'Dois hambúrgueres e queijo extra', 24.90, 40, 1),
('Refrigerante Lata', '350ml - diversos sabores', 5.00, 100, 2),
('Água Mineral', '500ml sem gás', 3.00, 120, 2),
('Sorvete de Chocolate', '1 bola de sorvete artesanal', 7.00, 30, 3);

-- Clientes
INSERT INTO customer (document_number, email, first_name, last_name) VALUES
('12345678900', 'ana@email.com', 'Ana', 'Silva'),
('98765432100', 'bruno@email.com', 'Bruno', 'Oliveira');

-- Pedidos
INSERT INTO orders (order_code, status_order, total_amount, customer_id) VALUES
('ORD001', 'Finalizado', 48.80, 1),
('ORD002', 'Pendente', 32.90, 2);

-- Itens do pedido
INSERT INTO order_product (order_id, product_id, product_quantity, product_total_amount) VALUES
(1, 1, 1, 18.90),
(1, 2, 1, 24.90),
(1, 3, 1, 5.00),
(2, 2, 1, 24.90),
(2, 4, 1, 3.00),
(2, 5, 1, 5.00);

-- Pagamentos
INSERT INTO payment (customer_id, mercado_pago_id, payment_method, payment_status, payment_value, order_id) VALUES
(1, 'MP123ABC', 'Cartão de Crédito', 'Aprovado', 48.80, 1),
(2, 'MP456DEF', 'Pix', 'Pendente', 32.90, 2);

-- Funcionários
INSERT INTO employee (document_number, manager_indicator, name, password) VALUES
('00011122233', TRUE, 'Carlos Gerente', 'hash_da_senha'),
('00044455566', FALSE, 'Fernanda Atendente', 'hash_da_senha');
```

---

### 🔍 Views Úteis

#### 1. Total gasto por cliente

```sql
CREATE VIEW vw_total_por_cliente AS
SELECT 
    c.customer_id,
    CONCAT(c.first_name, ' ', c.last_name) AS cliente,
    SUM(o.total_amount) AS total_gasto
FROM orders o
JOIN customer c ON c.customer_id = o.customer_id
GROUP BY c.customer_id;
```

#### 2. Faturamento por categoria

```sql
CREATE VIEW vw_faturamento_categoria AS
SELECT 
    cat.category_name,
    SUM(op.product_total_amount) AS total_vendido
FROM order_product op
JOIN product p ON p.product_id = op.product_id
JOIN category cat ON cat.category_id = p.category_id
GROUP BY cat.category_name;
```

#### 3. Produtos mais vendidos

```sql
CREATE VIEW vw_produtos_mais_vendidos AS
SELECT 
    p.name AS produto,
    SUM(op.product_quantity) AS total_vendido
FROM order_product op
JOIN product p ON p.product_id = op.product_id
GROUP BY p.name
ORDER BY total_vendido DESC;
```

## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java 21
- Gradle 8
- MySQL 8 (ou banco H2 para testes)
- Docker (opcional para execução via containers)

### Passos

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
4. Acesse a aplicação em `http://localhost:8080` e a documentação Swagger em `http://localhost:8080/swagger-ui/index.html`.

📚 Principais Endpoints

| Resource   | Method | Route                             | Description                        |
| ---------- | ------ |-----------------------------------|------------------------------------|
| Customer   | POST   | `/customers`                      | Register new customer              |
| Customer   | GET    | `/customers/{documentNumber}`     | Get customer by CPF                |
| Product    | POST   | `/products`                       | Register new product               |
| Product    | GET    | `/products`                       | List all products                  |
| Product    | GET    | `/products/category/{categoryId}` | List all products by category      |
| Product    | PUT    | `/products/{id}`                  | Update product                     |
| Product    | DELETE | `/products/{id}`                  | Remove product                     |
| Category   | GET    | `/categories`                     | List categories                    |
| Order      | POST   | `/orders`                         | Create new order                   |
| Checkout   | POST   | `/checkout`                       | Generate Mercado Pago payment link |

💳 Integração com Mercado Pago

A funcionalidade de checkout utiliza uma chamada rest para a API do Mercado Pago para gerar links de pagamento baseados no valor total do pedido. O cliente é redirecionado para a plataforma externa para finalizar a compra de forma segura.

## Testes 🧪

Os testes podem ser executados com:
```bash
./gradlew test
```
Caso esteja utilizando Docker, é possível rodar os testes dentro do próprio container.

## Configurações ⚙️

As credenciais do banco e do Mercado Pago podem ser alteradas em `src/main/resources/application.yaml`.

## Feito por Pedro Peçanha, Gustavo e Leonardo Fujimura
