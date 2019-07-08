package ru.alexdern.liferay.security.audit.transport;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.audit.AuditException;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.audit.AuditMessageProcessor;
import org.osgi.service.component.annotations.*;
import ru.alexdern.liferay.security.audit.transport.configuration.RestConfiguration;
import ru.alexdern.liferay.security.audit.transport.support.AuditEventDto;
import ru.alexdern.liferay.security.audit.transport.support.HttpClient;

import java.io.IOException;
import java.util.Map;

@Component(
        configurationPid = "ru.alexdern.liferay.security.audit.transport.configuration.RestConfiguration",
        immediate = true,
        property = "eventTypes=*",
        service = AuditMessageProcessor.class
)
public class RestAuditMessageProcessor implements AuditMessageProcessor {

    private static final Log _log = LogFactoryUtil.getLog(RestAuditMessageProcessor.class);

    private volatile RestConfiguration _configuration;

    private final HttpClient http = new HttpClient();


    /*
    @Reference
    private RestClient rest;
    */


    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        _configuration = ConfigurableUtil.createConfigurable(RestConfiguration.class, properties);
        if (_log.isInfoEnabled()) {
            _log.info("## REST Transport ## -> Audit Message Processor enabled: " + _configuration.enabled());
        }
        http.setBaseUrl(_configuration.apiUrl());
        http.setToken(_configuration.apiKey());
    }


    @Deactivate
    public void deactivate() throws Exception {
        _log.info("## REST Transport Shutdown ##");
        http.close();
    }


    @Override
    public void process(AuditMessage auditMessage) throws AuditException {
        try {
            doProcess(auditMessage);
        }
        catch (Exception e) {
            _log.fatal("Unable to process audit message " + auditMessage, e);
        }

    }

    protected void doProcess(AuditMessage auditMessage) {
        try {
            AuditEventDto auditEventDto = AuditEventDto.fromAuditMessage(auditMessage);
            _log.info("Try send audit message to remote collector.");
            String status = http.post("/api/audit/collector", auditEventDto.toJson());
            _log.info("Send payload completed. Result: " + status);
        } catch (IOException e) {
            if (_log.isWarnEnabled()) {
                _log.warn("Send payload failed. -> ", e);
            }
        }
    }

}
