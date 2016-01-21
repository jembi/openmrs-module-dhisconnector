angular.module('exportMappingApp', []).controller('exportMappingController', function($scope) {
	
	$scope.existingMappings = initializeMappings();
	$scope.selectedMappings = [];
	
	$scope.addAll_Disabled = function() {
		var selectedOptions = jq("#left-mapping-select").find("option:enabled");
		
		if(selectedOptions.length > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	$scope.addSelected_Disabed = function() {
		/*if(jq("#left-mapping-select").find("option:enabled").length > 0 && jq("#left-mapping-select").find("option:enabled:checked") > 0) {
			return false;
		} else {
			return true;
		}*/return false; 
	}
	$scope.removeSelected_Disabed = function() {
		/*if(jq("#right-mapping-select").find("option:enabled").length > 0 && jq("#right-mapping-select").find("option:enabled:checked") > 0) {
			return false;
		} else {
			return true;
		}*/return false; 
	}
	
	$scope.removeAll_Disabled = function() {
		var selectedOptions = jq("#right-mapping-select").find("option:enabled");
		
		if(selectedOptions.length > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	$scope.addAll = function() {
		$scope.existingMappings = initializeMappings();
		
		var selectedOptions = jq("#left-mapping-select").find("option");
		var selectedMappingsToSport = "";
		
		//jq("#right-mapping-select").find("option:enabled").remove();
		for(var i = 0; i < selectedOptions.length; i++) {
			if(selectedOptions[i].value !== "header" && !objectExistsInArray(rightMappings(), selectedOptions[i].value)) {
				var option = "<option value='" + selectedOptions[i].value + "'>" + selectedOptions[i].text + "</option>";
				
				jq("#right-mapping-select").find("option:enabled").end().append(option);
				selectedMappingsToSport += selectedOptions[i].value + "<:::>";
				selectedOptions[i].setAttribute('disabled', true);
			}
		}
		jq("#selected-mappings-to-export").val(selectedMappingsToSport);
	}
	
	$scope.removeAll = function() {
		$scope.existingMappings = initializeMappings();
		
		var leftOptions = jq("#left-mapping-select").find("option");
		var selectedMappingsToSport = "";
		
		jq("#right-mapping-select").find("option:enabled").remove();
		for(var i = 0; i < leftOptions.length; i++) {
			if(leftOptions[i].value !== "header") {
				leftOptions[i].removeAttribute('disabled');
			}
		}
		jq("#selected-mappings-to-export").val(selectedMappingsToSport);
	}
	
	$scope.removeSelected = function() {
		var selectedExistingOptions = jq("#right-mapping-select").find("option:enabled:checked");
		var leftOptions = jq("#left-mapping-select").find("option:disabled");
		var selectedMappingsToSport = jq("#selected-mappings-to-export").val();
		
		if(selectedExistingOptions.length > 0) {
			for(var i = 0; i < selectedExistingOptions.length; i++) {
				for(var j = 0; j < leftOptions.length; j++) {
					if(leftOptions[j].value !== "header" && objectExistsInArray(leftDisabledMappings(), selectedExistingOptions[i].value)) {//first header option
						leftOptions[j].removeAttribute('disabled');
					}
				}
				jq("#right-mapping-select option[value='" + selectedExistingOptions[i].value + "']").remove();
				selectedMappingsToSport = selectedMappingsToSport.replace(selectedExistingOptions[i].value, "")
				jq("#selected-mappings-to-export").val(selectedMappingsToSport)
			}
		}
		jq("#selected-mappings-to-export").val(selectedMappingsToSport);
	}
	
	$scope.addSelected = function() {
		var selectedExistingOptions = jq("#left-mapping-select").find("option:enabled:checked");
		var selectedMappingsToSport = jq("#selected-mappings-to-export").val();
		
		if(selectedExistingOptions.length > 0) {
			for(var i = 0; i < selectedExistingOptions.length; i++) {
				if(!objectExistsInArray(rightMappings(), selectedExistingOptions[i].value)) {
					var option = "<option value='" + selectedExistingOptions[i].value + "'>" + selectedExistingOptions[i].text + "</option>";
					
					selectedMappingsToSport += selectedExistingOptions[i].value + "<:::>";
					jq("#right-mapping-select").find("option:enabled").end().append(option);
					selectedExistingOptions[i].setAttribute('disabled', true);
				}
			}
		}
		jq("#selected-mappings-to-export").val(selectedMappingsToSport);
	}
	
});

function leftDisabledMappings() {
	var selectedOptions = jq("#left-mapping-select").find("option:disabled");
	var col = [];
	
	for(var i = 0; i < selectedOptions.length; i++) {
		if(selectedOptions[i].value !== "header") {
			col.push(selectedOptions[i].value);
		}
	}
	return col;
}

function rightMappings() {
	var selectedOptions = jq("#right-mapping-select").find("option:enabled");
	var col = [];
	
	for(var i = 0; i < selectedOptions.length; i++) {
		col.push(selectedOptions[i].value);
	}
	return col;
}

function objectExistsInArray(arr, obj) {
	var exists = false;
	
	for(var i = 0; i < arr.length; i++) {
		if(arr[i] === obj) {
			exists = true;
		}
	}
	return exists;
}

function stringStartsWith(string, prefix) {
    return string.slice(0, prefix.length) == prefix;
}
