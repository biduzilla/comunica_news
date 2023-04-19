
# Projeto Comunica News

## Sobre o projeto
Projeto criado com a ideia de ser um app para disponibilizar posts sobre notícas e eventos sobre a IASD-NB com possibilidade de interação entre os membros através de comentários.
A sua criação consite na criação da api construida com Spring Boot onde existirá verificação com token onde pode se realizar o cadastro de usuário, criação e visualização de posts dependendo da ROLE do usuario e alteração de posts.
A parte do app é feita em Kotlin pensando no android nativo onde ocorre o consumo da api para visualização dos dados.

## Tecnologias Utilizadas
### Back end
- Spring Boot
- H2(por enquanto)

### Mobile
- Kotlin(android)

## Técnicas e tecnologias utilizadas

### Spring Boot

A aplicação foi desenvolvida com o Spring Boot utilizando Java e foram utilizadas as seguintes técnicas:

- `Controllers`: mapear os endpoints
- `Services`: realizar as ações esperadas pelo controller
- `Repositories`: oferecer e realizar os comportamentos de persistência de banco de dados
- `DTO`: padrão para indicar quais informações devem ser enviada/recebidas via requisição
- `JPA` com `Hibernate`: solução para se comunicar com o banco de dados
- `H2 database`: banco de dados que pode ser persistido em um arquivo local sem a necessidade de um serviço de SGBD
- `Security`: tratamento e analise do Token

Bibliotecas do Spring Framework que foram utilizadas:

- `devtools`: ferramenta para agilizar o processo de desenvolvimento sem reiniciar a aplicação para atualizar
- `starter-web`: suporte para aplicação web em geral
- `starter-data-jpa`: suporte para abstrair a implementação de repositórios e reutilizar comportamentos de CRUD com base na configuração da JPA
- `starter-security`: sistemas de autenticação, autorização e proteção contra diferentes tipos de vulnerabilidades de aplicações web,também disponibiliza algoritmos de criptografias
- `starter-validation`: caso tenha algum problema com a nossa requisição, retorna o erro da requisição
## Endpoints para api
### Usuario
```bash
#Salvar usuario

Método: Post

endpoint: /users/cadastrar
#Request
{
    "titulo": "titulo",
    "descricao": "descricao",
    "img": "img"
}

#Response
Status 201 - Created
```

```bash
#Login usuario

Método: Post

endpoint: /users/login

#Request
{
    "email": "lucky@toddy",
    "senha": "123456"
}

#Response
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWNreUB0b2RkeSIsImV4cCI6MTY4MTkxNTUzMn0.9kaLbSt-fnSoUJMH0RM-9WACjT1_rbFNDYkm8xjt9E7GKqUh_wRcSY1SQFNktkbh11fLE51O47vdOm5TiSXytQ",
    "idUser": "c829f9ff-63d9-4fcf-9430-fadf4e9eaa56"
}
Status 200 - OK
```

```bash
#Update usuario
Role: USER

Método: Put

endpoint: /users/atualizar

#header
"Authorization:" "Bearer Token"

#Request
{
    "nome": "lucky",
    "senha": "123456"
}

#Response
Status 200 - OK
```

```bash
#Deletar usuario
Role: ADMIN

Método: Delete

endpoint: /users/deletar/idUser

#header
"Authorization:" "Bearer Token"

#Response
Status 200 - OK
```

### Posts
```bash
#Salvar post
Role: ADMIN

Método: Post

endpoint: /posts/salvarPost

#Header
"Authorization:" "Bearer Token"

#Request
{
    "titulo": "titulo",
    "descricao": "descricao",
    "img": "img"
}

#Response
Status 201 - Created
```

```bash
#Ver todos os posts
Role: USER

Método: Get

endpoint: /posts/getAllPosts

#header
"Authorization:" "Bearer Token"

#Response
Status 200 - Ok
```

```bash
#Ver todos os meus posts
Role: ADMIN

Método: Get

endpoint: /posts/getAllMyPosts/id_usuario

#header
"Authorization:" "Bearer Token"

#Response
Status 200 - Ok
```
```bash
#Ver post pelo id
Role: USER

Método: Get

endpoint: /posts/getPostById/id_post

#header
"Authorization:" "Bearer Token"

#Response
Status 200 - Ok
```

```bash
#Atualizar post
Role: ADMIN

Método: Put

endpoint: /posts/updatePost/id_post

#header
"Authorization:" "Bearer Token"

#Request
{
    "titulo": "titulo alterado",
    "descricao": "descricao",
    "img": "img"
}

#Response
Status 200 - Ok
```

```bash
#Deletar post
Role: ADMIN

Método: Delete

endpoint: /posts/deletePost/id_post

#header
"Authorization:" "Bearer Token"

#Response
Status 204 - No Content
```