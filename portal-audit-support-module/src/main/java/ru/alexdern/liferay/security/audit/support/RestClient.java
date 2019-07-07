package ru.alexdern.liferay.security.audit.support;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import java.util.Map;


@Component(service = RestClient.class, immediate = true)
public class RestClient {

    private static final Log _log = LogFactoryUtil.getLog(RestClient.class);

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        if (_log.isInfoEnabled()) {
            _log.info("## RestClient activate ##");
        }

    }

    public void test() {
        if (_log.isInfoEnabled()) {
            _log.info("## RestClient ## -> Hello!!!");
        }
    }

}
