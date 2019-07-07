package ru.alexdern.liferay.security.audit.transport.support;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.osgi.service.component.annotations.Component;
import ru.alexdern.liferay.security.audit.transport.RestAuditMessageProcessor;

@Component(service = SendCommand.class, immediate = true)
public class SendCommand {

    private static final Log _log = LogFactoryUtil.getLog(SendCommand.class);

    public void send(String message) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        //HttpPost httpPost = new HttpPost(POST_URL);
        //httpPost.addHeader("User-Agent", USER_AGENT);


        _log.info("-- SAY == ".concat(message));
    }

}
