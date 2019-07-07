package ru.alexdern.liferay.security.audit.transport.support;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.Closeable;
import java.io.IOException;

public class HttpClient implements Closeable {

    private final CloseableHttpClient http;

    private String path;
    private String token;

    public HttpClient() {

        int timeout = 3;

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000)
                .build();

        http = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .build();

    }

    public void setBaseUrl(String path) {
        this.path = path;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String post(String url, String payload) throws IOException {

        HttpPost httpPost = new HttpPost(path.concat(url));

        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("X-API-KEY", token);
        httpPost.setEntity(new StringEntity(payload));

        try (CloseableHttpResponse response = http.execute(httpPost)) {
            return response.getStatusLine().toString();
        }

    }

    @Override
    public void close() throws IOException {
        http.close();
    }

}
