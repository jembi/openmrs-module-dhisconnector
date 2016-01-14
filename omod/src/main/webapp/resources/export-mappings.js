var OMRS_WEBSERVICES_BASE_URL = '../..';
var jq = jQuery;

angular.module('exportMappingApp', []).controller('exportMappingController', function($scope) {
	
	$scope.existingMappings = initializeMappings();
	$scope.selectedMappings = [];
	$scope.addAll_Disabled = false;
	$scope.addSelected_Disabed = true;
	$scope.removeSelected_Disabed = true;
	$scope.removeAll_Disabled = true;
	
	$scope.addAll = function() {
		$scope.existingMappings = initializeMappings();
		
		var selectedOptions = jq("#left-mapping-select").find("option:enabled");
		var selectedMappingsToSport = "";
		
		jq("#right-mapping-select").find("option:enabled").remove();
		for(var i = 0; i < selectedOptions.length; i++) {
			var option = "<option value='" + selectedOptions[i].value + "'>" + selectedOptions[i].text + "</option>";
			
			selectedOptions[i].setAttribute("disabled", true);
			selectedOptions[i].setAttribute('selected', true)
			jq("#right-mapping-select").find("option:enabled").end().append(option);
			selectedMappingsToSport += selectedOptions[i].value + "<:::>";
		}
		jq("#selected-mappings-to-export").val(selectedMappingsToSport);
		$scope.addAll_Disabled = true;
		$scope.addSelected_Disabed = true;
		$scope.removeSelected_Disabed = false;
		$scope.removeAll_Disabled = false;
	}
	
	$scope.removeAll = function() {
		$scope.existingMappings = initializeMappings();
		
		var leftOptions = jq("#left-mapping-select").find("option");
		
		jq("#right-mapping-select").find("option:enabled").remove();
		for(var i = 0; i < leftOptions.length; i++) {
			if(i != 0 && leftOptions[i].value != "header") {
				leftOptions[i].removeAttribute('disabled');
			}
		}
		$scope.addAll_Disabled = false;
		$scope.addSelected_Disabed = true;
		$scope.removeSelected_Disabed = true;
		$scope.removeAll_Disabled = true;
	}

	$scope.addSelected = function() {
		
	}
	
	$scope.removeSelected = function() {
		
	}
	
	$scope.removeSelected_Disabling = function() {
		
	}
	
});

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

function generateDateTimeDisplay(timeStamp) {
	var date = new Date(timeStamp);
	var month = date.getMonth() + 1;
	
	return date.getDate() + "/" + month + "/" + date.getUTCFullYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
}

function stringStartsWith(string, prefix) {
    return string.slice(0, prefix.length) == prefix;
}
