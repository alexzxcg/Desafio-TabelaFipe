package com.devAlex.TabelaFipe.service;

import com.devAlex.TabelaFipe.model.DadosModelo;
import com.devAlex.TabelaFipe.model.DadosVeiculo;
import com.devAlex.TabelaFipe.model.DadosVeiculoPorAno;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.stream.Collectors;

public class VeiculoService {
    private final ConsumoApi consumo;  // Instância da classe para consumir a API
    private final ConverteDados conversor;  // Instância da classe para converter dados
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/"; // URL base da API
    private final String MARCAS = "/marcas/";  // Endpoint para marcas
    private final String MODELOS = "/modelos/";  // Endpoint para modelos
    private final String ANOS = "/anos/";  // Endpoint para anos

    public VeiculoService() {
        this.consumo = new ConsumoApi();  // Inicializa o consumo da API
        this.conversor = new ConverteDados();  // Inicializa a conversão de dados
    }

    // Método para obter marcas disponíveis para um tipo de veículo
    public List<DadosVeiculo> obterMarcas(String tipoVeiculo) {
        var json = consumo.obterDados(ENDERECO + tipoVeiculo + MARCAS);
        return conversor.obterDados(json, new TypeReference<List<DadosVeiculo>>() {});
    }

    // Método para obter modelos disponíveis para uma marca específica
    public DadosModelo obterModelos(String tipoVeiculo, String codigoMarca) {
        var json = consumo.obterDados(ENDERECO + tipoVeiculo + MARCAS + codigoMarca + MODELOS);
        return conversor.obterDados(json, DadosModelo.class);
    }

    // Método para filtrar a lista de veículos por um trecho do nome
    public List<DadosVeiculo> filtrarVeiculosPorNome(List<DadosVeiculo> veiculos, String trechoNome) {
        return veiculos.stream()
                .filter(dados -> dados.nome().toLowerCase().contains(trechoNome))
                .collect(Collectors.toList());
    }

    // Método para obter detalhes dos veículos por ano para um modelo específico
    public List<DadosVeiculoPorAno> obterDetalhesPorAno(String tipoVeiculo, String codigoMarca, String codigoModelo) {
        var jsonAnos = consumo.obterDados(ENDERECO + tipoVeiculo + MARCAS + codigoMarca + MODELOS + codigoModelo + ANOS);
        List<DadosVeiculo> listaAnos = conversor.obterDados(jsonAnos, new TypeReference<List<DadosVeiculo>>() {});

        // Mapeia cada ano para obter os detalhes do veículo
        return listaAnos.stream()
                .map(ano -> {
                    String codigoAno = ano.codigo();
                    var jsonDetalhes = consumo.obterDados(ENDERECO + tipoVeiculo + MARCAS + codigoMarca + MODELOS + codigoModelo + ANOS + codigoAno);
                    return conversor.obterDados(jsonDetalhes, DadosVeiculoPorAno.class);
                })
                .collect(Collectors.toList());
    }
}