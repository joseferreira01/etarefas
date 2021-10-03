
<h3 align="center">
  E-Tarefas
</h3>

<p align="center"> A aplicação  nsiste em criar um simples gerenciador de taefas com as seguintes funcionalidades:

- Criar uma tarefa
- Atualizar a tarefa
- Remover a tarefa
- Listar tarefas</p>



## 👨🏼‍💻 Responsavel

- [José Ferreira](https://github.com/joseferreira01/)

## 🚀 Tecnologias

- ⚡ JSF (JavaServer Faces)
- JPA (Java Persistence API)

## ✋🏻 Pré-requisitos

- [java ](https://www.java.com/pt-BR/) `Vesão 8 ou superior`
- [mavem](https://maven.apache.org/) `Vesão 3.8 ou superior`
- [posgreSQL](https://www.postgresql.org/) `Vesão 10 ou superior`
- Servido de aplicação nesse exemplo [payara sever](https://www.payara.fish/downloads/payara-platform-community-edition/).  

## 🔥 Instalação e execução no localhost

1. Faça um clone desse repositório;
2. Entre na pasta `cd devtop`;
3. Configure a conexao com o banco `apigithub/src/main/resources/application.properties`
4. Altere `URL, username e password .
5.Configure variáveis ambiente:
- APP_CLAENT_SECRETS
- APP_CLAENT_ID.
<p>
Essas variáveis são necessárias para utilização da API do github https://docs.github.com/pt/enterprise-server@3.0/developers/apps/building-github-apps/creating-a-github-app
</p>

5. Rode `mvn clean packag` para instalar as dependências e criar o executavel (api.jar);
6. Rode `java -jar target/api.jar` para iniciar o servidor.
7. Acesse  [use a api no localhost](http://localhost:8080/api/user)
8. Documentação [docs](http://localhost:8080/v2/api-docs)



## ⚡️ Como contribuir

- Faça um fork desse repositório;
- Cria uma branch com a sua feature: `git checkout -b minha-feature`;
- Faça commit das suas alterações: `git commit -m 'feat: Minha nova feature'`;
- Faça push para a sua branch: `git push origin minha-feature`.

Depois que o merge da sua pull request for feito, você pode deletar a sua branch.

##  Código do front and 
- [Aqui ](https://github.com/joseferreira01/devtop-front)

---

Feito com 💖 by José Ferreira 👋 [Entre na nossa comunidade!](https://github.com/joseferreira01/)
