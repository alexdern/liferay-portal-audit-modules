package ru.alexdern.liferay.security.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import ru.alexdern.liferay.security.api.RestClient;
import ru.alexdern.liferay.security.api.dto.EventDto;

import java.io.IOException;
import java.util.List;

@Service
public class AuditService {

    private static final Log LOGGER = LogFactoryUtil.getLog(AuditService.class);

    @Autowired
    private RestClient rest;


    public List<EventDto> getAll() throws IOException {
        Call<List<EventDto>> call = rest.rsAudit().getAll();
        Response<List<EventDto>> rs = call.execute();
        LOGGER.info("RS -> ".concat(rs.toString()));
        return rs.body();
    }

}
