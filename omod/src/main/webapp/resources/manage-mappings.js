angular.module('manageMappingsApp', []).controller('manageMappingsController', function($scope) {
	$scope.existingMappings = initializeMappings();
	
	$scope.fetchMappingDisplay = function(mapping) {
		if(mapping !== undefined) {
			//this hack ([@]) is understood by as the server to trigger a get by uuid
			return {"name": mapping.name, "created": mapping.dateTime};
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
			window.location = "../../module/dhisconnector/createMapping.form?edit=" + mapping.name + "&created=" + mapping.created;
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
		//mapping display format: name[@]dateTime
		var selectedMappings = jq(".select-this-mapping:checked");
		
		if(confirm("Are you sure you want to delete selected Mapping(s)?")) {
			for(i = 0; i < selectedMappings.length;i++) {
				jq.ajax({
					url: OMRS_WEBSERVICES_BASE_URL + "/ws/rest/v1/dhisconnector/mappings/" + selectedMappings[i].value,
					method: "DELETE",
					success: function (data) {
						//TODO check for and handle status 202/success or 404/mapping not found
						location.reload();
					}
				});
			}
		}
	}
});
