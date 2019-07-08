
<portlet:actionURL name="addContentPage" var="addContentPageUrl" />
<aui:form action="${addContentPageUrl}" name="pageform">
    <h3>Enter content here</h3>
    <liferay-ui:input-editor name="pageEditor" placeholder="Enter content here"></liferay-ui:input-editor>
    <aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>
    <br/>
</aui:form>


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

