# Resultados da Iteração 2 #

A equipe propôs o seguinte modelo de dados para a aplicação:

http://larbc.googlecode.com/files/EntidadeRelacionamento.JPG

Onde:

  * Administradores: tabela onde estarão armazenadas informações sobre os administradores do sistema.
  * Casos: tabela onde os casos serão armazenados.
  * O relacionamento adicionaCaso irá mapear qual administrados inseriu determinado caso na base de dados. Em relação a sua multiplicidade, um administrador adiciona vários casos e um caso é adicionado por um, e somente um, administrador.

Mais uma vez, o risco indisponibilidade do cliente se mostrou presente, de forma que o modelo de dados não fora validado.

O mais imediato possível, precisamos validar tais informações com o cliente para assim darmos prosseguimento à implementação da primeira release implementável.