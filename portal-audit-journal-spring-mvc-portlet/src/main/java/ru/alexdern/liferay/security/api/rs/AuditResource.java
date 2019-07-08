package ru.alexdern.liferay.security.api.rs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.alexdern.liferay.security.api.dto.EventDto;


import java.util.List;

public interface AuditResource {

    @GET("audit/journal")
    public Call<List<EventDto>> getAll();

    @GET("audit/journal/filter")
    public Call<List<EventDto>> filter(@Query("u") Long userId,
                                       @Query("q") String query,
                                       @Query("t") String eventType,
                                       @Query("dt_start") String dtStart,
                                       @Query("dt_end") String dtEnd);


}
