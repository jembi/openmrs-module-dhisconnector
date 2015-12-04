// Globals
var reports;
var dataSets;
var drake;

function getCategoryComboOptions(dataElementId, categoryOptionsBox) {
    // fetch data element details
    jQuery.get( "/openmrs/ws/rest/v1/reportingresttodhis/dhisdataelements/" + dataElementId + "?v=full&limit=100", function( dataelement ) {
        // fetch the category combo options
        jQuery.get( "/openmrs/ws/rest/v1/reportingresttodhis/dhiscategorycombos/" + dataelement.categoryCombo.id  + "?v=full&limit=100", function( categorycombo ) {
            var dataElementOptionRow = jQuery('<div class="reportRow row"></div>');
            for(var i = 0; i < categorycombo.categoryOptionCombos.length; i++) {
                dataElementOptionRow.append('<div class="reportIndicator box">'+categorycombo.categoryOptionCombos[i].name+'</div>');
            }
            categoryOptionsBox.html("");
            categoryOptionsBox.append(dataElementOptionRow);
            categoryOptionsBox.hide().fadeIn("slow");
            drake.containers.push(dataElementOptionRow.get(0));
        });
    });
}

function onDataSetSelect() {
    // fetch dataset details
    jQuery.get( "/openmrs/ws/rest/v1/reportingresttodhis/dhisdatasets/" + jQuery('#dataSetSelect').val() + "?v=full&limit=100", function( data ) {

        var dataElements = data.dataElements;
        var dataElementsOptionsCol = jQuery('#dataElementsOptions');
        dataElementsOptionsCol.html("");

       dataElementsOptionsCol.append('<div class="reportRow row"><div class="reportIndicatorCol col-xs"><div class="reportIndicator box"><h4>Data Element Options</h4></div></div><div class="reportDimensionCol col-xs"><div class="reportDimension box"><h4>Category Options</h4></div></div></div>'); 


        for(var i = 0; i < dataElements.length; i++) {
            var dataElementOptionRow = jQuery('<div class="reportRow row"></div>');
            var dataElementOptionBox = jQuery('<div class="reportIndicator box">'+dataElements[i].name+'</div>');
            var categoryComboOptionOptionBox = jQuery('<div class="reportDimension box">&nbsp; </div>');

            getCategoryComboOptions(dataElements[i].id, categoryComboOptionOptionBox);

            var dataElementOptionContainer = jQuery('<div class="reportIndicatorCol col-xs"></div>');
            var categoryComboOptionContainter = jQuery('<div class="reportDimensionCol col-xs"></div>');

            drake.containers.push(dataElementOptionContainer.get(0));
            //drake.containers.push(categoryComboOptionContainter.get(0));

            dataElementOptionRow.append(dataElementOptionContainer.append(dataElementOptionBox));
            dataElementOptionRow.append(categoryComboOptionContainter.append(categoryComboOptionOptionBox));
            dataElementsOptionsCol.append(dataElementOptionRow);
        }

        dataElementsOptionsCol.hide().fadeIn("slow");
    });   
}

function onReportSelect() {
    var reportIndicators = jQuery('#reportIndicators');
    reportIndicators.html("");

    var dateElementMappings = jQuery('#dataElementsMappings');
    dateElementMappings.html("");

       reportIndicators.append('<div class="reportRow row"><div class="reportIndicatorCol col-xs"><div class="reportIndicator box"><h4>Indicators</h4></div></div><div class="reportDimensionCol col-xs"><div class="reportDimension box"><h4>Dimensions</h4></div></div></div>');
       dateElementMappings.append('<div class="reportRow row"><div class="reportIndicatorCol col-xs"><div class="reportIndicator box"><h4>Mapped Data Element</h4></div></div><div class="reportDimensionCol col-xs"><div class="reportDimension box"><h4>Mapped Category</h4></div></div></div>'); 

    var selectedSchema = reports.filter(function(val) { return val.uuid === jQuery('#reportSelect').val(); })[0].schema;

    for (var property in selectedSchema.columns) {
        if (selectedSchema.columns.hasOwnProperty(property)) {
            var indicatorBox = jQuery('<div class="reportIndicator box"></div>');
            var dataElementMappingBox = jQuery('<div class="dataElementDragDestination reportIndicator box">&nbsp; </div>');

            indicatorBox.append(property);

            var dimensionsBox = jQuery('<div class="reportDimension box"></div>');
            var categoryComboOptionMappingBox = jQuery('<div class="comboOptionDragDestination reportDimension box">&nbsp; </div>');

            var dimObj = selectedSchema.columns[property];

            if(!jQuery.isEmptyObject(dimObj)) {
                for (var dimProp in dimObj) {
                    if (dimObj.hasOwnProperty(dimProp)) {
                        dimensionsBox.append(dimProp + '=' + dimObj[dimProp]);
                    }
                }
            } else {
                dimensionsBox.append("&nbsp;");
            }

            var row = jQuery('<div class="reportRow row"></div>');
            var dataSetRow = jQuery('<div class="reportRow row"></div>');

            row.append(jQuery('<div class="reportIndicatorCol col-xs"></div>').append(indicatorBox));
            row.append(jQuery('<div class="reportDimensionCol col-xs"></div>').append(dimensionsBox));
            reportIndicators.append(row);

            var dataElementMappingContainer = jQuery('<div class="reportIndicatorCol col-xs"></div>');
            dataSetRow.append(dataElementMappingContainer);//.append(dataElementMappingBox));
            var comboMappingContainer = jQuery('<div class="reportDimensionCol col-xs"></div>');
            dataSetRow.append(comboMappingContainer)//.append(categoryComboOptionMappingBox));
            dateElementMappings.append(dataSetRow);

            drake.containers.push(dataElementMappingContainer.get(0));
            drake.containers.push(comboMappingContainer.get(0));
        }
    }
    reportIndicators.hide().fadeIn("slow");
    dateElementMappings.hide().fadeIn("slow");
}

function populateReportsDropdown() {
    // fetch reports
    jQuery.get( "/openmrs/ws/rest/v1/reportingresttodhis/periodindicatorreports?limit=100", function( data ) {

        var reportSelect = jQuery('<select id="reportSelect"></select>');
        reportSelect.on('change', onReportSelect);

        for(var i = 0; i < data.results.length; i++) {
            reportSelect.append('<option value="' + data.results[i].uuid + '">' + data.results[i].name + '</option>');
        }

        jQuery('#reports').html("");

        jQuery('#reports').append(reportSelect);

        jQuery("#reportSelect").hide().fadeIn("slow");

        reports = data.results;
    });
}

function populateDataSetsDropdown() {
     jQuery.get( "/openmrs/ws/rest/v1/reportingresttodhis/dhisdatasets?v=full&limit=100", function( data ) {

        var reportSelect = jQuery('<select id="dataSetSelect"></select>');
        reportSelect.on('change', onDataSetSelect);

        for(var i = 0; i < data.results.length; i++) {
            reportSelect.append('<option value="' + data.results[i].id + '">' + data.results[i].name + '</option>');
        }

        jQuery('#datasets').html("");

        jQuery('#datasets').append(reportSelect);

        jQuery("#dataSetSelect").hide().fadeIn("slow");

        dataSets = data.results;
    });   
}

jQuery(function(){
    populateReportsDropdown();
    populateDataSetsDropdown();
    drake = dragula({copy: true});
});

