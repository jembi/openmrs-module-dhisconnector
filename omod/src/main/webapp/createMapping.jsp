<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/moduleResources/reportingresttodhis/flexboxgrid.min.css"/>
<openmrs:htmlInclude file="/moduleResources/reportingresttodhis/dragula.min.css"/>
<openmrs:htmlInclude file="/moduleResources/reportingresttodhis/reportingresttodhis.css"/>
<openmrs:htmlInclude file="/moduleResources/reportingresttodhis/dragula.min.js"/>
<openmrs:htmlInclude file="/moduleResources/reportingresttodhis/reportingresttodhis.js"/>

<%@ include file="template/localHeader.jsp"%>

<h3>Generate Period Indicator Report to DHIS Dataset Mapping</h3>

<p>
<div class="row">
  <div class="col-xs-5">
    <div class="box" id="reports"><img class="spinner" src="/openmrs/moduleResources/reportingresttodhis/loading.gif"/></div>
  </div>
  <div class="col-xs">
    <div class="box" id="datasets"><img class="spinner" src="/openmrs/moduleResources/reportingresttodhis/loading.gif"/></div>
  </div>
</div>
</p>

<p>
<div class="row">
  <div class="col-xs-5" id="reportIndicators">

  </div>
  <div class="col-xs" id="dataElements">
    <div class="row">
    <div class="col-xs" id="dataElementsMappings">
      </div>
    <div class="col-xs" id="dataElementsOptions">
    </div>
      </div>
  </div>
  </div>
</p>

<%@ include file="/WEB-INF/template/footer.jsp"%>