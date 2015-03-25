# Resultados da Iteração 3 #

Abaixo estão as tarefas definidas e os status momentâneos de cada uma delas.

| Tarefa | Membro Responsável | Status |
|:-------|:--------------------|:-------|
| Enriquecer o modelo de dados | Alcione, Diego, Felipe | Completo |
| Criação dos elementos Hibernate | Diego | Completo |
| Implementação do ciclo de consultas RBC | Alcione, Felipe | Em andamento |
| Criação de protótipo de interface | Alcione | Em andamento |

## Modelo de Dados ##

Após a validação do cliente, o modelo de dados foi redefinido e pode ser visto abaixo:

![http://larbc.googlecode.com/files/DBModel.png](http://larbc.googlecode.com/files/DBModel.png)

  * A tabela **Administradores** armazena informações sobre os administradores do sistema.
  * A tabela **Casos** armazena dados sobre o caso da aplicação, que pode ser um apartamento, uma residência, um terreno ou qualquer que seja o imóvel que a imobiliária detenha e pretenda negociar.
  * A tabela **Fotos** armazena caminhos de fotos para cada caso do sistema.
  * A tabela **Demandas** armazena demandas de usuários com relação a imobiliária.

## Elementos Hibernate ##

Todas as classes que serão persistidas no banco de dados quando as classes que manipulam os dados do banco, juntamente com métodos básicos de CRUD foram implementados. Alguma operação que venha a ser necessária será adicionada ao longo do desenvolvimento.

## Ciclo RBC ##

As etapas do ciclo de consultas RBC foram feitas, mas ainda faltando serem testadas, e integradas ao banco de dados "físico", pois ainda está acessando um banco de dados em memória.

Como resumo dos resultados, temos um bigchart com os número de artefatos gerados que pode ser acessado [aqui](BigChart.md).