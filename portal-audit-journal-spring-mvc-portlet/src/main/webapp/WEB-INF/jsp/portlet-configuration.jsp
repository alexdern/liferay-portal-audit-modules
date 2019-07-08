
<%@ include file="/init.jsp"%>

<%-- Create a specific URL to go to the configuration controller : SamplePortletConfigurationController.java --%>
<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

    <%--
    	Because the controller SamplePortletConfigurationController extends
    	the class DefaultConfigurationAction, form fields naming is particular.
    	It has to start with "preferences--" and end with "--".

    	Thus the update of the portlet preferences will be handled by the method
    	DefaultConfigurationAction#processAction
     --%>
    <aui:input type="text" name="preferences--myConfigField--" value="${myConfigField}" />

    <aui:button-row>
        <aui:button type="submit" />
    </aui:button-row>
</aui:form>