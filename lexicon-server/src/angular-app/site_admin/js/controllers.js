'use strict';

/* Controllers */

function UserCtrl($scope, userService) {

	$scope.vars = {
		selectedIndex: -1,
		editButtonName: "",
		editButtonIcon: "",
		inputfocus: false,
		showPasswordForm: false,
	};
	
	$scope.focusInput = function() {
		$scope.vars.inputfocus = true;
	};

	$scope.blurInput = function() {
		$scope.vars.inputfocus = false;
	};

	$scope.selected = [];
	$scope.updateSelection = function(event, item) {
		var selectedIndex = $scope.selected.indexOf(item);
		var checkbox = event.target;
		if (checkbox.checked && selectedIndex == -1) {
			$scope.selected.push(item);
		} else if (!checkbox.checked && selectedIndex != -1) {
			$scope.selected.splice(selectedIndex, 1);
		}
	};
	$scope.isSelected = function(item) {
		return item != null && $scope.selected.indexOf(item) >= 0;
	};

	$scope.users = [];
	$scope.queryUsers = function() {
		userService.list(function(result) {
			if (result.ok) {
				$scope.users = result.data.entries;
				$scope.userCount = result.data.count;
			} else {
				$scope.users = [];
			};
		});
	};
	//$scope.queryUsers();  // And run it right away to fetch the data for our list.

	$scope.selectRow = function(index, record) {
		console.log("Called selectRow(", index, ", ", record, ")");
		$scope.vars.selectedIndex = index;
		if (index < 0) {
			$scope.vars.record = {};
		} else {
			$scope.vars.record = record;
			$scope.vars.editButtonName = "Save";
			$scope.vars.editButtonIcon = "pencil";
		}
	};

	$scope.$watch("vars.record.id", function(newId, oldId) {
		// attrs.$observe("userid", function(newval, oldval) {
		console.log("Watch triggered with oldval '" + oldId + "' and newval '" + newId + "'");
		if (newId) {
			userService.read(newId, function(result) {
				$scope.record = result.data;
			});
		} else {
			// Clear data table
			$scope.record = {};
		}
	});

	$scope.addRecord = function() {
		$scope.selectRow(-1); // Make a blank entry in the "User data" area
		// TODO: Signal the user somehow that he should type in the user data area and hit Save
		// Right now this is not intuitive, so we need some kind of visual signal
		$scope.vars.editButtonName = "Add";
		$scope.vars.editButtonIcon = "plus";
		$scope.focusInput();
	};

	$scope.updateRecord = function(record) {
		console.log("updateRecord() called with ", record);
		if (record === undefined || record === {}) {
			// Avoid adding blank records to the database
			return null; // TODO: Or maybe just return a promise object that will do nothing...?
		}
		
		var isNewRecord = false;
		if (record.id === undefined) {
			isNewRecord = true; // Will be used below
//			if (record.groups === undefined) {
//				record.groups = [null]; // TODO: Should we put something into the form to allow setting gropus? ... Later, not now.
//			}
		}
		
		userService.update(record, function(result) {
			$scope.queryUsers();
			record.id = result.data;
			// TODO Don't do this as a separate API call here. CP 2013-07
			$scope.changePassword(record);
		});
		
		if (isNewRecord) {
			// We just added a record... so clear the user data area so we can add a new one later
			$scope.record = {};
			// And focus the input box so the user can just keep typing
			$scope.focusInput();
		} else {
			// We just edited a record, so remove focus from the user data area
			$scope.blurInput();
		}
	};

	$scope.removeUsers = function() {
		console.log("removeUsers");
		var userIds = [];
		for(var i = 0, l = $scope.selected.length; i < l; i++) {
			userIds.push($scope.selected[i].id);
		}
		if (l == 0) {
			// TODO ERROR
			return;
		}
		userService.remove(userIds, function(result) {
			if (result.ok) {
				$scope.queryUsers();
				// TODO
			}
		});
	};

	$scope.changePassword = function(record) {
		console.log("changePassword() called with ", record);
		userService.changePassword(record.id, record.password, function(result) {
			console.log("Password successfully changed.");
		});
	};
	
	$scope.showPasswordForm = function() {
		$scope.vars.showPasswordForm = true;
	};
	$scope.hidePasswordForm = function() {
		$scope.vars.showPasswordForm = false;
	};
	$scope.togglePasswordForm = function() {
		$scope.vars.showPasswordForm = !$scope.vars.showPasswordForm;
	}

}

angular.module(
	'sfAdmin.controllers',
	[ 'sf.services', 'palaso.ui.listview', 'palaso.ui.typeahead', 'ui.bootstrap' ]
)
.controller('UserCtrl', ['$scope', 'userService', UserCtrl])
.controller('PasswordCtrl', ['$scope', 'jsonRpc', function($scope, jsonRpc) {
	$scope.changePassword = function(record) {
		// Validation
		if (record.password != record.confirmPassword) {
			console.log("Error: passwords do not match");
			// TODO: Learn how to do Angular validation so I can give control back to the user. RM 2013-07
			return null;
		}
		jsonRpc.connct("/api/lf");
		params = {
			"userid": record.id,
			"newPassword": record.password,
		};
	};
}])
;