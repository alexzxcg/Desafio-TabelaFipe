# Java Spring Desafio TabelaFipe
# Objetivo:
> Como desafio proposto após a conclusão do curso 'Java: Trabalhando com Lambdas, Streams e Spring Framework' da Alura, foi desenvolvida uma aplicação back-end em Java,
> utilizando o Spring Framework com interface de linha de comando (CLI). O objetivo é realizar consultas à [API FIPE HTTP REST](https://deividfortuna.github.io/fipe/), que fornece informações sobre veículos no Brasil.
> A aplicação permite que os usuários pesquisem dados, preços e características de veículos, de forma similar ao que é oferecido no site da [Tabela FIPE](https://veiculos.fipe.org.br/). Terá como objetivo consolidar os conhecimentos obtidos.

# Características do Projeto:
- Integração com [FIPE HTTTP REST API](https://deividfortuna.github.io/fipe/): A aplicação fará requisições para obter informações sobre veiculos, caminhões ou motos, processando e manipulando esses dados para fornecer uma experiência robusta de consultas.
- Spring Boot em Ação: O principal objetivo é consolidar conhecimentos sobre os recursos e funcionalidades do Spring Boot, explorando sua capacidade de lidar com operações de back-end de forma eficiente e escalável.
- Ultlização de Lambdas e Streams: Ultilização das funções lambdas e a API de Streams do Java serão utilizadas para manipular e filtrar os dados fornecidos pela API FIPE, permitindo um processamento eficiente e simplificado das informações de veículos, como preços e características, de acordo com as consultas realizadas pelos usuários.

# O Que Esperar:
- Na tela da aplicação, temos a primeira pergunta:
    ~~~~
    **** OPÇÕES ****

    Carro
    
    Moto
    
    Caminhão
    
    Digite uma das opções para consultar valores:
    ~~~~
- Vamos escolher "Carro", digitando essa palavra na tela, abaixo da pergunta.
  ~~~~
  Carro
  ~~~~
- Após o "Enter", o programa devolverá uma lista de códigos de Marcas.
    ~~~~
    Retorno omitido
    
    Cód: 5 Descrição: Asia Motors
    
    Cód: 21 Descrição: Fiat
    
    Cód: 54 Descrição: Subaru
    
    Cód: 55 Descrição: Suzuki
    
    Cód: 56 Descrição: Toyota
    
    Cód: 57 Descrição: Troller
    
    Cód: 58 Descrição: Volvo
    
    Cód: 59 Descrição: VW - VolksWagen
    
    Cód: 6 Descrição: Audi
    
    Cód: 7 Descrição: BMW
    
    Cód: 8 Descrição: BRM
    
    Informe o código da marca para consulta:
    ~~~~
- Então, pela string, a Fiat é a 21. Vamos escrever que queremos consultar a marca 21.
    ~~~~
    21
    ~~~~
- Então, na próxima questão, ele pergunta, qual é o carro que queremos e pede o nome dele.
  ~~~~
  Retorno omitido
  
  Cód: 9649 Descrição: PULSE IMPETUS 1.0 TURBO 200 Flex Aut

  Cód: 9723 Descrição: Strada Ranch 1.3 Flex 8V CD Aut.
  
  Cód: 9724 Descrição: Strada Volcano 1.3 Flex 8V CD Aut.
  
  Cód: 9725 Descrição: UNO CIAO 1.0 Fire Flex 8V 5p
  
  Cód: 9919 Descrição: Scudo Cargo 1.5 16V Turbo Diesel
  
  Cód: 9920 Descrição: Scudo Multi 1.5 16 V Turbo Diesel
  
  Cód: 9988 Descrição: CRONOS 1.0 6V Flex
  
  Cód: 9989 Descrição: CRONOS DRIVE 1.0 6V Flex
  
  Cód: 9990 Descrição: CRONOS DRIVE 1.3 8V Flex Aut.
  
  Digite um trecho do nome do veículo para consulta:
  ~~~~

- Suponhamos que queremos saber quanto vale um carro chamado Palio, um carro comum.
  ~~~~
  Retorno omitido

  Cód: 554 Descrição: Palio Weekend ELX 1.6 mpi
  
  Cód: 555 Descrição: Palio Weekend EX 1.3 mpi Fire 8V 67cv 4p
  
  Cód: 556 Descrição: Palio Weekend EX 1.5 mpi
  
  Cód: 557 Descrição: Palio Weekend EX 1.8 mpi 8V 103cv 4p
  
  Cód: 558 Descrição: Palio Weekend HLX 1.8 mpi Flex 4p
  
  Cód: 559 Descrição: Palio Weekend Sport 1.6 mpi 16V 4p
  
  Cód: 560 Descrição: Palio Weekend Stile 1.6 mpi 16V 4p
  
  Cód: 561 Descrição: Palio Weekend Stile 1.8 mpi 8V 103cv 4p
  
  Cód: 4674 Descrição: Palio Weekend Trekking 1.4 Fire Flex 8V
  
  Cód: 5412 Descrição: Palio Weekend Trekking 1.6 Flex 16V 5p
  
  Cód: 5063 Descrição: Palio Weekend Trekking 1.8 mpi Flex 8V
  
  Cód: 562 Descrição: Palio Young 1.0 mpi 8v 2p
  
  Cód: 563 Descrição: Palio Young 1.0 mpi 8v 4p
  
  Cód: 564 Descrição: Palio Young 1.0 mpi Fire 8V 2p
  
  Cód: 565 Descrição: Palio Young 1.0 mpi Fire 8V 4p
  
  Digite o código do modelo para consultar valores:
  ~~~~
- Vamos escolher, por exemplo, o código 545, referente ao Palio Weekend.
  ~~~~
  545
  ~~~~
- E a ideia do processo é que ele mostre todos os anos cadastrados lá na tabela FIPE e seus valores.
  ~~~~
  Todos os veículos com os valores por ano:

  Veiculo [valor=R$ 15.872,00, marca=Fiat, modelo=Palio Weekend Adventure 1.6 8V/16V, ano=2003, combustivel=Gasolina
  
  Veiculo [valor=R$ 14.403,00, marca=Fiat, modelo=Palio Weekend Adventure 1.6 8V/16V, ano=2002, combustivel=Gasolina
  
  Veiculo [valor=R$ 13.435,00, marca=Fiat, modelo=Palio Weekend Adventure 1.6 8V/16V, ano=2001, combustivel=Gasolina
  
  Veiculo [valor R$ 11.986,00, marca=Fiat, modelo=Palio Weekend Adventure 1.6 8V/16V, ano=2000, combustivel=Gasolina
  
  Veiculo [valor R$ 10.788,00, marca=Fiat, modelo=Palio Weekend Adventure 1.6 8V/16V, ano=1999, combustivel=Gasolina
  ~~~~
# Desafios Enfrentados no Desenvolvimento
- Enfrentei o desafio de manipular os dados recebidos em uma lista. Após muita pesquisa, descobri a importância da biblioteca TypeReference, que informa ao Jackson que o objeto a ser deserializado é uma lista de DadosVeiculo. Com         isso, consegui armazenar e listar os dados de forma eficiente.
- Mas o maior dos desafios foi a implementação da consulta que busca informações detalhadas dos veículos. Para isso, precisei iterar sobre cada ano obtido para um modelo específico, modificando a URL em cada iteração para coletar todos os dados registrados.         Inicialmente, considerei uma abordagem "manual" utilizando um loop for para essa tarefa.

    Após conseguir os resultados esperados, busquei otimizar o código, explorando como poderia utilizar streams e lambdas para realizar essa consulta de maneira mais funcional. Depois de algumas pesquisas e testes, finalizei a implementação, obtendo o resultado que     atendia ao desafio proposto de forma eficiente.

