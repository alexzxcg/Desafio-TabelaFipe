package com.devAlex.TabelaFipe.service;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface IConverteDados {
    <T> T obterListaDados(String json, Class<T> classe);
    <T> List<T> obterListaDados(String json, TypeReference<List<T>> typeReference);
}
