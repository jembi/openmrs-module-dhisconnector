<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>

<h3><spring:message code="dhisconnector.runReports"/></h3>

<form method="POST">
  <table>
    <tbody>
    <tr>
      <td><spring:message code="dhisconnector.report"/></td>
      <td>dropdown of reports></td>
    </tr>
    <tr>
      <td><spring:message code="dhisconnector.location"/></td>
      <td>location selector</td>
    </tr>
    <tr>
      <td><spring:message code="dhisconnector.date"/></td>
      <td>date selector</td>
    </tr>
    <tr>
      <td><spring:message code="dhisconnector.action"/></td>
      <td>download or post</td>
    </tr>
    <tr>
      <td/>
      <td><input name="submit" type="submit" value="<spring:message code="dhisconnector.run" />"/></td>
    </tr>
    </tbody>
  </table>
</form>

<%@ include file="/WEB-INF/template/footer.jsp" %>
