# Função de Similaridade #

## Conceito ##

O raciocínio baseado em casos tem as suas consultas baseadas em comparações e cálculos de similaridade. Esse cálculo do coeficiente de similaridade é feito calculando um conjunto de funções de similaridade locais, uma para cada atributo envolvido, e calculando uma função de similaridade global, que sumariza todas as similaridades locais.

Para cada atributo do caso é definida uma função de similaridade local, consideremos f, onde para um par de valores, tomemos, x e y, onde x é o valor do atributo no caso, e y é o valor do mesmo atributo na consulta, f(x,y) = z, com 0  ≤ z ≤ 1.
Quando x e y são iguais, dependendo de seu tipo, a similaridade local é igual 1 e quando são totalmente contrários (segundo a semântica da função) a sua similaridade local é igual a 0.

E para configurar a importância que deve ser dada a um determinado atributo no cálculo durante uma consulta, para cada um deles é atribuído um peso, de modo a priorizar um, ou um subconjunto de atributos.

O cálculo do coeficiente de similaridade, além de fazer uso das funções de similaridade locais, particulares para cada tipo de atributo, usa uma função de similaridade global que recebe como entrada os valores das similaridades locais calculadas e os pesos dos atributos e fornece o coeficiente de similaridade entre um determinado caso da base e uma consulta realizada.

## Configuração das função de similaridade locais da aplicação ##

O jCOLIBRI2 fornece algumas funções de similaridade locais como _Treshold_, _Equal_, dentre outros comparadores simples. Todas as funções satisfazem um contrato, leia-se uma interface Java, _LocalSimilarityFunction_ que determina como uma função de similaridade deve se comportar.

Como alguns atributos (preço, localização, entre outros) não são comparados simplesmente, algumas funções de similaridade foram implementadas. Já que é deveras fácil inserir e/ou remover uma função de similaridade, o grupo decidiu que a configuração da função de similaridade também seja feita de forma incremental, mesmo que em paralelo com o desenvolvimento de outras atividades, e nesse documento poderemos acompanhar a mudança de funções de cada atributo.

Abaixo temos quais funções são utilizadas para cada atributo. São elas:

### Atributos e suas funções ###

  * **Estado**
    * Comparados de acordo suas siglas. Caso sejam iguais, similaridade igual a 1, caso contrário, similaridade igual a 0.

  * **Cidade**
    * Também comparados com o critério da igualdade.

  * **Bairro**
    * Comparados por critério de igualdade.
    * Será implementada, com a ajuda do cliente real, uma tabela com um rankeamento dos bairros de interesse e a similaridade será calculada em relação a diferença dos valores do ranking fornecido. Essa abordagem simplifica a análise, mas ainda não mapeia completamente essa comparação.

  * **Rua**
    * Comparada por critério de igualdade.
    * A busca foi aprimorada por uma consulta por trechos comuns entre os nomes da rua na consulta e do caso.

  * **Número**
    * Comparado com a igualdade numérica.

  * **Nome**
    * Comparado por critério de igualdade.

  * **Área construida**
    * É utilizado o critério de igualdade numérica.

  * **Área total**
    * É utilizado o critério de igualdade numérica.

  * **Vagas na garagem**
    * Verificação de igualdade numérica.

  * **Quartos**
    * Verificação de igualdade numérica.

  * **Suites**
    * Verificação de igualdade numérica.

  * **Banheiros**
    * Verificação de igualdade numérica.

  * **Tipo**
    * Verificar se o imóvel é do mesmo tipo, sendo do mesmo tipo, similaridade 1, não sendo similaridade 0.

  * **Preço**
    * Comparados segundo a igualdade numérica.
    * A função foi melhorada com uma comparação de acordo com um intervalo relativo à consulta do usuário.

  * http://larbc.googlecode.com/files/IntervaloRelativo.PNG , se o valor está no intervalo.

  * Caso contrário a similaridade é 0.
Estamos estudando a possibilidade de usar essa função em mais atributos.

  * **Tipo de Negócio**
    * Verificação de igualdade.

## Configuração da função de similaridade global ##

O jCOLIBRI2 fornece uma função de similaridade global que é a _Average_, que calcula a média dos valores avaliados das funções de similaridade locais, ponderada pelos pesos configurados. Então o coeficiente de similaridade entre uma consulta e um caso é calculado da seguinte forma:

http://larbc.googlecode.com/files/SimGlobal.PNG