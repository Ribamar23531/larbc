# Resultados da Release Final #

No dia 20/07/2009 foi apresentada a Professora Joseana a release final de projeto 1, foi feita uma demonstração do executável da nossa aplicação que se compõe da lógica do negócio (ciclo RBC e persistência de dados) toda implementada e testada e a interface gráfica.

As funcionalidades de nosso sistema que foram demonstradas para a professora Joseana são as seguintes:

  * O cliente da imobiliária pode escolher entre duas modalidades de serviço no sistema:
    * Comprar
      * O usuário pode realizar uma pesquisa por imóveis, preenchendo os dados que caracterizam o imóvel que ele deseja em um formulário
      * Os resultados dessa consulta retornam como uma lista ordenada de acordo com a similaridade dos casos existentes na nossa base de dados e caso preenchido pelo usuário
      * Caso o usuário não encontre o que queria, ele tem a opção de enviar as informações preenchidas por ele para o administrador da imobiliária (o caso é armazenado como demanda e um e-mail de alerta é enviado para o administrador)
    * Vender
      * O usuário pode cadastrar um imóvel para para vender através da imobiliária (esse cadastro será armazenado como demanda e um e-mail de alerta será enviado para o administrador)

O Bigchart atual pode ser acessado [aqui](BigChart.md).

Um documento, que está disponível [aqui](http://larbc.googlecode.com/files/Caso%20de%20Uso%20da%20Ferramenta.doc), descreve algumas funcionalidades do sistema através de capturas de tela.