<%@ page import="ru.alexdern.liferay.security.model.Search" %>
<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="auditjournal.caption"/></b>
</p>

<%

    Search search = new Search();
%>
<portlet:actionURL name="searchAction" var="searchUrl" />
<aui:form action="<%=searchUrl%>" name="fm" method="POST">

    <aui:model-context bean="<%=search %>" model="<%=Search.class%>" />

    <h2>Filter</h2>
    <div class="form-row">
        <div class="form-group col-md-2">
            <aui:input label="DateStart" name="dtStart" type="date" cssClass="form-control form-control-sm" />
        </div>
        <div class="form-group col-md-2">
            <aui:input label="DateEnd" name="dtEnd" type="date" cssClass="form-control form-control-sm" />
        </div>
        <div class="form-group col-md-2">
            <aui:input label="login" name="login" type="text" cssClass="form-control form-control-sm" />
        </div>
        <div class="form-group col-md-2">
            <aui:select label="event" name="eventType" cssClass="form-control form-control-sm">
                <aui:option label="LOGIN" selected="" useModelValue="<%= false %>" />
                <aui:option label="LOGOUT" selected="" useModelValue="<%= false %>" />
            </aui:select>
        </div>
    </div>
    <aui:button type="submit" value="search" cssClass="btn btn-sm btn-primary" />

</aui:form>

