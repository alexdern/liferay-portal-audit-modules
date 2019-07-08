package ru.alexdern.liferay.security.config;

import aQute.bnd.annotation.metatype.Configurable;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Component(
        configurationPid = "ru.alexdern.liferay.security.config.AuditConfiguration",
        configurationPolicy = ConfigurationPolicy.OPTIONAL,
        immediate = true,
        property = {"javax.portlet.name=ru_alexdern_liferay_security_AuditJournalPortlet"},
        service = ConfigurationAction.class
)
public class AuditConfigurationAction extends DefaultConfigurationAction {


    private static final Log _log = LogFactoryUtil.getLog(AuditConfigurationAction.class);

    @Override
    public void processAction(
            PortletConfig portletConfig, ActionRequest actionRequest,
            ActionResponse actionResponse)
            throws Exception {
        _log.info("##### Calling processAction() method in configuration ######");
        String transport = ParamUtil.getString(actionRequest, "transport");
        String numberOfRecordsDisplayed = ParamUtil.getString(actionRequest, "numberOfRecordsDisplayed");
        String sortOrder = ParamUtil.getString(actionRequest, "sortOrder");
        String unit = ParamUtil.getString(actionRequest, "unit");
        setPreference(actionRequest, "transport", transport);
        setPreference(actionRequest, "numberOfRecordsDisplayed", numberOfRecordsDisplayed);
        setPreference(actionRequest, "sortOrder", sortOrder);
        setPreference(actionRequest, "unit", unit);
        super.processAction(portletConfig, actionRequest, actionResponse);
    }


    @Override
    public void include(
            PortletConfig portletConfig, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {
        httpServletRequest.setAttribute(AuditConfiguration.class.getName(), _auditConfig);
        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _log.info("##### Calling activate() method in configuration ######");
        _auditConfig = Configurable.createConfigurable(AuditConfiguration.class, properties);
    }

    private volatile AuditConfiguration _auditConfig;

}
