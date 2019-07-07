package ru.alexdern.liferay.security.audit.transport.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(category = "audit")
@Meta.OCD(
        id = "ru.alexdern.liferay.security.audit.transport.configuration.RestConfiguration",
        localization = "content/Language",
        name = "%rest-transport-audit-security-configuration-name"
)
public interface RestConfiguration {

    @Meta.AD(
            deflt = "false",
            description = "%audit-message-processor-enabled-help",
            name = "%audit-message-processor-enabled",
            required = false
    )
    public boolean enabled();


    @Meta.AD(
            deflt = "http://localhost:8081/",
            description = "%audit-message-processor-api-url-help",
            name = "%audit-message-processor-api-url",
            required = false
    )
    public String apiUrl();


    @Meta.AD(
            deflt = "TESTING-KEY-DB194F1DA8D55F8E147D79CF7EEDC25F",
            description = "%audit-message-processor-api-key-help",
            name = "%audit-message-processor-api-key",
            required = false
    )
    public String apiKey();


}
