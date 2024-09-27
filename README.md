# Java Spring Desafio Tabela FIPE
## Objetivo:
> Desenvolver uma aplicação back-end em Java utilizando o Spring Framework com uma interface de linha de comando (CLI), para realizar consultas à [API FIPE HTTP REST](https://deividfortuna.github.io/fipe/). A aplicação permite pesquisar dados de veículos no Brasil, retornando informações como preços e características, semelhante ao que é oferecido no site da [Tabela FIPE](https://veiculos.fipe.org.br/). O foco do projeto é consolidar os conhecimentos adquiridos no curso "Java: Trabalhando com Lambdas, Streams e Spring Framework" da Alura.

## Funcionalidades
- Consulta de veículos: Pesquise por carros, motos ou caminhões na base de dados da FIPE.
- Integração com a API FIPE: Realiza requisições para a API FIPE e processa os dados recebidos.
- Manipulação de dados: Uso de lambdas e Streams do Java para filtrar e processar informações de forma eficiente.
- Interface de linha de comando (CLI): Permite ao usuário realizar consultas interativas via terminal.

## Fluxo de Execução
- Ao iniciar a aplicação, será exibido o seguinte menu:
    ~~~~
    **** OPÇÕES ****

    Carro
    
    Moto
    
    Caminhão
    
    Digite uma das opções para consultar valores:
    ~~~~
 - O usuário deve digitar "Carro", "Moto" ou "Caminhão" para continuar a consulta.
      ~~~~
      Carro
      ~~~~
- Seleção da Marca:
    - Após escolher o tipo de veículo, a aplicação retornará uma lista de marcas com seus respectivos códigos:
    ~~~~
   Cód: 21 Descrição: Fiat
    Cód: 54 Descrição: Subaru
    Cód: 59 Descrição: VW - VolksWagen
    ...
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
  
  Digite o código do modelo para consultar valores:
  ~~~~
- Vamos escolher, por exemplo, o código 545, referente ao Palio Weekend.
  ~~~~
  545
  ~~~~
- Consulta de Preços por Ano:
    - Após selecionar o modelo, a aplicação exibirá os preços de todos os anos disponíveis para o veículo escolhido:
  ~~~~
  Todos os veículos com os valores por ano:

  Veiculo [valor=R$ 15.872,00, marca=Fiat, modelo=Palio Weekend Adventure 1.6 8V/16V, ano=2003, combustivel=Gasolina
  
  Veiculo [valor=R$ 14.403,00, marca=Fiat, modelo=Palio Weekend Adventure 1.6 8V/16V, ano=2002, combustivel=Gasolina
  
  Veiculo [valor=R$ 13.435,00, marca=Fiat, modelo=Palio Weekend Adventure 1.6 8V/16V, ano=2001, combustivel=Gasolina
  
  Veiculo [valor R$ 11.986,00, marca=Fiat, modelo=Palio Weekend Adventure 1.6 8V/16V, ano=2000, combustivel=Gasolina
  
  Veiculo [valor R$ 10.788,00, marca=Fiat, modelo=Palio Weekend Adventure 1.6 8V/16V, ano=1999, combustivel=Gasolina
  ~~~~
## Desafios no Desenvolvimento
### Deserialização e Manipulação de Listas
- Ao trabalhar com a API FIPE, foi necessário deserializar a resposta JSON em uma lista de objetos. A biblioteca TypeReference do Jackson foi essencial para informar que os dados deveriam ser convertidos para uma lista de objetos específicos (DadosVeiculo).
  
### Consulta Detalhada dos Veículos
- Um dos maiores desafios foi a implementação da busca por informações detalhadas dos veículos por ano. Inicialmente, usei um loop for para iterar sobre cada ano e obter os dados detalhados, mas depois otimizei o código utilizando Streams e Lambdas, como mostrado abaixo:
    - Implementação Inicial
  ~~~~
  var jsonAnos = consumo.obterDados(ENDERECO + endpoint + MARCAS + codigoMarca + MODELOS + codigoModelo + ANOS);
  List<DadosVeiculo> listaAnos = conversor.obterListaDados(jsonAnos, new TypeReference<List<DadosVeiculo>>() {});
  for (DadosVeiculo ano : listaAnos) {
                String codigoAno = ano.codigo(); // Obter o código do ano
                var jsonDetalhes = consumo.obterDados(ENDERECO + endpoint + MARCAS + codigoMarca + MODELOS + codigoModelo + "/anos/" + codigoAno);
                // Aqui você deve deserializar a resposta para um objeto do tipo DadosVeiculoPorAno
                DadosVeiculoPorAno detalhesVeiculo = conversor.obterDados(jsonDetalhes, DadosVeiculoPorAno.class);
                // Exibir as informações
                System.out.println(detalhesVeiculo.toString());
            }
  ~~~~

   - Implementação Otimizada com Streams e Lambdas
  ~~~~
   var jsonAnos = consumo.obterDados(ENDERECO + endpoint + MARCAS + codigoMarca + MODELOS + codigoModelo + ANOS);
   List<DadosVeiculo> listaAnos = conversor.obterListaDados(jsonAnos, new TypeReference<List<DadosVeiculo>>() {});
   listaAnos.stream()
                    .map(ano -> {
                        String codigoAno = ano.codigo(); // Obter o código do ano
                        var jsonDetalhes = consumo.obterDados(ENDERECO + endpoint + MARCAS + codigoMarca + MODELOS + codigoModelo + ANOS + codigoAno);
                        // Deserializar a resposta para um objeto do tipo DadosVeiculoPorAno
                        return conversor.obterDados(jsonDetalhes, DadosVeiculoPorAno.class);
                    })
                    .forEach(detalhesVeiculo -> {
                        // Exibir as informações detalhadas do veículo
                        System.out.println(detalhesVeiculo.toString());
                    });
  ~~~~
  
## Como Executar o Projeto
- Clone este repositório:
  ~~~~
  git clone https://github.com/usuario/tabela-fipe-cli.git
  ~~~~
- Acesse a pasta do projeto:
  ~~~~
  cd tabela-fipe-cli
  ~~~~
- Execute o projeto:
  ~~~~
  ./mvnw spring-boot:run
  ~~~~
## Tecnologias Utilizadas
- Java 21 LTS
- Spring Boot
- API REST FIPE
- Jackson (para serialização/deserialização de JSON)
- Maven
  
## Conclusão
- Este projeto foi uma oportunidade de consolidar o aprendizado do curso "Java: Trabalhando com Lambdas, Streams e Spring Framework", aplicando conceitos importantes na integração com APIs externas e manipulando dados de maneira eficiente com Streams e Lambdas.
