package com.devAlex.TabelaFipe.principal;

import com.devAlex.TabelaFipe.model.DadosModelo;
import com.devAlex.TabelaFipe.model.DadosVeiculo;
import com.devAlex.TabelaFipe.model.DadosVeiculoPorAno;
import com.devAlex.TabelaFipe.service.ConsumoApi;
import com.devAlex.TabelaFipe.service.ConverteDados;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    // Instâncias de classes para consumo da API e conversão de dados
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    // Constantes para construção de URLs da API FIPE
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private final String MARCAS = "/marcas/";
    private final String MODELOS = "/modelos/";
    private final String ANOS = "/anos/";

    // Lista de tipos de veículos disponíveis
    private List<String> listaNomeVeiculos = Arrays.asList("Carro", "Moto", "Caminhão");


    // Método que exibe o menu e gerencia a lógica de consulta
    public void exibeMenu(){

        // Exibir as opções de veículos disponíveis
        for(String listaNomeVeiculo : listaNomeVeiculos){
            System.out.println(listaNomeVeiculo);
            System.out.println();
        }

        try {
            // Solicitar ao usuário que escolha um tipo de veículo
            System.out.println("Digite uma das opções para consultar valores:");
            String nomeVeiculo = leitura.nextLine().toLowerCase();

            // Definir o endpoint com base na escolha do usuário
            String endpoint = switch (nomeVeiculo) {
                case "carro" -> "carros";
                case "moto" -> "motos";
                case "caminhão" -> "caminhoes";
                default -> throw new IllegalArgumentException("Veículo não reconhecido");
            };

            // Obter a lista de marcas disponíveis para o tipo de veículo escolhido
            var json = consumo.obterDados(ENDERECO + endpoint + MARCAS);
            List<DadosVeiculo> listaDadosVeiculosPorMarca = conversor.obterDados(json, new TypeReference<List<DadosVeiculo>>() {});

            // Exibir todas as marcas obtidas
            for (DadosVeiculo dados : listaDadosVeiculosPorMarca) {
                System.out.println(dados);
            }

            // Solicitar ao usuário o código da marca desejada
            System.out.println("Informe o código da marca para consulta:");
            String codigoMarca = leitura.nextLine();
            json = consumo.obterDados(ENDERECO + endpoint + MARCAS + codigoMarca + MODELOS);
            DadosModelo dadosModelo = conversor.obterDados(json, DadosModelo.class);

            // Exibir os modelos disponíveis da marca escolhida
            List<DadosVeiculo> listaDadosVeiculosPorModelo = dadosModelo.modelos();
            for (DadosVeiculo dados : listaDadosVeiculosPorModelo) {
                System.out.println("Cód: " + dados.codigo() + " Descrição: " + dados.nome());
            }

            // Permitir ao usuário filtrar veículos por nome
            System.out.println("Digite um trecho do nome do veículo para consulta:");
            String trechoNomeBuscado = leitura.nextLine().toLowerCase();
            List<DadosVeiculo> veiculosFiltrados = listaDadosVeiculosPorModelo.stream()
                    .filter(dados -> dados.nome().toLowerCase().contains(trechoNomeBuscado))
                    .collect(Collectors.toList());


            // Exibir veículos filtrados ou mensagem se nenhum veículo for encontrado
            if (veiculosFiltrados.isEmpty()) {
                System.out.println("Nenhum veículo encontrado");
            } else {
                veiculosFiltrados.forEach(dados ->
                        System.out.println("Cód: " + dados.codigo() + " Descrição: " + dados.nome())
                );
            }

            // Solicitar ao usuário o código do modelo desejado
            System.out.println("Digite o código do modelo:");
            String codigoModelo = leitura.nextLine();


            // Obter a lista de anos disponíveis para o modelo escolhido
            var jsonAnos = consumo.obterDados(ENDERECO + endpoint + MARCAS + codigoMarca + MODELOS + codigoModelo + ANOS);
            List<DadosVeiculo> listaAnos = conversor.obterDados(jsonAnos, new TypeReference<List<DadosVeiculo>>() {});

            // Para cada ano, obter e exibir os detalhes do veículo
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

            } catch (Exception e) {
            // Capturar e exibir erros que ocorreram durante a execução
            System.out.println("Erro ao obter dados: " + e.getMessage());
        }

    }
}
