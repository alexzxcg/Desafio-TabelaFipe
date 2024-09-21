package com.devAlex.TabelaFipe.service;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
    <T> List<T> obterDados(String json, TypeReference<List<T>> typeReference);
}
