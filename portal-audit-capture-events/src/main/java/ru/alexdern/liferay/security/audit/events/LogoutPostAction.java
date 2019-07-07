package ru.alexdern.liferay.security.audit.events;

import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouter;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.security.audit.event.generators.constants.EventTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import ru.alexdern.liferay.security.audit.events.support.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component(
        immediate = true, property = {"key=logout.events.post"},
        service = LifecycleAction.class
)
public class LogoutPostAction extends Action {

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            AuditMessage auditMessage = _message.build(EventTypes.LOGOUT, null, request);
            _auditRouter.route(auditMessage);
        }
        catch (Exception e) {
            throw new ActionException(e);
        }
    }


    @Reference
    private AuditRouter _auditRouter;

    @Reference
    private Message _message;

}
