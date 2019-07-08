package ru.alexdern.liferay.security.portlet;

import javax.portlet.*;


import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import ru.alexdern.liferay.security.api.dto.UserDto;
import ru.alexdern.liferay.security.model.Search;
import ru.alexdern.liferay.security.model.User;

import ru.alexdern.liferay.security.service.AuditService;
import ru.alexdern.liferay.security.service.UserService;
import ru.alexdern.liferay.security.util.LiferayUtil;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author AlexDern
 */
@Controller("search")
@RequestMapping("EDIT")
@SessionAttributes("search")
public class AuditJournalSpringPortletViewController {

	public static final String DEFAULT_VIEW = "view";
	public static final String ALTERNATIVE_VIEW = "alternativeView";

	private static final Log LOGGER = LogFactoryUtil.getLog(AuditJournalSpringPortletViewController.class);

	/*
    @Autowired
    private LocalValidatorFactoryBean localValidatorFactoryBean;
    */

	@Autowired
	private AuditService auditService;

	@Autowired
	private UserService userService;


    @ModelAttribute("myConfigFieldPortletPreference")
    public String getPortletPreference(PortletRequest portletRequest) {
        PortletPreferences portletPreferences = portletRequest.getPreferences();
        return portletPreferences.getValue("myConfigField", "Not set");
    }

    @ModelAttribute("search")
    public Search populateRecord() {
        return new Search();
    }

    /** default renderer **/
	@RenderMapping
	public String view(Model model, @ModelAttribute("search") Search search, RenderRequest request, RenderResponse response) {

        PortletPreferences portletPreferences = request.getPreferences();


		System.out.println("Default View");
		search.setQuery("TEST");
		model.addAttribute("search", search);

        // Current user
        User user = LiferayUtil.getCurrentUser(request);
        model.addAttribute("user", user);

        // ReleaseInfo
        model.addAttribute("releaseInfo", ReleaseInfo.getReleaseInfo());


        try {

            Map<Long,String> userList = userService.getAll()
                    .stream()
                    .collect(Collectors.toMap(UserDto::getUserID,UserDto::getLogin));
            model.addAttribute("userList", userList);

            model.addAttribute("journal", auditService.getAll());

        } catch (IOException e) {
            LOGGER.warn("API request failed.", e);
        }

		return DEFAULT_VIEW;
	}

    @ActionMapping(params = "action=actionSearch")
    public void actionSearch(Model model, @ModelAttribute("search") Search search, BindingResult bindingResult,
                             ActionRequest request,
                             ActionResponse response,
                             SessionStatus sessionStatus) {
        //localValidatorFactoryBean.validate(search, bindingResult);
        System.out.println("Action search; hasErrors: " + bindingResult.hasErrors());

        System.out.println(search);
        model.addAttribute("search", search);
        response.setRenderParameter("action", "view");
        sessionStatus.setComplete();
    }


    @RenderMapping(params = "render=alternative-view")
	public String alternativeView() {
		System.out.println("Alternative view");
		return ALTERNATIVE_VIEW;
	}

    @RenderMapping(params = "action=add")
    public ModelAndView addView(RenderRequest req, RenderResponse res) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add");
        return modelAndView;
    }

    @RenderMapping(params = "action=form")
    public ModelAndView formView(RenderRequest req, RenderResponse res) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("form");
        return modelAndView;
    }

    @ActionMapping
    public void createEmp(ActionRequest req, ActionResponse res) {
        String name = req.getParameter("name");
        System.out.println("--- name -----------> " + name);
    }

	@ActionMapping(params = "action=action-one")
	public void actionOne() {
		System.out.println("Action one");
	}

	@ActionMapping(params = "action=action-two")
	public void actionTwo(ActionResponse actionResponse) {
		System.out.println("Action two");
		actionResponse.setRenderParameter("render", "alternative-view");
	}


}