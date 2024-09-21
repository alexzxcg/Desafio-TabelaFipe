package com.devAlex.TabelaFipe.principal;

import com.devAlex.TabelaFipe.model.DadosModelo;
import com.devAlex.TabelaFipe.model.DadosVeiculo;
import com.devAlex.TabelaFipe.service.ConsumoApi;
import com.devAlex.TabelaFipe.service.ConverteDados;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private final String MARCAS = "/marcas/";
    private final String MODELOS = "/modelos/";
    private List<String> listaNomeVeiculos = Arrays.asList("Carro", "Moto", "Caminhão");



    public void exibeMenu(){

        for(String listaNomeVeiculo : listaNomeVeiculos){
            System.out.println(listaNomeVeiculo);
            System.out.println();
        }

        try {
            System.out.println("Digite uma das opções para consultar valores:");
            String nomeVeiculo = leitura.nextLine().toLowerCase();

            String endpoint = switch (nomeVeiculo) {
                case "carro" -> "carros";
                case "moto" -> "motos";
                case "caminhão" -> "caminhoes";
                default -> throw new IllegalArgumentException("Veículo não reconhecido");
            };


            var json = consumo.obterDados(ENDERECO + endpoint + MARCAS);

            List<DadosVeiculo> listaDadosVeiculos = conversor.obterDados(json, new TypeReference<List<DadosVeiculo>>() {});
            for (DadosVeiculo dados : listaDadosVeiculos) {
                System.out.println(dados);
            }

            System.out.println("Informe o código da marca para consulta:");

            String codigoInformado = leitura.nextLine();
            json = consumo.obterDados(ENDERECO + endpoint + MARCAS + codigoInformado + MODELOS);
            DadosModelo dadosModelo = conversor.obterDados(json, DadosModelo.class);

            List<DadosVeiculo> listaDadosVeiculosPorModelo = dadosModelo.modelos();
            for (DadosVeiculo dados : listaDadosVeiculosPorModelo) {
                System.out.println("Cód: " + dados.codigo() + " Descrição: " + dados.nome());
            }

        } catch (Exception e) {
            System.out.println("Erro ao obter dados: " + e.getMessage());
        }

    }
}
