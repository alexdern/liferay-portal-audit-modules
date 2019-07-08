package ru.alexdern.liferay.security.portlet;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import javax.portlet.*;
import java.io.IOException;

public class AuditJournalSpringPortletConfigurationController extends DefaultConfigurationAction {

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {

        PortletPreferences portletPreferences = renderRequest.getPreferences();

        // Get the actual configuration
        String myConfigField = portletPreferences.getValue("myConfigField", "");

        // Set variables into the request
        renderRequest.setAttribute("myConfigField", myConfigField);

        super.render(renderRequest, renderResponse);
    }

    @Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        super.processAction(portletConfig, actionRequest, actionResponse);
    }

}
