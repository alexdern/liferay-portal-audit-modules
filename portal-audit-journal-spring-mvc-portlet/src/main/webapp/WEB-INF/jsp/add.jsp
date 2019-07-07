<%@ include file="/WEB-INF/jsp/init.jsp" %>

<portlet:actionURL var="saveUrl" name="createEmp" escapeXml="false"></portlet:actionURL>

<form:form action="${saveUrl}" method="POST" cssClass="form-horizontal">
    <div class="control-group">
        <label class="control-label">name</label>
        <div class="controls">
            <input type="text" name="name" value="${name}"/>
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <input class="btn btn-primary" type="submit" value="Create"/>
        </div>
    </div>
</form:form>

<portlet:renderURL var="returnUrl"></portlet:renderURL>
<a href="${returnUrl}">return</a>
