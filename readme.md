<h1 align="center">
      <a href="#" alt="site do ecoleta"> Desafio Pisma </a>
</h1>
<h4 align="center">
	🚧   Status: Finalizado 🚀 🚧
</h4>

Tabela de conteúdos 
=================
<!--ts-->
   * [Sobre o projeto](#-sobre-o-projeto)
   * [Funcionalidades](#-funcionalidades)
   * [Release Note](#-release-notes)
   * [Estrutura do Projeto](#-estrutura-do-projeto)
   * [Como executar o projeto](#-como-executar-o-projeto)
     * [Pré-requisitos](#pré-requisitos)
     * [Rodando o Backend (Local)](#user-content--rodando-o-backend)
     * [Rodando Testes  (Local)](#user-content--rodando-testes-locais)
   * [Tecnologias](#-tecnologias)
     * [Testes Locais](#user-content-testes-locais)
     * [Backend](#user-content-backend)
<!--te-->


## 💻 Sobre o projeto

Api em Java e Spring para transações.
---

## ⚙️ Funcionalidades


  - [x] Create Transaction endpoint.
  - [x] Create Account endpoint.
  - [X] Find Account By Id endpoint.

---

## Release Notes
<details>
  <summary>Click to expand!</summary>
  1. APP V1<br>
</details>

---

## Estrutura do Projeto

<details>
  <summary>Click to expand!</summary>
1  ./infra = Arquivos de cfg (SQL/Compose file)<br>
2  ./src/main/java = Java files<br>	
     *   ../domain = estruturas de dados, DTO | Entitys | Strategy's<br>
     *   ../infrastructure = configuração/Plugins, mapper | configuration | commun <br>	
     *   ../repository = comunicação com db , mapper | configuration | commun <br>
     *   ../resources = controllers rest, v1 <br>
     *   ../service = classes de negocio e suas interfaces <br>	


</details>

---

## 🚀 Como executar o projeto

1. Backend (pasta aonde está o código do projeto ex: /src/main) 
2. Testes Locais(pasta aonde está os testes ex:/src/test)
3. Infra(pasta com os arquivos de infraestrutura ex: /infra)

💡Observações importantes para execução do projeto caso seja necessário.

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
Listar as ferramentas essenciais. 

1. Docker
2. Jdk21
3. maven 3.9.5

#### 🎲 Rodando o Backend

```bash

# Clone este repositório
$ git clone https://github.com/DiegoSenaa/pismo_challenger


# No terminal
$ mvn clean install

# Naveguei dentro do projeto até a pasta infra execute o comando
$ docker compose up mysql

# Para remover o container mysql execute o comando dentro da pasta infra
$ docker compose down --remove-orphans

#Caso queira roda tanto o Mysql como a aplicação em container, basta executar dentro da pasta infra
$ docker compose build

#Em seguida
$ docker compose up -d

# Para remover os containers execute o comando dentro da pasta infra
$ docker compose down --remove-orphans

```
#### 🧭 Rodando Testes Locais

```bash

# Clone este repositório
$ git clone git clone git clone https://github.com/DiegoSenaa/pismo_challenger

# No terminal
$ mvn clean packge

# Arquivo de teste na pasta do projeto target/site
$ executar no navegador o arquivo index.html

```

---

## 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

#### **Testes Locais** 

-   **[JUnit](https://junit.org/junit5/)**
-   **[Spring testing](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html)**


#### **Backend**

-   **[Java 21](https://kotlinlang.org/)**
-   **[Apache Maven 3.9.5](https://maven.apache.org/)**
-   **[Spring boot 3.2](https://spring.io/projects/spring-boot)**
-   **[Mysql 8.2](https://dev.mysql.com/)**

#### **Utilitários**

-   GitBash:  **[GitBash](https://git-scm.com/about)**
-   Editor: **[Intellij 2023.x](https://www.jetbrains.com/pt-br/idea/)**
