<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude
	file="/moduleResources/dhisconnector/dhisconnector.css" />
<openmrs:htmlInclude
	file="/moduleResources/dhisconnector/dragula.min.js" />
<openmrs:htmlInclude
	file="/moduleResources/dhisconnector/dhisconnector.js" />

<%@ include file="template/localHeader.jsp"%>

<c:if test="${showLogin == 'true'}">
	<c:redirect url="../../login.htm" />
</c:if>

<h3>
	<spring:message code="dhisconnector.fullMapping" />
</h3>
<br />
<spring:message code="dhisconnector.uploadMapping.message" />
<br />
<br />
<div class="error-encountered">${failureWhileUploading}</div>
<br />
<div class="success-encountered">${successWhileUploading}</div>
<br />
<form method="post" onsubmit="getFullMappingValues()">
	<input type="hidden" name="fullMappingValues" value="1=2">
	<table>
		<thead>
			<tr>

				<th>OpenMRS Location</th>
				<th>DHIS2 Organization Unit</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${locations}" var="location">
				<tr class="evenRow">

					<td><select name="location_${location.uuid}">
							<option value="${location.uuid}">${location.name}</option>
					</select></td>
					<td><select name="orgUnit_${location.uuid}">
							<option></option>

							<c:forEach items="${orgUnits}" var="orgUnit">

								<c:forEach items="${fullMappingsMap}" var="map">
									<c:if test="${map.key == location.uuid}">
										<c:if test="${map.value == orgUnit.id}">

											<c:set value="selected=\"selected\"" var="isSelected"></c:set>
										</c:if>
									</c:if>

								</c:forEach>

								<option ${isSelected} value="${orgUnit.id}">${orgUnit.name}</option>
								<c:set value="" var="isSelected"></c:set>
							</c:forEach>
					</select></td>

				</tr>
			</c:forEach>
			<!--  
                <tr class="evenRow">
                    <td><input type="checkbox" name="mappingIds" value="${mpg.id}"/></td>
                    <td><input type="checkbox" name="runs" value="${mpg.uuid}"/></td>
                    <td>${fn:substringBefore(mpg.mapping, '.')}</td>
                    <td>${mpg.location.name}</td>
                    <td>${orgUnitsByIds[mpg.orgUnitUid]}</td>
                    <td><c:if test="${not empty mpg.lastRun}"><input type="checkbox" name="reRuns" value="${mpg.uuid}"/></c:if> ${mpg.lastRun}</td>
                </tr>-->

		</tbody>
	</table>
	<input type="submit" value="Submit">
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>
<%@ include file="jembiOpenMRSFooter.jsp"%>