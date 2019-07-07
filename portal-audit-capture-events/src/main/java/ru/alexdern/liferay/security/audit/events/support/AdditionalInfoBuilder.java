package ru.alexdern.liferay.security.audit.events.support;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


public class AdditionalInfoBuilder {

    public static JSONObject create(HttpServletRequest request) {
        JSONObject additionalInfo = JSONFactoryUtil.createJSONObject();
        Enumeration<String> e = request.getHeaderNames();
        String name, value;
        while(e.hasMoreElements()) {
            name = e.nextElement();
            value = request.getHeader(name);
            additionalInfo.put(name, value);
        }
        return additionalInfo;
    }

}
