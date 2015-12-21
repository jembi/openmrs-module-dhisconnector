<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<openmrs:htmlInclude file="/moduleResources/dhisconnector/dhisconnector.css"/>
<openmrs:htmlInclude file="/moduleResources/dhisconnector/dhisconnector-runreports.js"/>
<openmrs:htmlInclude file="/moduleResources/dhisconnector/jquery.monthpicker.js"/>

<%@ include file="template/localHeader.jsp" %>

<h3><spring:message code="dhisconnector.runReports"/></h3>

<form method="POST">
  <table>
    <tbody>
    <tr>
      <th class="runHeader"><spring:message code="dhisconnector.report"/></th>
      <td>
        <span id="reportsSelectContainer"><img class="spinner" src="/openmrs/moduleResources/dhisconnector/loading.gif"/></span>
      </td>
    </tr>
    <tr>
      <th class="runHeader"><spring:message code="dhisconnector.mapping"/></th>
      <td>
        <span id="mappingSelectContainer"><img class="spinner" src="/openmrs/moduleResources/dhisconnector/loading.gif"/></span>
      </td>
    </tr>
    <tr>
      <th class="runHeader"><spring:message code="dhisconnector.location"/></th>
      <td>
        <table>
          <thead>
            <tr>
              <th class="runHeader">OpenMRS Location</th>
              <th class="runHeader">DHIS Organisation Unit</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><span id="locationSelectContainer"><img class="spinner" src="/openmrs/moduleResources/dhisconnector/loading.gif"/></span></td>
              <td><span id="orgUnitSelectContainer"><img class="spinner" src="/openmrs/moduleResources/dhisconnector/loading.gif"/></span></td>
            </tr>
          </tbody>
        </table>
      </td>
    </tr>
    <tr>
      <th class="runHeader"><spring:message code="dhisconnector.date"/></th>
      <td><input type="text" name="date" id="periodSelector" class="periodSelector"/></td>
    </tr>
    <tr>
      <th class="runHeader"><spring:message code="dhisconnector.action"/></th>
      <td><input name="submit" type="button" onClick="sendDataToDHIS()" value="<spring:message code="dhisconnector.post" />"/> <input name="submit" type="button" value="<spring:message code="dhisconnector.download" />"/></td>
    </tr>
    </tbody>
  </table>
</form>

<%@ include file="/WEB-INF/template/footer.jsp" %>
