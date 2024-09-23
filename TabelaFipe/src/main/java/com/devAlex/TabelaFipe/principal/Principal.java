package com.devAlex.TabelaFipe.principal;

import com.devAlex.TabelaFipe.model.DadosModelo;
import com.devAlex.TabelaFipe.model.DadosVeiculo;
import com.devAlex.TabelaFipe.model.DadosVeiculoPorAno;
import com.devAlex.TabelaFipe.service.VeiculoService;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private VeiculoService veiculoService = new VeiculoService();
    private List<String> listaNomeVeiculos = List.of("Carro", "Moto", "Caminhão");

    // Método que exibe o menu e gerencia a lógica de consulta
    public void exibeMenu() {
        // Exibe as opções de veículos disponíveis
        for (String listaNomeVeiculo : listaNomeVeiculos) {
            System.out.println(listaNomeVeiculo);
        }

        try {
            System.out.println("Digite uma das opções para consultar valores:");
            String nomeVeiculo = leitura.nextLine().toLowerCase();

            // Define o endpoint com base na escolha do usuário
            String endpoint = switch (nomeVeiculo) {
                case "carro" -> "carros";
                case "moto" -> "motos";
                case "caminhão" -> "caminhoes";
                default -> throw new IllegalArgumentException("Veículo não reconhecido");
            };

            // Obtém a lista de marcas disponíveis para o tipo de veículo escolhido
            List<DadosVeiculo> listaMarcas = veiculoService.obterMarcas(endpoint);
            listaMarcas.forEach(System.out::println);

            // Solicita ao usuário o código da marca desejada
            System.out.println("Informe o código da marca para consulta:");
            String codigoMarca = leitura.nextLine();
            DadosModelo dadosModelo = veiculoService.obterModelos(endpoint, codigoMarca);
            List<DadosVeiculo> listaModelos = dadosModelo.modelos();
            listaModelos.forEach(dados -> System.out.println("Cód: " + dados.codigo() + " Descrição: " + dados.nome()));

            // Permite ao usuário filtrar veículos por nome
            System.out.println("Digite um trecho do nome do veículo para consulta:");
            String trechoNomeBuscado = leitura.nextLine().toLowerCase();
            List<DadosVeiculo> veiculosFiltrados = veiculoService.filtrarVeiculosPorNome(listaModelos, trechoNomeBuscado);

            // Exibe veículos filtrados ou mensagem se nenhum veículo for encontrado
            if (veiculosFiltrados.isEmpty()) {
                System.out.println("Nenhum veículo encontrado");
            } else {
                veiculosFiltrados.forEach(dados -> System.out.println("Cód: " + dados.codigo() + " Descrição: " + dados.nome()));
            }

            // Solicita ao usuário o código do modelo desejado
            System.out.println("Digite o código do modelo:");
            String codigoModelo = leitura.nextLine();
            List<DadosVeiculoPorAno> detalhesVeiculos = veiculoService.obterDetalhesPorAno(endpoint, codigoMarca, codigoModelo);
            System.out.println("Todos os veículos com os valores por ano:");
            detalhesVeiculos.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Erro ao obter dados: " + e.getMessage());
        }
    }
}