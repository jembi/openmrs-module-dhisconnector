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
var jq = jQuery;
var reportsDropDownAjax;
var dataSetsDropDownAjax;
var displayIndicatorsAjax;
var displayDatasetsAjax;
var headingForCreateMapping = "Add New Mapping";

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
            var comboOptionBox = jQuery('<div class="reportIndicator box" data-uid="' + categoryComboOptions[comboOption].id + '" title="' + categoryComboOptions[comboOption].name + '">' + renderDHIS2DatasetDragablePhrase(categoryComboOptions[comboOption].name) + '</div>');

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
    displayDatasetsAjax = jQuery.get(OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/dhisdataelements/" + dataElementId + "?v=full&limit=100", function (dataelement) {
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
    displayIndicatorsAjax = jQuery.get(OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/dhisdatasets/" + jQuery('#dataSetSelect').val() + "?v=full&limit=100", function (data) {

        jQuery('#periodType').html(data.periodType);

        dataElements = data.dataElements;
        var dataElementsOptionsCol = jQuery('#dataElementsOptions');
        dataElementsOptionsCol.html("");
        jQuery('#categoryComboOptions').html("");
        categoryComboOptions = {};


        dataElementsOptionsCol.append('<div class="reportRow row"><div class="reportIndicatorCol col-xs"><div class="reportIndicator box" style="height:2.8em;"><h4>Data Element Options</h4></div></div></div>');
        jQuery('#categoryComboOptions').append('<div class="reportRow row"><div class="reportIndicatorCol col-xs"><div class="reportIndicator box" style="height:2.8em;"><h4>Category Options</h4></div></div></div><img id="categoryComboLoader" class="spinner" src="../../moduleResources/dhisconnector/loading.gif"/>');


        for (var i = 0; i < dataElements.length; i++) {
            var dataElementOptionRow = jQuery('<div class="reportRow row"></div>');
            var dataElementOptionBox = jQuery('<div class="reportIndicator box" data-uid="' + dataElements[i].id + '" title="' + dataElements[i].name + '">' + renderDHIS2DatasetDragablePhrase(dataElements[i].name) + '</div>');

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

    reportIndicators.append('<div class="reportRow row"><div class="reportIndicatorCol col-xs"><div class="reportIndicator box" style="height:2.8em;"><h4>Indicators</h4></div></div><div class="reportDimensionCol col-xs"><div class="reportDimension box" style="height:2.8em;"><h4>Dimensions</h4></div></div></div>');
    dateElementMappings.append('<div class="reportRow row"><div class="reportIndicatorCol col-xs"><div class="reportIndicator box" style="height:2.8em;"><h4>Mapped Data Element</h4></div></div><div class="reportDimensionCol col-xs"><div class="reportDimension box" style="height:2.8em;"><h4>Mapped Category</h4></div></div></div>');

    var selectedSchema = reports.filter(function (val) {
        return val.uuid === jQuery('#reportSelect').val();
    })[0].schema;

    var rowCounter = 0;
    for (var property in selectedSchema.columns) {
        if (selectedSchema.columns.hasOwnProperty(property)) {
            var indicatorBox = jQuery('<div class="reportIndicator box"></div>');

            indicatorBox.append('<label title="' + property + '">' + renderIndicatorsDragablePhrase(property) + '</label>');

            var dimensionsBox = jQuery('<div class="reportDimension box"></div>');

            var dimObj = selectedSchema.columns[property];

            if (!jQuery.isEmptyObject(dimObj)) {
                for (var dimProp in dimObj) {
                    if (dimObj.hasOwnProperty(dimProp)) {
                    	var labelName = dimProp + '=' + dimObj[dimProp];
                    	
                    	dimensionsBox.append('<label title="' + labelName + '">' + renderIndicatorsDragablePhrase(labelName) + '</label>');
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
    reportsDropDownAjax = jQuery.get(OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/periodindicatorreports?limit=100", function (data) {

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
	dataSetsDropDownAjax = jQuery.get(OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/dhisdatasets?v=full&limit=100", function (data) {

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
                indicator: jQuery('.row-' + i + '.indicatorContainer > .box > label')[0].title,
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

    if (mapping.elements.length > 0) {
    	jQuery("#error-encountered-saving").html("");
		// post json obect
		jQuery.ajax({
			url : OMRS_WEBSERVICES_BASE_URL
					+ "/ws/rest/v1/dhisconnector/mappings",
			type : "POST",
			data : JSON.stringify(mapping),
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
				window.location = '../../module/dhisconnector/runReports.form';
			}
		});
	} else {
		jQuery('#saveMappingPopup').dialog('close');
		jQuery("#error-encountered-saving").html("Empty Mapping cannot be saved.");
		jQuery("html, body").animate({
			 scrollTop:0
		},"fast");
	}
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

function renderIndicatorsDragablePhrase(phrase) {
	return renderDragablePhrase(phrase, 26);
}

function renderMappingsDragablePhrase(phrase) {
	return renderDragablePhrase(phrase, 12);
}

function renderDHIS2DatasetDragablePhrase(phrase) {
	return renderDragablePhrase(phrase, 26);
}

function renderDragablePhrase(phrase, maxChars) {
	if(phrase.length > maxChars) {
		return phrase.substring(0, maxChars - 2) + "...";
	} else {
		return phrase;
	}
}

function generateDateTimeDisplay(timeStamp) {
	var date = new Date(timeStamp);
	var month = date.getMonth() + 1;
	
	return date.getDate() + "/" + month + "/" + date.getUTCFullYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
}

function initializeMappings() {
	var initializeMappings = [];
	
	jq.ajax({
		url: OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/mappings?limit=2000",
		async: false,
		method: "GET",
		dataType: "json",
		success: function (data) {
			for (var i = 0; i < data.results.length; i++) {
				if(data.results[i].name !== null && data.results[i].name !== "") {
					initializeMappings.push({"name": data.results[i].name, "created": generateDateTimeDisplay(data.results[i].created), "dateTime": data.results[i].created});
				}
			}
		}
	});
	return initializeMappings;
}

function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
}

function loadMappingToBeEditted(mapping) {
	console.log(mapping);
	if(reportsDropDownAjax !== undefined && dataSetsDropDownAjax !== undefined) {
		jq.when(reportsDropDownAjax, dataSetsDropDownAjax).done(function() {
			jq("#reportSelect").val(mapping.periodIndicatorReportGUID);
			jq("#dataSetSelect").val(mapping.dataSetUID);
			onReportSelect();
			onDataSetSelect();
			
			jq.when(displayIndicatorsAjax, displayDatasetsAjax).done(function() {
				for (var i = 0; i < jq('.indicatorContainer').length; i++) {
					var elementsIndex = elementsMatchIndicator(mapping.elements, i);
					
					var dataElement = (elementsIndex !== -1 && mapping.elements[elementsIndex] !== undefined) ? fetchElementFromGlobalDataElements(mapping.elements[elementsIndex].dataElement) : undefined;
					if(dataElement !== undefined) {
						jq('.row > .dataElementDragDestination.row-' + i).append('<div class="reportIndicator box" data-uid="' + dataElement.id + '" title="' + dataElement.name + '">' + renderMappingsDragablePhrase(dataElement.name) + '<span onclick="deleteMapping(this);" class="close">x</span></div>');
					}
					var comboOption = (elementsIndex !== -1 && mapping.elements[elementsIndex] !== undefined) ? fetchCategoryComboOption(mapping.elements[elementsIndex].comboOption, mapping.elements[elementsIndex].dataElement) : undefined;

					if(comboOption !== undefined) {
						jq('.row > .comboOptionDragDestination.row-' + i).append('<div class="reportIndicator box" data-uid="' + comboOption.id + '" title="' + comboOption.name + '">' + renderMappingsDragablePhrase(comboOption.name) + '<span onclick="deleteMapping(this);" class="close">x</span></div>');
					}
			    }
			});
		});
	}
	jq('#periodType').html(mapping.periodType);
	jq('#mappingName').val(mapping.name);
}

function fetchElementFromGlobalDataElements(element) {
	var dataElement;
	
	if(element !== undefined) {
		for(i = 0; i < dataElements.length; i++) {
			if(dataElements[i].id === element) {
				dataElement = dataElements[i];
			}
		}
	}
	return dataElement;
}

function fetchCategoryComboOption(combo, dataelement) {
	var comboOpt;
	var newCategoryComboOptions = [];
	
	if(dataelement !== undefined) {
		jq.ajax({
			url: OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/dhisdataelements/" + dataelement + "?v=full&limit=100",
			method: "GET",
			async: false,
			success: function(dataelementObj) {
				if(dataelementObj.categoryCombo !== null && dataelementObj.categoryCombo !== undefined && dataelementObj.categoryCombo.id !== undefined) {
					jq.ajax({
						url: OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/dhiscategorycombos/" + dataelementObj.categoryCombo.id + "?v=full&limit=100",
						method: "GET",
						async: false,
						success: function(categorycombo) {
					    	for (var i = 0; i < categorycombo.categoryOptionCombos.length; i++) {
					            if (categorycombo.categoryOptionCombos[i].id === combo) {
					            	comboOpt = categorycombo.categoryOptionCombos[i];
					            }
					        }
					    }
				    });
				}
			}
	    });
	}
	return comboOpt;
}

function elementsMatchIndicator(elements, index) {
	var elementsIndex = -1;

	for(var j = 0; j < elements.length; j++) {
		if (jq('.indicatorContainer')[index].children[0].children[0].title === jq(
				'.row-' + index + '.indicatorContainer > .box > label').html() && jq(
				'.indicatorContainer')[index].children[0].children[0].title === elements[j].indicator) {
			elementsIndex = j;
		}	
	}
	return elementsIndex;
}

jQuery(function () {//self invoked only if the whole page has completely loaded
    if (window.location.pathname.indexOf("createMapping.form") !== -1) {//loaded only at createMapping page
		//TODO this blocks takes long time while loading, investigate why and speed it up
    	populateReportsDropdown();
		populateDataSetsDropdown();
		drake = dragula({
			copy : dragulaCopyFunction,
			accepts : dragulaAcceptsFunction,
			removeOnSpill : true
		});
		jQuery('#saveMappingPopup').dialog({
			autoOpen : false,
			modal : true,
			title : 'Save DHIS Mapping',
			width : '90%'
		});
		jQuery('#saveMappingButton').click(function() {
			//TODO check if there are any active mappings that happened before pressing, else echo failure
			$j('#saveMappingPopup').dialog('open');
		});
		drake.on('drop', addCloseButtons);
		
		var selectedMapping = {"name" : getUrlParameter("edit"), "created" : getUrlParameter("created")};
		
		if(selectedMapping.name !== undefined && selectedMapping.created !== null) {
			var mappingDisplay = selectedMapping.name + "[@]" + selectedMapping.created;
			
			console.log("Loading selected mapping to be edited: " + mappingDisplay);
			headingForCreateMapping = "Editing: " + selectedMapping.name;
			
			var editAjaxCall = jq.ajax({
				url: OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/mappings/" + mappingDisplay,
				method: "GET",
				success: function(mapping) {
					if(mapping !== undefined && mapping.name !== undefined && mapping.created !== undefined) {
						
						loadMappingToBeEditted(mapping);
					} else {
						window.location = "../../module/dhisconnector/createMapping.form";
					}
				},
				statusCode: {
				    404: function() {
				    	window.location = "../../module/dhisconnector/createMapping.form";
				    }
				}
			});
		}
		jq("h4").html(headingForCreateMapping);
	}
});

