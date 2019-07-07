package ru.alexdern.liferay.security.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import org.springframework.stereotype.Service;
import ru.alexdern.liferay.security.api.rs.AuditResource;
import ru.alexdern.liferay.security.api.rs.UserResource;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;


@Service
public class RestClient {

    private static final String AUTHORIZATION_TOKEN = "TESTING-KEY-DB194F1DA8D55F8E147D79CF7EEDC25F";

    private Retrofit retrofit;


    @PostConstruct
    private void init() {
        retrofit = provideRetrofit("http://10.10.0.10:8080/api/");
    }

    private Retrofit provideRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(provideOkHttpClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.addInterceptor(new TokenInterceptor(AUTHORIZATION_TOKEN));
        return builder.build();
    }

    public UserResource rsUsers() {
        return retrofit.create(UserResource.class);
    }

    public AuditResource rsAudit() {
        return retrofit.create(AuditResource.class);
    }

}
