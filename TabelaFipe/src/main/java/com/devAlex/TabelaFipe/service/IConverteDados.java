package com.devAlex.TabelaFipe.service;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface IConverteDados {
    <T> List<T> obterDados(String json, TypeReference<List<T>> typeReference);
}
