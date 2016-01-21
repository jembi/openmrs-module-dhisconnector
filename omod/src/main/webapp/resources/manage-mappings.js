angular.module('manageMappingsApp', []).controller('manageMappingsController', function($scope) {
	$scope.existingMappings = initializeMappings();
	
	$scope.fetchMappingName = function(mapping) {
		if(mapping !== undefined) {
			return mapping.name + "." + mapping.dateTime;
		} else
			return undefined;
	}
	
	/*
	 * TODO load customized (display active mapping & delete its option) create mapping page to add support for:
	 * ESAUDE-36 [DHISConnector - Add support to edit existing mappings]
	 */
	$scope.loadMappingEditor = function(mapping) {
		if(mapping !== undefined && event.target.localName !== "input") {
			console.log(mapping);
		}
	}
	
	$scope.disableMultipleDeletion = function() {
		if(jq(".select-this-mapping").is(":checked")) {
			return false;
		} else {
			return true;
		}
	}
	
	$scope.deleteSelectedMappings = function() {
		//mapping format: name[@]dateTime
		var selectedMappings = jq(".select-this-mapping:checked");
		
		for(i = 0; i < selectedMappings.length;i++) {
			jq.ajax({
				url: OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/mappings/" + selectedMappings[i].value,
				method: "DELETE",
				success: function (data) {
					//TODO check for and handle status 202/success or 404/mapping not found
					console.log(data);
					location.reload();
				}
			});
		}
	}
});
