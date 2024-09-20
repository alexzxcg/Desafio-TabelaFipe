package com.devAlex.TabelaFipe.principal;

import com.devAlex.TabelaFipe.service.ConsumoApi;
import com.devAlex.TabelaFipe.service.ConverteDados;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private final String MARCAS = "/marcas";
    private List<String> listaNomeVeiculos = Arrays.asList("Carro", "Moto", "Caminhão");



    public void exibeMenu(){

        for(String listaNomeVeiculo : listaNomeVeiculos){
            System.out.println(listaNomeVeiculo);
            System.out.println();
        }

        try {
            System.out.println("Digite uma das opções para consultar valores:");
            String nomeVeiculo = leitura.nextLine();
            nomeVeiculo.toLowerCase();

        } catch (Exception e) {
            System.out.println("Nome inválido");;
        }

    }
}
