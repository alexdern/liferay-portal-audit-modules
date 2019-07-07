
<hr />

<div class="row">
    <label class="col-sm-3">Event</label>
    <label class="col-sm-2">User</label>
    <label class="col-sm-1">Client IP</label>
    <label class="col-sm-6">Client Agent</label>
</div>

<div>
    <c:forEach items="${journal}" var="item">
        <c:set var="userid" scope="session" value="${item.userID}"/>
        <div class="com-sm-12 row border-bottom">
            <label class="col-sm-1">
                    <span class="workflow-status">
                        <strong class="label status text-uppercase approved">
                                ${item.eventType}
                        </strong>
                    </span>
            </label>
            <label class="col-sm-2">${item.formatedDateTime}</label>
            <label class="col-sm-2">${item.username}<br />(${userid}/${item.login})</label>
            <label class="col-sm-1">${item.clientIp}</label>
            <label class="col-sm-6">${item.clientAgent}</label>
        </div>
    </c:forEach>
</div>
