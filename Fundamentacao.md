# Fundamentação Teórica #

Nesta seção, estão contemplados o referencial teórico dos conceitos, ferramentas e frameworks utilizados no desenvolvimento do referido projeto.


## Raciocínio Baseado em Casos ##


O Raciocínio Baseado em Casos - RBC, do Inglês Case Based Reasoning – CBR, é uma técnica de Inteligência Artificial que se propõe a, baseado em soluções usadas anteriormente, resolver problemas similares [[fonte](http://larbc.googlecode.com/files/Artigo_RBC.pdf)].


A técnica se espelha na forma que frequentemente os humanos agem para aprender novas tarefas, recorrendo a experiências anteriores para executar novos problemas ou ainda problemas comuns, mas sendo aplicados em contextos diferentes [[fonte](http://larbc.googlecode.com/files/tutorialjcolibri2.pdf)].
A aplicação do RBC é baseada em um ciclo, conforme a seguinte figura:

![http://larbc.googlecode.com/files/DiagramaRBC2.png](http://larbc.googlecode.com/files/DiagramaRBC2.png)

Como pode ser visto na figura acima, a operação normal de um sistema que usa RBC prevê o aumento da base de conhecimento, ou seja, o aprendizado é tarefa natural do processo, aumentando, assim, a capacidade de adaptação do sistema a novas situações.


## jCOLIBRI ##


É um framework para desenvolvimento de aplicações JAVA que implementa a técnica de Raciocínio Baseado em Casos.


A arquitetura do jCOLIBRI2 é montada para prover funcionalidades tanto para
desenvolvedores quanto para projetistas.


O jCOLIBRI2, versão mais atualizada do framework para o desenvolvimento das aplicações com RBC, provê:

  * Representação de um caso no contexto do sistema;
  * Estruturas para a organização da base de dados do sistema;
  * Formas de persistência dos dados;
  * Combinações de tarefas e métodos RBC que podem ser selecionados a fim de personalizar as aplicações.