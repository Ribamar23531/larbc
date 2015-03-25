# Testes #

Esta seção conta com a descrição dos testes que serão efetuados na aplicação ao longo do desenvolvimento.

## Testes de Unidade ##

Cada componente está sendo testado individualmente pelo integrante da equipe que o implementou. Todos os testes podem ser

acompanhados e lidos nos arquivos-fonte do projeto.

## Testes de Aceitação ##

### Consultas ###

Para um determinada quantidade de casos registrados e bem conhecidos, a lógica da aplicação será executada com várias

consultas, simulando clientes e suas diferentes necessidades, confrontando o resultado obtido com o resultado esperado.

#### Exemplo ####
- O cliente procura por um imóvel comercial, preferencialmente em algum bairro.

- Um cliente que escolhe o imóvel por seu valor comercial.

Para os testes de aceitação que envolvem consultas à base de casos, necessitamos de casos reais provenientes do cliente e de

julgamentos de quais casos serão retornados a cada consulta. O cliente real se comprometeu a nos fornecer alguns casos reais.


### Testes de Aceitação de Gerência ###

Para cada tarefa de administração como cadastrar administrador, fazer login, inserir caso no sistema, inserir demanda no sistema, alterar senha de super-usuário, serão testados cenários onde seja possível efetuar a ação e situações onde erros devem ser exibidos, como por exemplo, campos vazios, login e senha erradas.