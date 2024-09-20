package com.devAlex.TabelaFipe.service;

import com.devAlex.TabelaFipe.model.DadosVeiculo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConverteDados implements  IConverteDados {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> List<T> obterDados(String json, TypeReference<List<T>> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
