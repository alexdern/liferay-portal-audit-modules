package ru.alexdern.liferay.security.api.rs;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.alexdern.liferay.security.api.dto.EventDto;


import java.util.List;

public interface AuditResource {

    @GET("audit/journal")
    public Call<List<EventDto>> getAll();

}
