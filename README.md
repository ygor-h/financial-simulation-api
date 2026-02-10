<h1 align="center" style="font-weight: bold;">Financial Simulation API</h1>

<p align="center">
    <a href="#tecnologias">Tecnologias</a> •
    <a href="#funcionalidades">Funcionalidades</a> • 
    <a href="#arquitetura">Arquitetura</a> • 
    <a href="#iniciar">Rodar projeto</a> •
    <a href="#rotas">API Endpoints</a> •
    <a href="#docker">Variáveis de Ambiente</a>
</p>

<p align="center">
    <b>API REST para simulações financeiras, desenvolvida com Spring Boot, Java 21, PostgreSQL e Docker.
O projeto permite calcular e armazenar simulações de investimento com base em valor inicial, taxa de juros e período.</b>
</p>

<h2 id="tecnologias">💻 Tecnologias utilizadas</h2>

- Java 21
- Spring Boot
- JPA / Hibernate
- PostgreSQL
- Docker
- Maven
- Swagger/OpenAPI

<h2 id="funcionalidades">📌 Funcionalidades</h2>

- Simulação de investimentos com juros compostos
- Simulação com aportes mensais
- Persistência das simulações no banco de dados
- Documentação com Swagger/OpenAPI
- Ambiente isolado com Docker

<h2 id="arquitetura">📦 Arquitetura</h2>

- <b>API:</b> Spring Boot rodando em container Docker
- <b>Banco de dados:</b> PostgreSQL em container separado
- Comunicação entre serviços via <b>Docker Compose (network interna)</b>

<h2 id="iniciar">🚀 Como rodar o projeto</h2>

<h3>Pré-requisitos</h3>

- Docker
- Docker Compose

<b>Não é necessário ter Java ou PostgreSQL instalados localmente</b>


<h3>Instalação</h3>

Clonar o repositório

```bash
git clone https://github.com/ygor-h/financial-simulation-api.git
```

<h3>▶️ Subir a aplicação</h3>

Na raiz do projeto, execute:

```bash
docker compose up --build
```

Isso irá:

- Buildar a imagem da API
- Subir o container do PostgreSQL
- Criar automaticamente o banco e as tabelas
- Iniciar a aplicação na porta 8080

<h2 id="rotas">API Endpoints</h2>

A API fornece os seguintes endpoints:
​
| rotas               | descrição                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /simulation</kbd>     | Buscar todas as simulações
| <kbd>GET /simulation/{id}</kbd>     | Buscar simulação usando ID
| <kbd>POST /simulation/simulate</kbd>     | Simular investimento sem aportes mensais
| <kbd>POST /simulation/simulate-with-contribution</kbd>     | Simular investimento com aportes mensais
| <kbd>DELETE /simulation/{id}</kbd>     | Deletar simulação usando ID

<b>Documentação completa quando estiver rodando:</b>

```bash
http://localhost:8080/swagger-ui/index.html
```

<h2 id="docker">Variáveis de ambiente (Docker)</h2>

As configurações principais já estão definidas no `docker-compose.yml`, incluindo:

- Host do banco: `postgres`
- Porta: `5432`
- Database: `financial_simulation_db`

A API se conecta ao banco automaticamente via rede do Docker
