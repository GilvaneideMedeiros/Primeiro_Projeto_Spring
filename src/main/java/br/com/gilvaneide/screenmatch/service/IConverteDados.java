package br.com.gilvaneide.screenmatch.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe) throws Exception;
}



