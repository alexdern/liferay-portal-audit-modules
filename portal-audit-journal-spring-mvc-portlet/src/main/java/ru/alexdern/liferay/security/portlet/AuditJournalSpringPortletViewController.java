package ru.alexdern.liferay.security.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;


import com.liferay.portal.kernel.util.ReleaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import retrofit2.Response;
import ru.alexdern.liferay.security.api.dto.EventDto;
import ru.alexdern.liferay.security.model.SearchForm;
import ru.alexdern.liferay.security.model.User;
import ru.alexdern.liferay.security.api.RestClient;
import ru.alexdern.liferay.security.util.LiferayUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AlexDern
 */
@Controller
@RequestMapping("VIEW")
public class AuditJournalSpringPortletViewController {

	public static final String DEFAULT_VIEW = "view";
	public static final String ALTERNATIVE_VIEW = "alternativeView";


	@Autowired
	private RestClient rest;


	@RenderMapping
	public ModelAndView view(RenderRequest request, RenderResponse response) {
		System.out.println("Default View");
        ModelAndView model = new ModelAndView(DEFAULT_VIEW);
        model.addObject("releaseInfo", ReleaseInfo.getReleaseInfo());
        model.addObject("searchFormModel", new SearchForm());

        Map<String, String> userList = new HashMap<>();
        userList.put("A", "alex");
        userList.put("D", "denis");
        userList.put("P", "pavel");
        model.addObject("userList", userList);



        List<EventDto> journal;
        try {
            Response<List<EventDto>> rs = rest.rsAudit().getAll().execute();
            System.out.println("RS = " + rs.toString());
            journal = rs.body();
            System.out.println(journal);
            model.addObject("journal", journal.subList(0, 5));
        } catch (IOException e) {
            e.printStackTrace();
        }


        User user = LiferayUtil.getCurrentUser(request);
		model.addObject("user", user);
		return model;
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
        System.out.println("name----------- " + name);
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