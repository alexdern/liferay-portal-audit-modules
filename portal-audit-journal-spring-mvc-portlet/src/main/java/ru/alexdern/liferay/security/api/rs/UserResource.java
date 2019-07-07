package ru.alexdern.liferay.security.api.rs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.alexdern.liferay.security.api.dto.UserDto;

import java.util.List;

public interface UserResource {

    @GET("users")
    public Call<List<UserDto>> getAll();

    @GET("users")
    public Call<List<UserDto>> getAll(@Query("per_page") int per_page, @Query("page") int page);

    @GET("users/by-login/{login}")
    public Call<UserDto> getByLogin(@Path("login") String login);

}
