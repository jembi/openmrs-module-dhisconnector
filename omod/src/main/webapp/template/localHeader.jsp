<spring:htmlEscape defaultHtmlEscape="true" />
<ul id="menu">
	<li class="first"><a
		href="${pageContext.request.contextPath}/admin"><spring:message
				code="admin.title.short" /></a></li>

	<li
		<c:if test='<%= request.getRequestURI().contains("/configureServer") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/dhisconnector/configureServer.form"><spring:message
				code="dhisconnector.configureServer" /></a>
	</li>

	<li
			<c:if test='<%= request.getRequestURI().contains("/uploadMapping") %>'>class="active"</c:if>>
		<a
				href="${pageContext.request.contextPath}/module/dhisconnector/uploadMapping.form"><spring:message
				code="dhisconnector.uploadMapping" /></a>
	</li>
	
	<li
			<c:if test='<%= request.getRequestURI().contains("/createMapping") %>'>class="active"</c:if>>
		<a
				href="${pageContext.request.contextPath}/module/dhisconnector/createMapping.form"><spring:message
				code="dhisconnector.createMapping" /></a>
	</li>

	<li
			<c:if test='<%= request.getRequestURI().contains("/runReports") %>'>class="active"</c:if>>
		<a
				href="${pageContext.request.contextPath}/module/dhisconnector/runReports.form"><spring:message
				code="dhisconnector.runReports" /></a>
	</li>
	
	<!-- Add further links here -->
</ul>
<h2>
	<spring:message code="dhisconnector.title" />
</h2>
