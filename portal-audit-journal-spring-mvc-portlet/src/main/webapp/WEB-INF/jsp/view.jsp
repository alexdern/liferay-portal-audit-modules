<%@ include file="/WEB-INF/jsp/init.jsp" %>


<portlet:actionURL var="searchURL">
    <portlet:param name="mvcPath" value="/view.jsp" />
    <portlet:param name="type" value="search" />
</portlet:actionURL>

<portlet:renderURL var="addUrl">
    <portlet:param name="action" value="add"/>
</portlet:renderURL>

<portlet:renderURL var="formUrl">
    <portlet:param name="action" value="form"/>
</portlet:renderURL>

<pre>
<c:out escapeXml="true" value="${releaseInfo}" />.
</pre>

<p>
	<b><liferay-ui:message key="auditjournalspring.caption" /></b> :: <a href="${addUrl}">Add</a> :: <a href="${formUrl}">Form</a>
</p>

<h1><liferay-ui:message key="liferay7springmvcportlet.sayHello" arguments="${user.firstName}" /></h1>
<br />


<div class="container">

    <%@ include file="filter.jsp" %>
    <%@ include file="resultset.jsp" %>

</div>