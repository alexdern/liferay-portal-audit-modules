<%@ include file="/init.jsp" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="ru.alexdern.liferay.security.config.AuditConfiguration" %>

<%
    AuditConfiguration auditConfig = (AuditConfiguration)renderRequest.getAttribute(AuditConfiguration.class.getName());

    String transport = StringPool.BLANK;
    String unit = StringPool.BLANK;
    String numberOfRecordsDisplayed = StringPool.BLANK;
    String sortOrder = StringPool.BLANK;

    if (Validator.isNotNull(auditConfig)) {
        transport = portletPreferences.getValue("city", auditConfig.transport());
        unit = portletPreferences.getValue("unit", auditConfig.unit());
        numberOfRecordsDisplayed = portletPreferences.getValue("numberOfRecordsDisplayed", auditConfig.numberOfRecordsDisplayed());
        sortOrder = portletPreferences.getValue("sortOrder", auditConfig.sortOrder());
    }

%>

<liferay-portlet:actionURL portletConfiguration="<%=true%>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%=true%>" var="configurationRenderURL" />


<aui:form action="<%=configurationActionURL%>" method="post" name="fm">

    <div class="portlet-configuration-body-content">
        <div class="container-fluid-1280">

            <aui:fieldset-group markupView="lexicon">
                <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
                <aui:input name="redirect" type="hidden" value="<%=configurationRenderURL%>" />

                <aui:fieldset>
                    <aui:select name="transport" label="Transport" value="<%=transport%>">
                        <aui:option value="HttpClient">Apache HttpClient</aui:option>
                        <aui:option value="Retrofit">Retrofit</aui:option>
                    </aui:select>
                    <aui:select label="Unit" name="unit" value="<%=unit%>">
                        <aui:option value="A">A</aui:option>
                        <aui:option value="B">B</aui:option>
                    </aui:select>
                    <aui:input name="numberOfRecordsDisplayed" label="Number of records to display" value="<%=numberOfRecordsDisplayed%>">
                    </aui:input>
                    <aui:input name="sortOrder" label="Sort asc or desc" value="<%=sortOrder%>">
                    </aui:input>
                </aui:fieldset>

            </aui:fieldset-group>

        </div>
    </div>
    <aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>

</aui:form>

