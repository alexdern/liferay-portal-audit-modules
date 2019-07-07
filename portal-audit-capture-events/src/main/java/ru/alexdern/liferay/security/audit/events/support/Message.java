package ru.alexdern.liferay.security.audit.events.support;

import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Portal;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;

@Component(service = Message.class, immediate = true)
public class Message {

    @Reference
    private Portal _portal;


    public AuditMessage build(String eventType, String message, HttpServletRequest request)
            throws PortalException {

        User user = _portal.getUser(request);
        if (user == null) {
            throw new ActionException("User undefined.");
        }

        JSONObject additionalInfo = AdditionalInfoBuilder.create(request);
        additionalInfo.put("login", user.getLogin());
        additionalInfo.put("user_tz", user.getTimeZone().getID());
        additionalInfo.put("user_lang", user.getLanguageId());

        AuditMessage auditMessage = new AuditMessage(
                eventType, user.getCompanyId(), user.getUserId(),
                user.getFullName(), User.class.getName(),
                String.valueOf(user.getPrimaryKey()), message, additionalInfo);

        return auditMessage;
    }

}
