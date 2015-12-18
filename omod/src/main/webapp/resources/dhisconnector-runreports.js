var reports;
var mappings

function onReportSelect() {

}

function populateReportsDropdown() {
    // fetch reports
    jQuery.get( "/openmrs/ws/rest/v1/dhisconnector/periodindicatorreports?limit=2000&q=hasMapping", function( data ) {

        var reportSelect = jQuery('<select id="reportSelect"></select>');
        reportSelect.append('<option value="">Select</option>');

        reportSelect.on('change', onReportSelect);

        for(var i = 0; i < data.results.length; i++) {
            reportSelect.append('<option value="' + data.results[i].uuid + '">' + data.results[i].name + '</option>');
        }

        jQuery('#reportsSelectContainer').html("");

        jQuery('#reportsSelectContainer').append(reportSelect);

        jQuery("#reportSelect").hide().fadeIn("slow");

        reports = data.results;
    });
}

function populateMappingsDropdown() {
    // fetch mappings
    jQuery.get( "/openmrs/ws/rest/v1/dhisconnector/mappings?limit=2000", function( data ) {

        var mappingSelect = jQuery('<select id="mappingSelect"></select>');
        mappingSelect.append('<option value="">Select</option>');

        //reportSelect.on('change', onReportSelect);

        for(var i = 0; i < data.results.length; i++) {
            reportSelect.append('<option value="' + data.results[i].uuid + '">' + data.results[i].name + '</option>');
        }

        jQuery('#mappingSelectContainer').html("");

        jQuery('#mappingSelectContainer').append(mappingSelect);

        jQuery("#mappingSelect").hide().fadeIn("slow");

        mappings = data.results;
    });
}

function populateOpenMRSLocationsDropdown() {

}

function populateDHISOrgUnitsDropdown() {

}

jQuery(function(){
    populateReportsDropdown();
    populateMappingsDropdown();
    populateOpenMRSLocationsDropdown();
    populateDHISOrgUnitsDropdown();
});
