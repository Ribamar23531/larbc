# Projeto Arquitetural #

O projeto arquitetural se dá basicamente conforme a fugura abaixo:

http://larbc.googlecode.com/files/Arquitetura.PNG

## Descrição ##

O sistema será composto basicamente por dois módulos, um gerente e uma interface.

### Módulo RBC ###

Será responsável por toda a representação, aprendizagem e inferência de conhecimento.

### Módulo de persistência ###

Será responsável pelo armazenamento de dados como contas de administradores.

### Gerente do Sistema ###

Conhecerá os módulos do sistema e delegará atividades para os módulos responsáveis.

### Interface GWT ###

A interface que será apresentada ao cliente via browser será feita utilizando o framework GWT.

## Decisões Arquiteturais ##

|Decisão 1 |A aplicação é WEB|
|:----------|:-------------------|
|Prós |Pode ser acessada de qualquer lugar desde que se disponha de uma conexão a internet.|
|Contras |Requer uma estrutura mais complexa de implantação e de operação.|
|Infruência |Acesso simultâneo, em qualquer lugar e de qualquer Sistema Operacional.|

|Decisão 2 |Deve ser independente de browser|
|:----------|:-------------------------------|
|Prós |Não importa qual o browser que o usuário esteja utilizando, a aplicação deverá ser corretamente renderizada em seu display.|
|Contras |Necessita de alguns cuidados na hora de escrever os códigos, de modo a sempre verificar qual o browser utilizado.|
|Infruência |Acesso universal dos usuários.|

|Decisão 3 |Uso do framework GWT|
|:----------|:-------------------|
|Prós |É gratuito, oferece interface com usuário elegante, permite que o desenvolvedor codifique essa interface em Java, que será convertida para AJAX, provendo comportamento assíncrono.Tecnologia já conhecida pela maioria dos integrantes da equipe.|
|Contras |Requer a manutenção de arquivos .xml de configuração.|
|Infruência |O GWT gera o código apropriado para cada browser utilizado pelo cliente, provendo assim a independência de browser.|

|Decisão 4 |Uso do servidor aplicação web Tomcat|
|:----------|:-------------------------------------|
|Prós |É um servidor gratuito, de fácil configuração e manuseio.|
|Contras |Não dá suporte a soluções mais avançadas, como EJB, o que poderia ser necessário se a aplicação crescesse muito.|
|Infruência |A disponibilização da aplicação, no que concerne ao servidor utilizado, é de forma gratuita e facilitada.|

|Decisão 5 |Uso do jCOLIBRI2|
|:----------|:---------------|
|Prós |Provê o tratamento do raciocínio baseado em casos que será usado no LARbc.|
|Contras |É mais uma tecnologia a ser estudada pela equipe, o que demanda tempo.|
|Infruência |Facilidade de implementação.|

|Decisão 6 |Uso do framework Hibernate|
|:----------|:-------------------------|
|Prós |É gratuito e provê a abstração para a persistência de dados da aplicação.|
|Contras |A forma de configuração não é muito trivial.|
|Infruência |Persistência de dados mais facilitada|

|Decisão 7 |Uso do SGBD MySQL|
|:----------|:----------------|
|Prós |É gratuito e fornece acesso (tanto leitura quanto escrita) de forma mais eficiente que outros SGBDs gratuitos [[fonte](http://larbc.googlecode.com/files/8330.pdf)].|
|Contras |Existem SGBDs que proveem mais segurança que o MySQL.|
|Infruência |Forma de persistir os dados gerados/processados pela aplicação.|