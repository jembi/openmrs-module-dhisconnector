/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

// Globals
var reports;
var dataSets;
var drake;
var dataElements;
var categoryComboOptions;
var OMRS_WEBSERVICES_BASE_URL = '../..';

function allowMappingRemoval(el, container, source) {
    console.log(el);
    console.log(container);
    console.log(source);
}

function renderCategoryComboOptions() {
    var comboOptionsCol = jQuery('#categoryComboOptions');

    jQuery('#categoryComboLoader').remove();

    for (var comboOption in categoryComboOptions) {
        if (categoryComboOptions.hasOwnProperty(comboOption)) {
            var comboOptionRow = jQuery('<div class="reportRow row"></div>');
            var comboOptionBox = jQuery('<div class="reportIndicator box" data-uid="' + categoryComboOptions[comboOption].id + '">' + categoryComboOptions[comboOption].name + '</div>');

            var comboOptionBoxContainer = jQuery('<div class="comboOptionDragSource reportIndicatorCol col-xs"></div>');
            comboOptionRow.append(comboOptionBoxContainer.append(comboOptionBox));
            comboOptionsCol.append(comboOptionRow);
            drake.containers.push(comboOptionBoxContainer.get(0));
        }
    }

    comboOptionsCol.hide().fadeIn("slow");
}

function getCategoryComboOptions(dataElementId, requests) {
    var def = jQuery.Deferred();
    var requests = [];

    // fetch data element details
    jQuery.get(OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/dhisdataelements/" + dataElementId + "?v=full&limit=100", function (dataelement) {
        // fetch the category combo options
        requests.push(jQuery.get(OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/dhiscategorycombos/" + dataelement.categoryCombo.id + "?v=full&limit=100", function (categorycombo) {
            for (var i = 0; i < categorycombo.categoryOptionCombos.length; i++) {
                if (!categoryComboOptions.hasOwnProperty(categorycombo.categoryOptionCombos[i].id)) {
                    categoryComboOptions[categorycombo.categoryOptionCombos[i].id] = categorycombo.categoryOptionCombos[i];
                }
            }
        }));
        jQuery.when.apply($, requests).then(function () {
            def.resolve();
        });
    });

    return def;
}

function getDataElementsAndCategoryComboOptions() {
    var def = jQuery.Deferred();
    var requests = [];

    // fetch dataset details
    jQuery.get(OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/dhisdatasets/" + jQuery('#dataSetSelect').val() + "?v=full&limit=100", function (data) {

        jQuery('#periodType').html(data.periodType);

        dataElements = data.dataElements;
        var dataElementsOptionsCol = jQuery('#dataElementsOptions');
        dataElementsOptionsCol.html("");
        jQuery('#categoryComboOptions').html("");
        categoryComboOptions = {};


        dataElementsOptionsCol.append('<div class="reportRow row"><div class="reportIndicatorCol col-xs"><div class="reportIndicator box"><h4>Data Element Options</h4></div></div></div>');
        jQuery('#categoryComboOptions').append('<div class="reportRow row"><div class="reportIndicatorCol col-xs"><div class="reportIndicator box"><h4>Category Options</h4></div></div></div><img id="categoryComboLoader" class="spinner" src="../../moduleResources/dhisconnector/loading.gif"/>');


        for (var i = 0; i < dataElements.length; i++) {
            var dataElementOptionRow = jQuery('<div class="reportRow row"></div>');
            var dataElementOptionBox = jQuery('<div class="reportIndicator box" data-uid="' + dataElements[i].id + '">' + dataElements[i].name + '</div>');

            requests.push(getCategoryComboOptions(dataElements[i].id, requests));

            var dataElementOptionContainer = jQuery('<div class="dataElementDragSource reportIndicatorCol col-xs"></div>');

            drake.containers.push(dataElementOptionContainer.get(0));

            dataElementOptionRow.append(dataElementOptionContainer.append(dataElementOptionBox));
            dataElementsOptionsCol.append(dataElementOptionRow);
        }

        dataElementsOptionsCol.hide().fadeIn("slow");
        jQuery.when.apply($, requests).then(function () {
            def.resolve();
        });
    });

    return def;
}


function onDataSetSelect() {
    getDataElementsAndCategoryComboOptions().then(renderCategoryComboOptions);
}

function onReportSelect() {
    var reportIndicators = jQuery('#reportIndicators');
    reportIndicators.html("");

    var dateElementMappings = jQuery('#dataElementsMappings');
    dateElementMappings.html("");

    reportIndicators.append('<div class="reportRow row"><div class="reportIndicatorCol col-xs"><div class="reportIndicator box"><h4>Indicators</h4></div></div><div class="reportDimensionCol col-xs"><div class="reportDimension box"><h4>Dimensions</h4></div></div></div>');
    dateElementMappings.append('<div class="reportRow row"><div class="reportIndicatorCol col-xs"><div class="reportIndicator box"><h4>Mapped Data Element</h4></div></div><div class="reportDimensionCol col-xs"><div class="reportDimension box"><h4>Mapped Category</h4></div></div></div>');

    var selectedSchema = reports.filter(function (val) {
        return val.uuid === jQuery('#reportSelect').val();
    })[0].schema;

    var rowCounter = 0;
    for (var property in selectedSchema.columns) {
        if (selectedSchema.columns.hasOwnProperty(property)) {
            var indicatorBox = jQuery('<div class="reportIndicator box"></div>');

            indicatorBox.append(property);

            var dimensionsBox = jQuery('<div class="reportDimension box"></div>');

            var dimObj = selectedSchema.columns[property];

            if (!jQuery.isEmptyObject(dimObj)) {
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

            row.append(jQuery('<div class="indicatorContainer reportIndicatorCol col-xs row-' + rowCounter + '"></div>').append(indicatorBox));
            row.append(jQuery('<div class="dimensionContainer reportDimensionCol col-xs row-' + rowCounter + '"></div>').append(dimensionsBox));
            reportIndicators.append(row);

            var dataElementMappingContainer = jQuery('<div class="dataElementContainer dataElementDragDestination reportIndicatorCol col-xs row-' + rowCounter + '"></div>');
            dataSetRow.append(dataElementMappingContainer);
            var comboMappingContainer = jQuery('<div class="comboOptionContainer comboOptionDragDestination reportDimensionCol col-xs row-' + rowCounter + '"></div>');
            dataSetRow.append(comboMappingContainer);
            dateElementMappings.append(dataSetRow);

            drake.containers.push(dataElementMappingContainer.get(0));
            drake.containers.push(comboMappingContainer.get(0));

            rowCounter++;
        }
    }
    reportIndicators.hide().fadeIn("slow");
    dateElementMappings.hide().fadeIn("slow");
}

function populateReportsDropdown() {
    // fetch reports
    jQuery.get(OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/periodindicatorreports?limit=100", function (data) {

        var reportSelect = jQuery('<select id="reportSelect"></select>');
        reportSelect.on('change', onReportSelect);

        for (var i = 0; i < data.results.length; i++) {
            reportSelect.append('<option value="' + data.results[i].uuid + '">' + data.results[i].name + '</option>');
        }

        jQuery('#reports').html("");

        jQuery('#reports').append(reportSelect);

        jQuery("#reportSelect").hide().fadeIn("slow");

        reports = data.results;
    });
}

function populateDataSetsDropdown() {
    // fetch datasets
    jQuery.get(OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/dhisdatasets?v=full&limit=100", function (data) {

        var reportSelect = jQuery('<select id="dataSetSelect"></select>');
        reportSelect.on('change', onDataSetSelect);

        for (var i = 0; i < data.results.length; i++) {
            reportSelect.append('<option value="' + data.results[i].id + '">' + data.results[i].name + '</option>');
        }

        jQuery('#datasets').html("");

        jQuery('#datasets').append(reportSelect);

        jQuery("#dataSetSelect").hide().fadeIn("slow");

        dataSets = data.results;
    });
}

function dragulaCopyFunction(el, source) {
    //console.log(source);
    return true;
}

function dragulaAcceptsFunction(el, target, source, sibling) {
    var dataElementDrag = (jQuery(target).hasClass('dataElementDragDestination') && jQuery(source).hasClass('dataElementDragSource'));
    var comboOptionDrag = (jQuery(target).hasClass('comboOptionDragDestination') && jQuery(source).hasClass('comboOptionDragSource'));

    // Ensure we can only create one mapping per indicator
    if (jQuery(target).children().length > 1) {
        return false;
    }

    return dataElementDrag || comboOptionDrag;
}

function getDefault() {
    var comboOptions = jQuery('.comboOptionDragSource > .box');

    for (var i = 0; i < comboOptions.length; i++) {
        var option = jQuery(comboOptions.get(i));
        if (option.html() == "(default)") {
            return option.attr('data-uid');
        }
    }

    return null;
}

function saveMapping(event) {
    var mapping = {};

    jQuery('#nameEmptyError').remove();
    if (jQuery('#mappingName').val().length == 0) {
        jQuery('#mappingName').parent().append('<span class="error" id="nameEmptyError">Mapping name cannot be empty.</span>');
        event.preventDefault();
        return;
    }

    // build mapping json
    mapping.name = jQuery('#mappingName').val();
    mapping.periodType = jQuery('#periodType').html();
    mapping.created = Date.now();
    mapping.dataSetUID = jQuery('#dataSetSelect').val();
    mapping.periodIndicatorReportGUID = jQuery('#reportSelect').val();

    mapping.elements = [];
    for (var i = 0; i < jQuery('.indicatorContainer').length; i++) {
        var row =

            mapping.elements.push({
                indicator: jQuery('.row-' + i + '.indicatorContainer > .box').html(),
                dataElement: jQuery('.row-' + i + '.dataElementContainer > .box').attr('data-uid'),
                comboOption: jQuery('.row-' + i + '.comboOptionContainer > .box').attr('data-uid')
            });
    }

    // remove unmapped indicators
    mapping.elements = mapping.elements.filter(function (value) {
        return value.dataElement != undefined;
    });

    // associate default combooption with blank category mappings
    var def = getDefault();
    var noMapping = [];
    for (var j = 0; j < mapping.elements.length; j++) {
        if (mapping.elements[j].dataElement == undefined) {
            noMapping.push(j);
        } else if (mapping.elements[j].comboOption == undefined) {
            mapping.elements[j].comboOption = def;
        }
    }

    // post json obect
    jQuery.ajax({
        url: OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/mappings",
        type: "POST",
        data: JSON.stringify(mapping),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            window.location = '../../module/dhisconnector/runReports.form';
        },
    });
}

function addCloseButtons() {
    var mappings = jQuery('#dataElementsMappings .box');

    for (var i = 0; i < mappings.length; i++) {
        if (jQuery(mappings[i]).attr('data-uid') && jQuery(mappings[i]).children().length == 0) { // don't add button to headings
            jQuery(mappings[i]).append('<span onClick="deleteMapping(this);" class="close">x</span>');
        }
    }

}

function deleteMapping(el) {
    jQuery(el).parent().remove();
}

jQuery(function () {
    populateReportsDropdown();
    populateDataSetsDropdown();
    drake = dragula({
        copy: dragulaCopyFunction,
        accepts: dragulaAcceptsFunction,
        removeOnSpill: true
    });

    jQuery('#saveMappingPopup').dialog({
        autoOpen: false,
        modal: true,
        title: 'Save DHIS Mapping',
        width: '90%'
    });

    jQuery('#saveMappingButton').click(function () {
        $j('#saveMappingPopup').dialog('open');
    });
    drake.on('drop', addCloseButtons);
});

