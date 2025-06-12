# Crud-produtos

## Grupo
- Jonas Nogueira Neto
- Christyan Fernandes
- Anna Caronlina
- Lucas Dombrowski
- Guilherme Vicente Costa
- Gabriel Teixeira
- Laura Pacheco

## Projeto
CRUD em Java usando Swing, MVC e MySQL

## Imagem do Projeto

Tela principal aonde é possível visualizar os produtos cadastrados, criar novos e editar os existentes clicando sobre a linha do produto
![Tela do Crud-shop](/images/main.png)

![Tela de cadastro de produtos](/images/new-product.png)

![Tela de edição de produtos](/images/datails.png)


## Instruções
1. Crie o banco de dados
2. Rode os scripts de criação das tabelas `/scripts`
3. Crie um arquivo `.env` na raiz, e copie o conteúdo do arquivo `.env.example` para ele
4. Configure a URL do banco de dados no arquivo `.env`
5. Execute o projeto
6. Faça login com o usuário padrão `admin` e senha `admin`

## Dependências 
- Java 21
- MySQL 8.0
- MySQL Connector 9.3.0
- dotenv-java 3.2.0

## Estrutura
```
├── crud-shop.iml # Arquivo de configuração para IDE
├── lib # Dependências do projeto
│   ├── dotenv-java-3.0.0.jar
│   └── mysql-connector-j-9.3.0.jar
├── scripts # Scripts SQL para criação do banco de dados
│   └── script.sql
└── src 
    ├── Main.java # Ponto de entrada do aplicativo
    ├── controller # Camadas do MVC
    ├── model # Camadas do MVC
    ├── services # Camadas do MVC
    ├── util
    └── view # Telas do swing

```
