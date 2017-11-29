<img src="https://s3-eu-west-1.amazonaws.com/jembi/images/OMRSsig1.jpg" alt="OpenMRS"/>

# DHIS Connector Module

This module posts OpenMRS Period Indicator Report data to DHIS2 using the Reporting Rest module. Mappings between Period
Indicator Reports and DHIS2 Data Sets can be generated via the UI. The DHIS2 API is backed up for offline operation and
DXF files can be downloaded instead of posting to DHIS2 directly.

## Setup

Before you use the DHIS Connector Module, you will need to configure *both* a Period Indicator Report in OpenMRS ([instructions](https://wiki.openmrs.org/pages/viewpage.action?pageId=19300405#BuildingReports(StepByStepGuide)-PeriodIndicatorReportStep-By-Step)) and the corresponding DHIS2 Data Set in DHIS2 ([instructions](http://dhis2.github.io/dhis2-docs/master/en/implementer/html/ch12.html)).

### Installation

First install the [Reporting Rest Module](https://modules.openmrs.org/#/show/121/reportingrest)<sup>†</sup> (you will also need the [Rest Module](https://modules.openmrs.org/#/show/153/webservices-rest) and the [Reporting Module](https://modules.openmrs.org/#/show/119/reporting)). Then download and install the [DHIS Connector Module](https://github.com/jembi/openmrs-module-dhisconnector/releases).

† You will actually need to download [this build](https://github.com/psbrandt/openmrs-module-reportingrest/releases/download/1.5.1/reportingrest-1.5.1.omod) of the Reporting Rest Module until [this change](https://github.com/psbrandt/openmrs-module-reportingrest/commit/270a44b45b88bf1ba60d60e90938475d1265f12e) is merged and released.

### Configuration

The first step is to configure the link to your DHIS2 server. This is done by clicking the *Configure DHIS Server* link under the *DHIS Connector Module* heading on the OpenMRS Administration page. You will need to know the URL of your target DHIS2 instance as well the details of a user that has access to the API. To test with the DHIS2 demo server, use the following details:

| Field | Value |
| ----- | ----- |
|URL | https://play.dhis2.org/demo|
|Username | admin|
|Password | district|

> :warning: **NB:** Since DHIS API pagination isn't handled yet, you will have to change the *Rest Max Results Absolute* Webservices Module global property to 2000. Do this by clicking the *Settings* link on the OpenMRS Administration page, then click *Webservices* on the bottom left. Change the value of the *Rest Max Results Absolute* property to 2000 and click save.
> Ensure to build your reports in OpenMRS but making sure each indicator id is uniquely named

## Mappings

Before you can send Period Indicator Report data to DHIS2, a mapping must exist between the report and a DHIS2 Data Set. Mappings can either be generated via the UI or placed in the correct location. Mappings are stored as JSON on the file system at `OPENMRS_DIR/dhisconnector/mappings/`. On Ubuntu, this usually corresponds to `/usr/share/tomcat7/.OpenMRS/dhisconnector/mappings/`.


### Create Mapping

To generate a mapping via the UI, click the *Create Mapping* link under the *DHIS Connector Module* heading on the OpenMRS Administration page. Then select the Period Indicator Report from the left menu and the corresponding DHIS2 Data Set from the right menu. Drag the Data Elements and Category Option Combos from the right to the matching row on the left as follows:

![](https://cloud.githubusercontent.com/assets/668093/12115457/35c47c4c-b3bb-11e5-8004-58f76fbdf0c1.gif)

Finally, click save and give your mapping a unique name.

### Exporting Existing Mapping

This module provides a way of exporting existing mappings which basically archives them into one download that can thereafter be imported/uploaded into the current or another OpenMRS instance.

### Uploading Existing Mapping

The module provides an option to import or upload mappings previously exported from the current or other instances, this feature is available and will be released as part of 0.1.2

## Posting Data

To post data to the DHIS2 server or download the data in DXF format, click the *Run Reports* link under the *DHIS Connector Module* heading on the OpenMRS Administration page. Select the Period Indicator Report and the corresponding mapping to use.

Since Period Indicator Reports are always run for a specific location, you will also need to select the OpenMRS Location as well as the corresponding DHIS2 Organisation Unit.

The date selector will changed based on the period type of the DHIS2 Data Set.

Once you have selected a value for all the fields, click *Send Data* to post data directly to the DHIS2 server, or *Download JSON* to download the data in DXF format.

> To post backedup DHIS2 API, run; `bash postDHIS2API.sh` and enter required details

## DHIS2 Backup

Every time a request is sent to the DHIS2 server, the resulting JSON is stored on the file system at `OPENMRS_DIR/dhisconnector/dhis2Backup/`. On Ubuntu, this usually corresponds to `/usr/share/tomcat7/.OpenMRS/dhisconnector/dhis2Backup/`. If the DHIS2 server is no longer reachable, these backed up API values will be used by the DHIS Connector Module.

For OpenMRS implementations that should operate offline, it is possible to pre-populate this dhis2Backup by using both the DHIS API Import and export pages. Assuming all the required resources have been backed up by the online implementation, the offline implementation should be able to function correctly without ever being able to reach the DHIS2 server.

## Module Status

Jira Issues
  - [DHISConnector Module's issues](https://jembiprojects.jira.com/issues/?filter=13312)

Implemented
  - [x] Configure DHIS2 Server
  - [x] Drag and drop mapping generator
  - [x] Post data or download DXF
  - [x] Backup DHIS2 API for offline use
  - [x] Interface for uploading mappings
  - [x] Interface for exporting mappings
  - [x] Fix mapping creation UI to support multi-line DataElement names
  - [x] Interface for prepopulating DHIS2 API backup  
  - [x] Error handling
  - [x] Managing/editing mappings
  - [X] Scroll page when dragging mapping to top and bottom of page if necessary
  - [X] Generation, Downloading & Posting of ADX
  - [x] Automated reporting and posting to DHIS2 using configured mapping

TODO
  - [ ] Support other types of OpenMRS reports
  - [ ] DHIS2 API pagination
  - [ ] Upgrade all dhis endpoints or api to work after 2.20
  - [ ] Support Quaterly, SixMonthly, SixMonthlyApril, BiMonthly, FinancialApril, FinancialJuly, FinancialOct period types

## License

[MPL 2.0 w/ HD](http://openmrs.org/license/) © [OpenMRS Inc.](http://www.openmrs.org/)
