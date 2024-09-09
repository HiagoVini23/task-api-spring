# Hiago's To Do

Bem-vindo ao Hiago's To Do, uma aplicação web projetada para ajudar você a gerenciar suas tarefas de maneira eficiente. Com esta aplicação, você pode criar, editar, excluir e listar suas tarefas, além de organizá-las em grupos chamados "TaskGroups".

## Recursos

- **Gerenciamento de Tarefas**: Crie, edite e exclua tarefas facilmente.
- **Agrupamento de Tarefas**: Organize suas tarefas em grupos para melhor organização e visualização.
- **Autenticação Segura**: Utilize autenticação JWT com Spring Security para proteger suas informações.
- **Criptografia de Senha**: As senhas são criptografadas utilizando bcrypt para garantir segurança adicional.
- **Interface Intuitiva**: Frontend desenvolvido com Angular e Angular Material para uma experiência de usuário moderna e responsiva.

## Tecnologias

- **Backend**: Java Spring Boot
  - **Autenticação**: JWT (JSON Web Tokens)
  - **Criptografia**: bcrypt
  - **Banco de Dados**: MySQL
- **Frontend**: Angular
  - **Componentes**: Angular Material

## Instalação e Configuração

### Backend

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/HiagoVini23/task-api-spring

2. **Navegue para o diretório do backend**:
   ```bash
   cd /backend-java

3. **Instale as dependências**:
   ```bash
   ./mvnw install

4. **Configure o banco de dados**:
   - Ajuste as configurações de conexão no arquivo application.properties.

5. **Execute o backend**:
   ```bash
   ./mvnw spring-boot:run

### Frontend

1. **Navegue para o diretório do frontend**:
   ```bash
   cd /front-todo

2. **Instale as dependências**:
   ```bash
   npm install

3. **Execute o frontend**:
   ```bash
   ng serve

A Aplicação estará disponível em http://localhost:4200

## Contribuição

Contribuições são bem-vindas! Para contribuir com o projeto, siga estas etapas:

1. **Fork o Repositório**
   - No GitHub, clique no botão "Fork" no canto superior direito do repositório para criar uma cópia do repositório em sua conta.

2. **Clone o Repositório Forkado**
   - Clone o repositório para a sua máquina local:
     ```bash
     git clone https://github.com/seuusuario/seurepositorio.git
     ```

3. **Crie uma Nova Branch**
   - Crie uma branch para suas alterações:
     ```bash
     git checkout -b minha-nova-feature
     ```

4. **Faça as Alterações Necessárias**
   - Edite, adicione ou remova arquivos conforme necessário.

5. **Commit suas Alterações**
   - Adicione e faça commit das suas alterações:
     ```bash
     git add .
     git commit -m 'Descrição clara das suas alterações'
     ```

6. **Push para a Branch**
   - Envie suas alterações para o repositório remoto:
     ```bash
     git push origin minha-nova-feature
     ```

7. **Abra um Pull Request**
   - No GitHub, vá até a página do seu repositório forkado e clique em "Compare & pull request".
   - Descreva claramente as suas alterações e submeta o pull request para revisão.

## Perguntas

Se você tiver alguma dúvida ou precisar de ajuda, sinta-se à vontade para abrir uma [issue](https://github.com/HiagoVini23/task-api-spring/issues) no repositório.






