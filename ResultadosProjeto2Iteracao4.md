# Resultados da iteração 4 #

| **Atividade** | **Responsável** | **Status** |
|:--------------|:-----------------|:-----------|
|Inserir funcionalidades na interface gráfica (novas funcionalidades que surgirem ao longo do desenvolvimento e funcionalidades já existentes na aplicação que ainda não estão inseridas na interface gráfica)|Alcione|Em andamento|
|Georreferenciar pontos de interesse|Diego|Concluída|
|Iniciar implementação de consultas (contar quantidade de pontos de interesse por tipo perto de um dado imóvel)|Diego|Em andamento|
|Terminar a implementação das funções de similaridade de avaliação de localização de pontos de interesse|João Felipe|Em andamento|
|Validação inicial de similaridade com tratamento de localização|Equipe|Em andamento|
|Documentação|Equipe|Em andamento|

## Descrição dos resultados ##

Nessa iteração foi desenvolvida a parte gráfica do gerenciamento de pontos de interesse. São passíveis de edição os seguintes tipos de pontos de interesse conforme a [Figura 1](http://larbc.googlecode.com/files/Igm%201.png): Escola, Universidade, Via Principal de Acesso, Area Verde, Shopping Center, Setor Industrial. Foi necessário criar três tipos de iterações: criar e editar entidades referenciáveis através de ponto [Figura 2](http://larbc.googlecode.com/files/Igm%202.png) e [Figura 3](http://larbc.googlecode.com/files/Igm%203.png), criar e editar entidades referenciáveis através de linha [Figura 4](http://larbc.googlecode.com/files/Igm%204.png), criar e editar entidades referenciáveis através de área [Figura 5](http://larbc.googlecode.com/files/Igm%205.png).

Devido a problemas de saúde e por ter ficado sem internet em casa durante 15 dias, a parte de implementação de consultas não foi concluída, porém foi dado um bom andamento quanto a isso.


A atividade "Inserir funcionalidades na interface gráfica" foi feita em conjunto com a correção de erros encontrados na interface e melhoras sugeridas pelo cliente, que foram:

  * Mudança na apresentação do nome na tela inicial que pode ser visto na [Figura 6](http://larbc.googlecode.com/files/inicial.PNG). Já na [Figura 7](http://larbc.googlecode.com/files/modalidades.JPG) é possível enxergar mudanças nos botões COMPRAR, como já foi citado nos resultados da iteração 3, e Administrar, que agora é diferente dos botões VENDER e COMPRAR/ALUGAR, ficando semelhante apenas ao botão Ajuda.

  * Outra mudança necessária foi mudar a forma de implementação dos formulaários de consulta e cadastro de venda. Antes, eram implementados com Textbox e passaram a ser implementados com Textfield, para permitir a validação online, como foi sugerido pela cliente. Essa validação ainda não está completa mas já pode ser visualizada na [Figura 8](http://larbc.googlecode.com/files/venda.PNG), onde há um erro no campo Nome da rua, porque o usuário deixou em branco e no cadastro de vendas isso não é permitido. O que falta é colocar alguma figura de indicação no momento em que o campo estiver validado para indicar o preenchimento para o cliente, isso já está sendo implementado.

  * Outra sugestão implementada foi a padronização da fonte em toda a interface. Isso pode ser visto na [Figura 9](http://larbc.googlecode.com/files/resultado.PNG) onde foi criado um estilo para a fonte fosse Arial, tamanho 11 entre outras configurações já utilizadas na interface. Esta implementação já está sendo feita em toda a aplicação.

  * Outro erro que estava ocorrendo é que a tecla TAB em alguns momentos não seguia a sequência correta da página. Esse problema já foi completamente resolvido.


A atividade de documentação é composta por: edição da página do projeto, elaboração de documentos que posteriormente serão inseridos na monografia e também documentação de código. Assim sendo, estará sempre em andamento até o término do projeto.