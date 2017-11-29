<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>

<c:if test="${showLogin == 'true'}">
	<c:redirect url="../../login.htm" />
</c:if>

<h3>Setting up OpenMRS reports to automatically report into the configured DHIS2 instance</h3>

<form method="post">
	<br />
		<input type="checkbox" name="toogleAutomation" <c:if test="${automationEnabled}">checked="checked"</c:if>>Enable/Disable Automation</input>
	<br />
    <table>
        <thead>
            <tr>
            	<th>Delete</th>
            	<th>Run</th>
                <th>Mapping</th>
                <th>OpenMRS Location</th>
                <th>DHIS2 Organization Unit</th>
                <th>Last Run</th>
            </tr>
        </thead>
        <tbody>
            <tr class="evenRow">
                <td>Add</td>
                <td>New</td>
                <td>
                    <select name="mapping">
                        <option></option>
                        <c:forEach items="${mappings}" var="mapping">
                            <option value="${mapping.name}.${mapping.created}">${mapping.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="location">
                        <option></option>
                        <c:forEach items="${locations}" var="location">
                            <option value="${location.uuid}">${location.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="orgUnit">
                        <option></option>
                        <c:forEach items="${orgUnits}" var="orgUnit">
                            <option value="${orgUnit.id}">${orgUnit.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td></td>
            </tr>
            <c:forEach items="${reportToDataSetMappings}" var="mpg">
                <tr class="evenRow">
                    <td><input type="checkbox" name="mappingIds" value="${mpg.id}"/></td>
                    <td><input type="checkbox" name="mappingUuids" value="${mpg.uuid}"/></td>
                    <td>${fn:substringBefore(mpg.mapping, '.')}</td>
                    <td>${mpg.location.name}</td>
                    <td>${orgUnitsByIds[mpg.orgUnitUid]}</td>
                    <td>${mpg.lastRun}</td>
                </tr>
           </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="Submit">
</form>

<c:forEach items="${postResponse}" var="resp">
	<div style="background-color: lightyellow;border: 1px dashed lightgrey;">${resp}</div><br />
</c:forEach>

<%@ include file="/WEB-INF/template/footer.jsp"%>