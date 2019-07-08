<%@ include file="/WEB-INF/jsp/init.jsp" %>

<portlet:renderURL var="addUrl">
    <portlet:param name="action" value="add"/>
</portlet:renderURL>

<portlet:renderURL var="formUrl">
    <portlet:param name="action" value="form"/>
</portlet:renderURL>

<p>
	<b><liferay-ui:message key="auditjournalspring.caption" /></b> :: <a href="${addUrl}">Add</a>
    <%-- :: <a href="${formUrl}">Form</a> --%>
</p>


<br />


<div class="container">


    <%-- ACTION URL that points to method SpringPortletMvcController#doActionSample --%>
    <portlet:actionURL var="searchURL">
        <portlet:param name="action" value="actionSearch"/>
    </portlet:actionURL>

    <form:form action="<%=searchURL %>" method="post" modelAttribute="search">

        <h2>Filter</h2>
        <div class="form-row">
            <div class="form-group col-md-2">
                <form:label path="startDate">Start Date:</form:label>
                <form:input path="startDate" type="date" cssClass="form-control form-control-sm" />
            </div>
            <div class="form-group col-md-2">
                <form:label path="endDate">End Date:</form:label>
                <form:input path="endDate" type="date" cssClass="form-control form-control-sm" />
            </div>
            <div class="form-group col-md-3">
                <form:label path="userId">Login:</form:label>
                <form:select path="userId" cssClass="form-control form-control-sm">
                    <form:option value="NONE" label="Select"/>
                    <form:options items="${userList}" />
                </form:select>
            </div>
            <div class="form-group col-md-3">
                    <%--
                    Once you set a modelAttribute in the parent form:form, you can access all fields of the bean.
                    Here we are calling the getter SearchForm#getUsername
                    --%>
                <form:label path="query">Search:</form:label>
                <form:input path="query" placeholder="Query" cssClass="form-control form-control-sm" />
            </div>
        </div>
        <input  type="submit" class="btn btn-sm btn-outline-primary" value="Search" />
    </form:form>


    <%@ include file="filter.jsp" %>
    <%@ include file="resultset.jsp" %>

</div>