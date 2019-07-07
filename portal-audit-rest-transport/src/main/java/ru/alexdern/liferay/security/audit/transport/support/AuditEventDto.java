package ru.alexdern.liferay.security.audit.transport.support;

import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.time.ZoneId;

public class AuditEventDto {

    public static AuditEventDto fromAuditMessage(AuditMessage m) {

        AuditEventDto self = new AuditEventDto();
        self.timestamp = m.getTimestamp()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toString();

        self.event_type = m.getEventType();
        self.server_host = m.getServerName();
        self.session_key = m.getSessionID();
        self.client_ip = m.getClientIP();
        self.company_id = String.valueOf(m.getCompanyId());
        self.class_pk = m.getClassPK();
        self.class_name = m.getClassName();
        self.user_id = String.valueOf(m.getUserId());
        self.username = m.getUserName();
        self.message = m.getMessage();

        JSONObject additionalInfo = m.getAdditionalInfo();
        if (additionalInfo != null) {
            if (additionalInfo.has("login")) {
                self.login = additionalInfo.getString("login");
            }
            if (additionalInfo.has("user_tz")) {
                self.user_timezone = additionalInfo.getString("user_tz");
            }
            if (additionalInfo.has("user_lang")) {
                self.user_language = additionalInfo.getString("user_lang");
            }
            if (additionalInfo.has("user-agent")) {
                self.client_agent = additionalInfo.getString("user-agent");
            }
        }

        return self;
    }


    /**
     * Event Data
     */
    public String timestamp;
    public String event_type;
    public String server_host;
    public String session_key;
    public String client_ip;
    public String client_agent;
    public String class_pk;
    public String class_name;
    public String message;

    /**
     * User Data
     */
    public String company_id;
    public String user_id;
    public String user_timezone;
    public String user_language;
    public String username;
    public String login;

    public String toJson() {
        return JSONFactoryUtil.looseSerialize(this);
    }

}
