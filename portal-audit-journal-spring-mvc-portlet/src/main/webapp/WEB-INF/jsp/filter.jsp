

<form:form action="${searchURL}" method="POST"  modelAttribute="searchFormModel">

    <h2>Recordset filter</h2>
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
            <form:label path="login">Login:</form:label>
            <form:select path="login" cssClass="form-control form-control-sm">
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
    <button type="submit" class="btn btn-sm btn-outline-primary">Search</button>
</form:form>
