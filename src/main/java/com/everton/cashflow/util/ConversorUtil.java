package com.everton.cashflow.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;

public class ConversorUtil<T> {
    private static ConversorUtil conversorUtil;

    public static ConversorUtil getInstance(){
        return Objects.nonNull(conversorUtil)
                ? conversorUtil
                : new ConversorUtil();
    }

    public T converterJsonEmEntidade(String json, Class<T> tClass){
        try {
            return new ObjectMapper().readValue(json, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> converterJsonEmListaEntidade(String json){
        try {
            return new ObjectMapper().readValue(json, new TypeReference<List<T>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String converterEntidadeEmJson(T entidade){
        try {
            return new ObjectMapper().writer().writeValueAsString(entidade);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
