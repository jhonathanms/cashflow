package com.everton.cashflow.models.clients.implementacoes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RequestResponse<T> {
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public String get(String uri) throws IOException {
        Request request = new Request.Builder()
                .url(uri )
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        String json = response.body().toString();

        return json;
    }

    public String get(String uri, Map<String, String> parametros) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(uri).newBuilder();
        parametros.entrySet().forEach(
                parametro -> {
                    urlBuilder.addQueryParameter(parametro.getKey(), parametro.getValue());
                }
        );
        String uriCompleta = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(uriCompleta)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if(!response.isSuccessful()) throw new IOException("Exception code " + response);
        return response.body().toString();
    }

    public boolean post(String uri, String JsonBody) throws IOException {
        RequestBody body = RequestBody.create(JsonBody, JSON);
        Request request = new Request.Builder()
                .url(uri)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        if(!response.isSuccessful()) throw new IOException("Exception code " + response);

        return response.isSuccessful();
    }

    public boolean put(String uri, String JsonBody) throws IOException {
        RequestBody body = RequestBody.create(JsonBody, JSON);
        Request request = new Request.Builder()
                .url(uri)
                .put(body)
                .build();

        Response response = client.newCall(request).execute();
        if(!response.isSuccessful()) throw new IOException("Exception code " + response);

        return response.isSuccessful();
    }

    public boolean testeConexao(String urlBase) throws IOException {
        Request request = new Request.Builder()
                .url(urlBase)
                .build();

        Call call = client.newCall(request);
        Response response = null;

            response = call.execute();

        return response.isSuccessful();
    }

    public T converterJsonEmEntidade(String json, Class<T> tClass){
        try {
            return new ObjectMapper().readValue(json, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<?> converterJsonEmListaEntidade(String json){
        try {
            return new ObjectMapper().readValue(json, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }



}
