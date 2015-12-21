var reports;
var mappings;
var locations;
var weekStartDate;
var weekEndDate

function onReportSelect() {

}

function populateReportsDropdown() {
    // fetch reports
    jQuery.get("/openmrs/ws/rest/v1/dhisconnector/periodindicatorreports?limit=2000&q=hasMapping", function (data) {

        var reportSelect = jQuery('<select id="reportSelect"></select>');
        reportSelect.append('<option value="">Select</option>');

        reportSelect.on('change', onReportSelect);

        for (var i = 0; i < data.results.length; i++) {
            reportSelect.append('<option value="' + data.results[i].uuid + '">' + data.results[i].name + '</option>');
        }

        jQuery('#reportsSelectContainer').html("");

        jQuery('#reportsSelectContainer').append(reportSelect);

        jQuery("#reportSelect").hide().fadeIn("slow");

        reports = data.results;
    });
}

function onMappingSelect() {
    // clear the date
    jQuery('#periodSelector').val("");
    jQuery('#periodSelector').monthpicker('destroy');
    jQuery('#periodSelector').datepicker('destroy');

    jQuery('#ui-datepicker-div > table > tbody > tr').die('mousemove');
    jQuery('.ui-datepicker-calendar tr').die('mouseleave');

    weekStartDate = null;
    weekEndDate = null;

    // get periodType
    var selectedPeriodType = mappings.filter(function (v) {
        return v.created == jQuery('#mappingSelect').val();
    })[0].periodType;

    if (selectedPeriodType == undefined)
        return;

    if (selectedPeriodType === 'Daily') {
        // set up daily
        jQuery('#periodSelector').datepicker({
            dateFormat: 'yymmdd'
        });
    } else if (selectedPeriodType === 'Weekly') {
        // set up weekly
        jQuery('#periodSelector').datepicker({
            showOtherMonths: true,
            selectOtherMonths: false,
            showWeek: true,
            firstDay: 1,
            onSelect: function (dateText, inst) {
                var date = jQuery(this).datepicker('getDate');
                var week = jQuery.datepicker.iso8601Week(date);
                if (week < 10) {
                    var displayWeek = date.getFullYear() + "W0" + week;
                } else {
                    var displayWeek = date.getFullYear() + "W" + week;
                }

                weekStartDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 1);
                weekEndDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 7)

                jQuery('#periodSelector').val(displayWeek);
            }
        });

        jQuery('#ui-datepicker-div > table > tbody > tr').live('mousemove', function () {
            jQuery(this).find('td a').addClass('ui-state-hover');
        });
        jQuery('.ui-datepicker-calendar tr').live('mouseleave', function () {
            jQuery(this).find('td a').removeClass('ui-state-hover');
        });
    } else if (selectedPeriodType === 'Monthly') {
        // set up monthly
        jQuery('#periodSelector').monthpicker({
            pattern: 'yyyymm'
        });
    }
}

function getPeriodDates() {
    var startDate, endDate;

    // get periodType
    var selectedPeriodType = mappings.filter(function (v) {
        return v.created == jQuery('#mappingSelect').val();
    })[0].periodType;

    if (selectedPeriodType == undefined)
        return;

    if (selectedPeriodType === 'Daily') {
        var date = jQuery('#periodSelector').datepicker('getDate');
        startDate = date;
        endDate = date;
    } else if (selectedPeriodType === 'Weekly') {
        startDate = weekStartDate;
        endDate = weekEndDate;
    } else if (selectedPeriodType === 'Monthly') {
        var date = jQuery('#periodSelector').monthpicker('getDate');
        startDate = new Date(date.getFullYear(), date.getMonth(), 1);
        endDate = new Date(date.getFullYear(), date.getMonth() + 1, 0);
    }

    return {
        startDate: startDate,
        endDate: endDate
    }
}

function populateMappingsDropdown() {
    // fetch mappings
    jQuery.get("/openmrs/ws/rest/v1/dhisconnector/mappings?limit=2000", function (data) {

        var mappingSelect = jQuery('<select id="mappingSelect"></select>');
        mappingSelect.append('<option value="">Select</option>');

        mappingSelect.on('change', onMappingSelect);

        for (var i = 0; i < data.results.length; i++) {
            mappingSelect.append('<option value="' + data.results[i].created + '">' + data.results[i].name + '</option>');
        }

        jQuery('#mappingSelectContainer').html("");

        jQuery('#mappingSelectContainer').append(mappingSelect);

        jQuery("#mappingSelect").hide().fadeIn("slow");

        mappings = data.results;
    });
}

function populateOpenMRSLocationsDropdown() {
    // fetch locations
    jQuery.get("/openmrs/ws/rest/v1/location?limit=2000", function (data) {

        var locationSelect = jQuery('<select id="locationSelect"></select>');
        locationSelect.append('<option value="">Select</option>');

        //reportSelect.on('change', onReportSelect);

        for (var i = 0; i < data.results.length; i++) {
            locationSelect.append('<option value="' + data.results[i].uuid + '">' + data.results[i].display + '</option>');
        }

        jQuery('#locationSelectContainer').html("");

        jQuery('#locationSelectContainer').append(locationSelect);

        jQuery("#locationSelect").hide().fadeIn("slow");

        locations = data.results;
    });
}

function populateDHISOrgUnitsDropdown() {
    // fetch orgunits
    jQuery.get("/openmrs/ws/rest/v1/dhisconnector/orgunits?limit=2000", function (data) {

        var orgUnitSelect = jQuery('<select id="orgUnitSelect"></select>');
        orgUnitSelect.append('<option value="">Select</option>');

        //reportSelect.on('change', onReportSelect);

        for (var i = 0; i < data.results.length; i++) {
            orgUnitSelect.append('<option value="' + data.results[i].id + '">' + data.results[i].name + '</option>');
        }

        jQuery('#orgUnitSelectContainer').html("");

        jQuery('#orgUnitSelectContainer').append(orgUnitSelect);

        jQuery("#orgUnitSelect").hide().fadeIn("slow");

        orgUnitSelect = data.results;
    });
}

function getReportData() {
    // http://localhost:8084/openmrs/ws/rest/v1/reportingrest/reportdata/e858e06a-06a0-4f26-9b97-a225089cd526?startDate=2012-10-10&endDate=2012-11-11&location=5c303ee9-ca10-43fc-829f-c9e45bb7748e&v=custom:(dataSets)
}

function testNotEmpty(selector, message) {
    jQuery(selector).siblings().remove();
    if(jQuery(selector).val() == "" || jQuery(selector).length == 0) {
        jQuery(selector).parent().append('<span class="error" id="nameEmptyError">'+message+'</span>');
        return false;
    }
    return true;
}

function checkMappingAppliesToReport() {
    // mapping.periodIndicatorReportGUID must equal the UUID of the selected report
    jQuery('#mappingSelect').siblings().remove();

    var mappingReport = mappings.filter(function (v) {
        return v.created == jQuery('#mappingSelect').val();
    })[0].periodIndicatorReportGUID;

    if(mappingReport == "" || mappingReport == undefined)
        return false;

    var selectedReport = jQuery('#reportSelect').val();

    if(selectedReport == "" || selectedReport == undefined)
        return false;

    if(mappingReport === selectedReport) {
        return true;
    } else {
        jQuery('#mappingSelect').parent().append('<span class="error" id="nameEmptyError">Selected mapping does not apply to selected report</span>');
        return false;
    }
}

function validateForm() {
    var ret = true;

    // Make sure we have no empty values
    ret &= testNotEmpty('#reportSelect', 'Report cannot be empty');
    ret &= testNotEmpty('#mappingSelect', 'Mapping cannot be empty');
    ret &= testNotEmpty('#locationSelect', 'Location cannot be empty');
    ret &= testNotEmpty('#orgUnitSelect', 'Organisational Unit cannot be empty');
    ret &= testNotEmpty('#periodSelector', 'Period cannot be empty');

    // Make sure mapping applies to report
    ret &= checkMappingAppliesToReport();

    return ret;
}

function sendDataToDHIS() {
   if(validateForm()) {
       var jsonPayload = getReportData();

       console.log(jsonPayload);
   }
}

jQuery(function () {
    populateReportsDropdown();
    populateMappingsDropdown();
    populateOpenMRSLocationsDropdown();
    populateDHISOrgUnitsDropdown();
});
