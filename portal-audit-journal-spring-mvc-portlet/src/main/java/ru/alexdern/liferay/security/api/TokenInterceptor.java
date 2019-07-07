package ru.alexdern.liferay.security.api;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TokenInterceptor implements Interceptor {

    private String token;

    TokenInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request newRequest = originalRequest.newBuilder()
                .header("X-API-KEY", token)
                .build();
        return chain.proceed(newRequest);
    }

}
