package ru.alexdern.liferay.security.portlet;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import ru.alexdern.liferay.security.config.AuditConfiguration;
import ru.alexdern.liferay.security.constants.AuditJournalPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;
import ru.alexdern.liferay.security.model.Search;

import java.io.IOException;
import java.util.Map;

/**
 * @author AlexDern
 */
@Component(
	immediate = true,
    configurationPid = "ru.alexdern.liferay.service.config.AuditConfiguration",
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AuditJournal",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AuditJournalPortletKeys.AUDITJOURNAL,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AuditJournalPortlet extends MVCPortlet {

	private static final Log _log = LogFactoryUtil.getLog(AuditJournalPortlet.class);

	private volatile AuditConfiguration _configuration;


	@ProcessAction(name="searchAction")
	public void search(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException {
		_log.info("##### Calling addPage() method in portlet ######");
		String pageEditor = actionRequest.getParameter("pageEditor");
		_log.info("V -> " + pageEditor);
	}

	@ProcessAction(name="addContentPage")
	public void addPage(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException {
		_log.info("##### Calling addPage() method in portlet ######");
		String pageEditor = actionRequest.getParameter("pageEditor");
		_log.info("V -> " + pageEditor);
	}


	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		renderRequest.setAttribute(AuditConfiguration.class.getName(), _configuration);
		renderRequest.setAttribute("search", new Search());
		super.doView(renderRequest, renderResponse);
	}

	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		_log.info("##### Calling activate() method in portlet ######");
		_configuration = ConfigurableUtil.createConfigurable(AuditConfiguration.class, properties);
	}

	@Deactivate
	public void deactivate() throws Exception {
		_log.info("##### Shutdown portlet ######");
	}

}
