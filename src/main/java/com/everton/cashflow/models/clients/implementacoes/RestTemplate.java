package com.everton.cashflow.models.clients.implementacoes;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class RestTemplate<T> {
    OkHttpClient client = new OkHttpClient();
    private static RestTemplate restTemplate;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static RestTemplate getInstance(){
        return Objects.nonNull(restTemplate)
                ? restTemplate
                : new RestTemplate();
    }

    public String get(String uri){
        Request request = new Request.Builder()
                .url(uri )
                .build();

        Call call = client.newCall(request);
        Response response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String json = response.body().toString();

        return json;
    }
    public String getById(String uri, Long id){
        String uriCompleta = Objects.nonNull(id) ? uri.concat("/" + id) : uri;
        Request request = new Request.Builder()
                .url(uriCompleta)
                .build();

        Call call = client.newCall(request);
        Response response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String json = response.body().toString();

        return json;
    }

    public String get(String uri, Map<String, String> parametros){
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
        Response response = null;
        try {
            response = call.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!response.isSuccessful())
            try {
                throw new IOException("Exception code " + response);
            } catch (IOException e) {
                e.printStackTrace();
            }

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

    public boolean put(String uri, Long id, String JsonBody) {
        RequestBody body = RequestBody.create(JsonBody, JSON);
        String uriCompleta = Objects.nonNull(id) ? uri.concat("/" + id) : uri;
        Request request = new Request.Builder()
                .url(uriCompleta)
                .put(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Exception code " + response);

            return response.isSuccessful();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String uri, Long id){
        if(Objects.nonNull(id)) {
            try {
                throw new IOException("Informe um ID válido");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Request request = new Request.Builder()
                .url(uri)
                .delete()
                .build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
            if(!response.isSuccessful())
                throw new IOException("Exception code " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }

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





}
