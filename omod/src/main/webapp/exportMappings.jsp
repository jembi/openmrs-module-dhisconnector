<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<openmrs:htmlInclude file="/moduleResources/dhisconnector/jquery-2.2.0.min.js"/>
<openmrs:htmlInclude file="/moduleResources/dhisconnector/angular.min.js"/>
<openmrs:htmlInclude file="/moduleResources/dhisconnector/dhisconnector.js"/>
<openmrs:htmlInclude file="/moduleResources/dhisconnector/export-mappings.js"/>
<openmrs:htmlInclude file="/moduleResources/dhisconnector/dhisconnector.css"/>

<%@ include file="template/localHeader.jsp" %>

<c:if test="${showLogin == 'true'}">
	<c:redirect url="../../login.htm" />
</c:if>

<h3><spring:message code="dhisconnector.exportMapping"/></h3>
<br /><spring:message code="dhisconnector.exportMapping.message"/><br />
<div ng-app="exportMappingApp" ng-controller="exportMappingController">
	<table border="0" cellspacing="0" cellpadding="0">
		<tbody>
		  <tr>
		    <td><spring:message code="dhisconnector.exportMapping.available"/></td>
		    <td>&nbsp;</td>
		    <td>
		      <table cellspacing="0" cellpadding="0" border="0" style="width: 100%;">
		        <tbody>
		          <tr>
		            <td><spring:message code="dhisconnector.exportMapping.selected"/></td>
		            <td></td>
		          </tr>
		        </tbody>
		      </table>
		    </td>
		  </tr>
		  <tr>&nbsp;</tr>
		  <tr>
		    <td valign="top">
		      <select class="mapping-select" id="left-mapping-select" multiple="multiple" size="25">
		      	<option class="header" disabled value="header"><spring:message code="dhisconnector.exportMapping.selectMappingsHeader"/></option>
		      	<option ng-repeat="mapping in existingMappings track by $index" value="{{mapping.name}}.{{mapping.dateTime}}">{{mapping.name}} :::> {{mapping.created}}</option>
		      </select>
		    </td>
		    <td>
		      <table style="text-align: center;">
		        <tbody>
		          <tr><td><input type="button" ng-click="addAll()" ng-disabled="addAll_Disabled()" title='<spring:message code="dhisconnector.exportMapping.addAll"/>' value=">>" style="width: 50px;"></td></tr>
		          <tr><td><input type="button" ng-click="addSelected()" ng-disabled="addSelected_Disabed()" title='<spring:message code="dhisconnector.exportMapping.addSelected"/>' value=">" style="width: 50px;"></td></tr>
		          <tr><td><input type="button" ng-click="removeSelected()" ng-disabled="removeSelected_Disabed()" title='<spring:message code="dhisconnector.exportMapping.removeSelected"/>' value="<" style="width: 50px;"></td></tr>
		          <tr><td><input type="button" ng-click="removeAll()" ng-disabled="removeAll_Disabled()" title='<spring:message code="dhisconnector.exportMapping.removeAll"/>' value="<<" style="width: 50px;"></td></tr>
		        </tbody>
		      </table>
		    </td>
		    <td valign="top">
		      <select class="mapping-select" name='selectedMappings' id="right-mapping-select" multiple="multiple" size="25">
		        <option class="header" disabled value="header"><spring:message code="dhisconnector.exportMapping.selectMappingsHeader"/></option>
		      </select>
		    </td>
		  </tr>
		</tbody>
	</table>
	<br />
	<div class="error-encountered" id="error-encountered-export">${failureWhileExporting}</div><br />
	<div class="success-encountered" id="success-encountered-export">${successWhileExporting}</div>
</div>

<form method="POST">
	<input type="hidden" value="" name="selectedMappings" id="selected-mappings-to-export">
    <input type="submit" value='<spring:message code="dhisconnector.export"/>'>
</form>

<%@ include file="/WEB-INF/template/footer.jsp" %>
<%@ include file="jembiOpenMRSFooter.jsp" %>