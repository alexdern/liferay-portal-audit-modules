<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="auditjournal.caption"/></b>
</p>

<portlet:actionURL var="searchURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
	<portlet:param name="type" value="search" />
</portlet:actionURL>

<aui:form action="${searchURL}" method="POST">

	<div class="row">
		<div class="col-md-8">
			<aui:input inlineLabel="left" label="" name="keywords"
					   placeholder="search-entries" size="256" />
		</div>

		<div class="col-md-4">
			<aui:button type="submit" value="search" />
		</div>
	</div>
</aui:form>


<%
    List<Student> students = StudentLocalServiceUtil.getStudents();
%>

<liferay-ui:search-container total="<%=students.size()%>">

    <liferay-ui:search-container-results results="${students}" />

    <liferay-ui:search-container-row className="kwan.org.database.model.Student" modelVar="student">

        <liferay-ui:search-container-column-text property="code" />
        <liferay-ui:search-container-column-text property="fullName" />
        <liferay-ui:search-container-column-date property="birthday" />
        <liferay-ui:search-container-column-text property="address" />

    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />

</liferay-ui:search-container>