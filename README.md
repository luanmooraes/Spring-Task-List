# Projeto Spring Java - TaskApp

Este é um projeto que utiliza o framework Spring para Java e 
demonstra a criação de uma API de Tarefas. Ele inclui configurações básicas, controladores e serviços no seu desenvolvimento.

## Descrição

O TaskApp é um sistema de gerenciamento de tarefas projetado para facilitar a organização e acompanhamento de tarefas individuais.

## Pré-requisitos

- JDK 17
- Apache Maven
- Um IDE Java (recomendado: IntelliJ IDEA, Eclipse)
- Conexão à internet para baixar dependências do Maven

## Tecnologias utilizadas

- Java
- Spring Boot
- Sistema de Gerenciamento de Banco de Dados MySQL
- JUnit e Mockito(Testes)
- Postman(Testes)
- Padrões de APIs Restul

## Configuração do Projeto

1. Clone este repositório:

    ```
    git clone https://github.com/luanmooraes/Spring-Task-List.git
    ```

2. Importe o projeto para o seu IDE Java.

3. Certifique-se de ter todas as dependências do Maven baixadas e atualizadas. Isso pode ser feito através do comando:

    ```
    mvn clean install
    ```

## Executando a Aplicação

1. Abra o projeto na sua IDE.

2. Encontre a classe `TodolistSpringApplication.java` no pacote `com.luan`.

3. Execute a classe `TodolistSpringApplication.java` como uma aplicação Java.

4. O servidor embutido do Spring será iniciado e você poderá acessar a aplicação em `http://localhost:8080`.

## Executando Testes

1. Na sua IDE, navegue até o diretório de testes, geralmente localizado em `src/test/java`.

2. Execute os testes JUnit clicando com o botão direito no diretório de testes e selecionando "Run All Tests" (ou similar, dependendo do IDE).

3. Os resultados dos testes serão exibidos no console do IDE.

## Testes dos EndPoints com Postman

1. Na IDE há um arquivo chamado `json.text` com o json usado nos testes do Postman. Faça a instalação do Postman para fazer os testes

2. Clique nesse arquivo e faça os testes dos endpoints.

3. Os resultados dos testes irão aparecer no Postman.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir problemas (issues) ou enviar pull requests.

## Autor

- Nome: Luan Moraes 
- E-mail: luansilvamoraess@gmail.com

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).
