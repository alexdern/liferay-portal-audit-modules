package ru.alexdern.liferay.security.audit.transport;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.audit.AuditException;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.audit.AuditMessageProcessor;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import ru.alexdern.liferay.security.audit.transport.configuration.RestConfiguration;

import java.util.Map;

@Component(
        configurationPid = "ru.alexdern.liferay.security.audit.transport.configuration.RestConfiguration",
        immediate = true,
        property = "eventTypes=*",
        service = AuditMessageProcessor.class
)
public class DummyAuditMessageProcessor implements AuditMessageProcessor {

    private static final Log _log = LogFactoryUtil.getLog(DummyAuditMessageProcessor.class);

    private volatile RestConfiguration _configuration;


    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        _configuration = ConfigurableUtil.createConfigurable(RestConfiguration.class, properties);
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
        if (_configuration.enabled()) {
            if (_log.isInfoEnabled()) {
                _log.info("Dummy processor processing this Audit Message => " + auditMessage.toJSONObject());
            }
        }
    }

}
