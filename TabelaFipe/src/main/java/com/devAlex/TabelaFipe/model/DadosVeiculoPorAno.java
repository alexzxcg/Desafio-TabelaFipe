package com.devAlex.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosVeiculoPorAno {
    @JsonAlias("TipoVeiculo")
    private int tipoVeiculo;

    @JsonAlias("Valor")
    private String valor;

    @JsonAlias("Marca")
    private String marca;

    @JsonAlias("Modelo")
    private String modelo;

    @JsonAlias("AnoModelo")
    private int anoModelo;

    @JsonAlias("Combustivel")
    private String combustivel;

    // Getters
    public int getTipoVeiculo() {
        return tipoVeiculo;
    }

    public String getValor() {
        return valor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    @Override
    public String toString() {
        return "Veiculo [valor=" + valor +
                ", marca=" + marca +
                ", modelo=" + modelo +
                ", ano=" + anoModelo +
                ", combustivel=" + combustivel + "]";
    }
    
}