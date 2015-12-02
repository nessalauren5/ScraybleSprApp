var http = require('http');
var Patient = require("./Patient");
var Encounter = require("./Encounter");
var Observation = require("./Observation");
var Condition = require("./Condition");
var Medication = require("./Medication");
var MedicationDispense = require("./MedicationDispense");
var MedicationPrescription = require("./MedicationPrescription");

function Fhir() {}

Fhir.prototype.constructUrl = function constructUrl(resource, id) {
	var httpUrl = "http";
	var colon = ":";
	var slash = "/";
	var url = "polaris.i3l.gatech.edu";
	var port = "8080";
	var app = "gt-fhir-webapp";
	var base = "base";
	var json = "?_format=json";
	return httpUrl + colon + slash + slash
		+ url + colon + port + slash
		+ app + slash
		+ base + slash
		+ resource + slash + id
		+ json;
};

Fhir.prototype.getResourceById = function getResourceById(resource, id, callback) {
	var fhir = new Fhir();
	var url = fhir.constructUrl(resource,id);
	http.get(url,
		function (resp) {
			var chunks = [];
			resp.on('data', function (chunk) {
				chunks.push(chunk);
			});
			resp.on('end', function (pat) {
				var text = Buffer.concat(chunks).toString('utf8');
				if (resource == "Patient") {
					var patient = new Patient();
					callback(patient.getPatientByText(text));
				}
				if (resource == "Encounter") {
					var encounter = new Encounter();
					callback(encounter.getEncounterByText(text));
				}
				if (resource == "Observation") {
					var observation = new Observation();
					callback(observation.getObservationByText(text));
				}
				if (resource == "Condition") {
					var condition = new Condition();
					callback(condition.getConditionByText(text));
				}
				if (resource == "Medication") {
					var medication = new Medication();
					callback(medication.getMedicationByText(text));
				}
				if (resource == "MedicationDispense") {
					var medicationDispense = new MedicationDispense();
					callback(medicationDispense.getMedicationDispenseByText(text));
				}
				if (resource == "MedicationPrescription") {
					var medicationPrescription = new MedicationPrescription();
					callback(medicationPrescription.getMedicationPrescriptionByText(text));
				}
			});
		}
	);
};

Fhir.prototype.printResourceById = function printResourceById(resource, id, callback) {
	var fhir = new Fhir();
	var url = fhir.constructUrl(resource,id);
	http.get(url,
		function (resp) {
			var chunks = [];
			resp.on('data', function (chunk) {
				chunks.push(chunk);
			});
			resp.on('end', function () {
				var text = Buffer.concat(chunks).toString('utf8');
				callback(text);
			});
		});
};

Fhir.prototype.insertOrUpdateResource = function insertOrUpdateResource(resource, callback) {
  var data = JSON.stringify(resource.jsonPrint());
	//var data = resource.jsonPrint();
	//callback(data);

	//Assume an update
	var slash = "/";
	var thisPath = "/gt-fhir-webapp/base"
		+ slash
		+ resource.resourceType
		+ slash
		+ resource.id
		+ "?_format=json";
	var thisMethod = "PUT";

	//Initialize for create.
	if (resource.id == "" || resource.id == null) {
		thisPath = "/gt-fhir-webapp/base"
			+ slash
			+ resource.resourceType
			+ "?_format=json";
		thisMethod = "POST";
	}

  var options = {
      host: "polaris.i3l.gatech.edu",
      port: "8080",
      path: thisPath,
      method: thisMethod,
      headers: {
          "Content-Type": "application/json+fhir",
          "Content-Length": Buffer.byteLength(data)
      }
  };

	var req = http.request(options,
			function (resp) {
				var chunks = [];
				resp.on('data', function (chunk) {
					chunks.push(chunk);
				});
				resp.on('end', function () {
					var text = {
						method: thisMethod,
						statusCode: resp.statusCode,
						headers: resp.headers
					}
					resp.setEncoding('utf8');
					callback(text);
				});
			});

	req.write(data);
	req.end();

};

Fhir.prototype.deleteResource = function deleteResource(resource, callback) {

	var slash = "/";
	var thisPath = "/gt-fhir-webapp/base"
		+ slash
		+ resource.resourceType
		+ slash
		+ resource.id
		+ "?_format=json";
	var thisMethod = "DELETE";

  var options = {
      host: "polaris.i3l.gatech.edu",
      port: "8080",
      path: thisPath,
      method: thisMethod,
      headers: {
          "Content-Type": "application/json+fhir",
          "Content-Length": 0
      }
  };

	var req = http.request(options,
			function (resp) {
				var chunks = [];
				resp.on('data', function (chunk) {
					chunks.push(chunk);
				});
				resp.on('end', function () {
					var text = {
						method: thisMethod,
						statusCode: resp.statusCode,
						headers: resp.headers
					}
					resp.setEncoding('utf8');
					callback(text);
				});
			});

	req.end();

};

module.exports = Fhir;
