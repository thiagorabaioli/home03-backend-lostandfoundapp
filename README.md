# Lost and Found APP
## Descrição
O Lost and Found APP é uma aplicação web desenvolvida em Java com Spring Boot, desenhada para gerir o registo e a devolução de itens perdidos e achados num determinado local. A plataforma permite que utilizadores autorizados (como vigilantes e administradores) registem itens encontrados, e mantém um registo de todas as interações, desde o registo até à entrega ao proprietário. A aplicação também oferece uma visão pública dos itens que ainda não foram reclamados.

## Ferramentas e Tecnologias
<img loading="lazy" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="40" height="40"/> <img loading="lazy" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/linux/linux-original.svg" width="40" height="40"/> <img loading="lazy" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" width="40" height="40"/>
<img loading="lazy" src="https://github.com/thiagorabaioli/assets/blob/main/img/docker.png" width="40" height="40"/> 
<img loading="lazy" src="https://github.com/thiagorabaioli/assets/blob/main/img/postman.png" width="40" height="40"/> 
<img loading="lazy" src="https://github.com/thiagorabaioli/assets/blob/main/img/springboot.png" width="80" height="80"/>


## Contatos:
<div>

<a href="https://www.twitch.tv/seu-usuário-aqui" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Twitch-9146FF?style=for-the-badge&logo=twitch&logoColor=white" target="_blank"></a>
<a href = "mailto:tf"><img loading="lazy" src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
<a href="https://www.linkedin.com/in/rabaioli37" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>   
</div>




  
## Funcionalidades Principais
### Gestão de Itens Perdidos:

### Registo de novos itens encontrados, com detalhes como descrição, local e data.

Atualização das informações de um item.

Listagem paginada de todos os itens para utilizadores autorizados.

Visualização pública de itens ainda por entregar.

Registo da entrega de um item ao seu proprietário.

## Controlo de Acesso Baseado em Papéis (Roles):

Sistema de autenticação e autorização robusto com Spring Security e OAuth2.

Diferentes níveis de permissão para ADMIN, VIGILANTE e outros papéis.

Apenas utilizadores autorizados podem gerir os itens.

## Gestão de Utilizadores:

Administradores podem criar, visualizar, atualizar e eliminar contas de utilizador.

## Histórico de Interações:

Cada item possui um histórico de interações (criação, entrega, etc.), registando qual utilizador realizou a ação e quando.

## Notificações por E-mail:

Envio automático de e-mails para os utilizadores relevantes quando um novo item é registado ou quando um item é entregue.

# Tecnologias Utilizadas
Backend:

Java 21

Spring Boot 3.5.4

Spring Data JPA & Hibernate

Spring Security (OAuth2 Authorization Server & Resource Server)

Maven (Gestor de dependências)

Base de Dados:

PostgreSQL (para ambientes de desenvolvimento e produção)

H2 Database (para ambiente de testes)

Outros:

Spring Boot Mail (para envio de e-mails)

Jakarta Bean Validation (para validação de dados)

# Como Começar
Siga estas instruções para configurar e executar o projeto no seu ambiente local.

### Pré-requisitos
JDK 21 ou superior

Maven 3.x

PostgreSQL

Instalação e Configuração
Clone o repositório:

### Bash
```
git clone https://github.com/thiagorabaioli/home03-lostandfoundapp.git
cd home03-lostandfoundapp
Configuração da Base de Dados (Desenvolvimento):
```
### Certifique-se de que tem o PostgreSQL em execução.

Crie uma base de dados com o nome laf.

Abra o ficheiro src/main/resources/application-dev.properties e altere as seguintes propriedades com as suas credenciais do PostgreSQL:

Properties

spring.datasource.url=jdbc:postgresql://SEU_HOST:5432/laf
spring.datasource.username=SEU_UTILIZADOR
spring.datasource.password=SUA_SENHA
Configuração de Variáveis de Ambiente:

#### A aplicação utiliza variáveis de ambiente para algumas configurações sensíveis. Pode configurá-las no seu sistema ou no seu IDE. As principais são (definidas em application.properties):

CLIENT_ID: ID do cliente OAuth2 (default: myclientid)

CLIENT_SECRET: Segredo do cliente OAuth2 (default: myclientsecret)

CORS_ORIGINS: Origens permitidas para CORS (ex: http://localhost:3000)

MAIL_USERNAME: O seu e-mail para o envio de notificações.

MAIL_PASSWORD: A sua senha de aplicação para o e-mail.

Ativar o Perfil de Desenvolvimento:

Abra o ficheiro src/main/resources/application.properties e certifique-se de que o perfil dev está ativo para usar a base de dados PostgreSQL:

### Properties

spring.profiles.active=dev
(O perfil test utiliza a base de dados em memória H2 por defeito).

Executar a Aplicação
Pode executar a aplicação usando o Maven Wrapper incluído:

### Bash
```
./mvnw spring-boot:run
A aplicação estará disponível em http://localhost:8080.
```
Estrutura da Base de Dados
A aplicação utiliza as seguintes entidades principais:

UserAPP: Representa um utilizador da aplicação, com os seus dados e papéis (roles).

Role: Define os papéis de autorização (ex: ROLE_ADMIN).

ItemLost: O objeto central, representa um item que foi perdido e encontrado.

Delivery / Owner: Regista a informação da entrega de um item ao seu proprietário.

OrderItem: Modela uma interação ou evento associado a um ItemLost (ex: registo inicial, entrega).

Pode encontrar os dados iniciais para popular a base de dados no ficheiro src/main/resources/import.sql.


# Lost and Found APP
Description
The Lost and Found APP is a web application developed in Java with Spring Boot, designed to manage the registration and return of lost and found items in a specific location. The platform allows authorized users (such as security guards and administrators) to register found items and maintains a record of all interactions, from registration to delivery to the owner. The application also offers a public view of items that have not yet been claimed.

Key Features
Lost Item Management:

Register new found items with details such as description, location, and date.

Update item information.

Paginated listing of all items for authorized users.

Public view of items yet to be returned.

Record the delivery of an item to its owner.

Role-Based Access Control:

Robust authentication and authorization system with Spring Security and OAuth2.

Different permission levels for ADMIN, VIGILANTE (Guard), and other roles.

Only authorized users can manage items.

User Management:

Administrators can create, view, update, and delete user accounts.

Interaction History:

Each item has an interaction history (creation, delivery, etc.), logging which user performed the action and when.

Email Notifications:

Automatic email dispatch to relevant users when a new item is registered or when an item is delivered.

Technologies Used
Backend:

Java 21

Spring Boot 3.5.4

Spring Data JPA & Hibernate

Spring Security (OAuth2 Authorization Server & Resource Server)

Maven (Dependency Manager)

Database:

PostgreSQL (for development and production environments)

H2 Database (for testing environment)

Other:

Spring Boot Mail (for sending emails)

Jakarta Bean Validation (for data validation)

Getting Started
Follow these instructions to set up and run the project in your local environment.

Prerequisites
JDK 21 or higher

Maven 3.x

PostgreSQL

Installation and Configuration
Clone the repository:

Bash
```
git clone https://github.com/thiagorabaioli/home03-lostandfoundapp.git
cd home03-lostandfoundapp
Database Configuration (Development):
```
Ensure you have PostgreSQL running.

Create a database named laf.

Open the file src/main/resources/application-dev.properties and change the following properties with your PostgreSQL credentials:

Properties

spring.datasource.url=jdbc:postgresql://YOUR_HOST:5432/laf
spring.datasource.username=YOUR_USER
spring.datasource.password=YOUR_PASSWORD
Environment Variables Configuration:

The application uses environment variables for some sensitive settings. You can configure them in your system or IDE. The main ones are (defined in application.properties):

CLIENT_ID: OAuth2 client ID (default: myclientid)

CLIENT_SECRET: OAuth2 client secret (default: myclientsecret)

CORS_ORIGINS: Allowed origins for CORS (e.g., http://localhost:3000)

MAIL_USERNAME: Your email for sending notifications.

MAIL_PASSWORD: Your application-specific password for the email account.

Activate the Development Profile:

Open the file src/main/resources/application.properties and ensure the dev profile is active to use the PostgreSQL database:

Properties

spring.profiles.active=dev
(The test profile uses the H2 in-memory database by default).

Running the Application
You can run the application using the included Maven Wrapper:

Bash
```
./mvnw spring-boot:run
The application will be available at http://localhost:8080.
```
Database Structure
The application uses the following main entities:

UserAPP: Represents an application user, with their data and roles.

Role: Defines authorization roles (e.g., ROLE_ADMIN).

ItemLost: The central object, representing an item that was lost and found.

Delivery / Owner: Records the information of an item's delivery to its owner.

OrderItem: Models an interaction or event associated with an ItemLost (e.g., initial registration, delivery).

You can find the initial data to populate the database in the src/main/resources/import.sql file.
