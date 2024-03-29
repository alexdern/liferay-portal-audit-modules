<%@ include file="/WEB-INF/jsp/init.jsp" %>

<portlet:actionURL var="createappntURL">
    <portlet:param name="execution" value="save" />
</portlet:actionURL>

<form:form action="${createappntURL}" method="POST" cssClass="form-horizontal">

    <div class="control-group">
        <label class="control-label" for="empName">Emp Name</label>
        <div class="controls">
            <form:input type="text" id="empName" path="empName" />
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="childName">Child Name</label>
        <div class="controls">
            <form:input type="text" id="childName" path="childName" />
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="hospName">Hospital Name</label>
        <div class="controls">
            <form:input type="text" id="hospName" path="hospName" />
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="date">Date</label>
        <div class="controls">
            <form:input type="text" id="date" path="date" />
        </div>
    </div>

    <div class="control-group">
        <div class="controls">
            <c:choose>
                <c:when test="${createappntURL == 0}">
                    <input id="validateNewEmployeeButton" class="btn btn-primary" type="submit" value="Create"/>
                </c:when>
                <c:otherwise>
                    <input id="validateUpdateContactButton" class="btn btn-primary" type="submit" value="Update"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</form:form>


<portlet:renderURL var="returnUrl"></portlet:renderURL>
<a href="${returnUrl}">return</a>

