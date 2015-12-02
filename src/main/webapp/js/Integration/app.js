var express = require('express');
var app = express();
var Fhir = require("./fhir");
var Patient = require("./Patient");
var Encounter = require("./Encounter");
var Observation = require("./Observation");
var Condition = require("./Condition");
var Medication = require("./Medication");
var MedicationDispense = require("./MedicationDispense");
var MedicationPrescription = require("./MedicationPrescription");
var ValueQuantity = require("./ValueQuantity");
var Coding = require("./Coding");
var Text = require("./Text");
var Quantity = require("./Quantity");

app.get('/', function(request, response) {
	var fhir = new Fhir();

	//READ
	//fhir.getResourceById("Patient", "556", function(resp) { response.send(resp.jsonPrint());});
	//fhir.getResourceById("Encounter", "7346", function(resp) { response.send(resp.jsonPrint());});
	//fhir.getResourceById("Observation", "40857", function(resp) { response.send(resp.jsonPrint());});
	//fhir.getResourceById("Condition", "3187", function(resp) { response.send(resp.jsonPrint());});
	//fhir.getResourceById("Medication", "528264", function(resp) { response.send(resp.jsonPrint());});
	//fhir.getResourceById("MedicationDispense", "774", function(resp) { response.send(resp.jsonPrint());});
	//fhir.getResourceById("MedicationPrescription", "2048", function(resp) { response.send(resp.jsonPrint());});

	//DEBUG METHODS
	//fhir.printResourceById("Patient", "556", function(resp) { response.send(resp);});
	//fhir.printResourceById("Encounter", "7346", function(resp) { response.send(resp);});
	//fhir.printResourceById("Observation", "40857", function(resp) { response.send(resp);});
	//fhir.printResourceById("Condition", "3187", function(resp) { response.send(resp);});
	//fhir.printResourceById("Medication", "528264", function(resp) { response.send(resp);});
	//fhir.printResourceById("MedicationDispense", "774", function(resp) { response.send(resp);});
	//fhir.printResourceById("MedicationPrescription", "2048", function(resp) { response.send(resp);});

	//CREATE

	//var entity = new Patient("Patient", "695", {"status":"generated","div":"<div><div class=\"hapiHeaderText\"> Josemaria L <b>DOLORES </b></div><table class=\"hapiPropertyTable\"><tbody><tr><td>Address</td><td><span>1234 Rocky Mountain Drive </span><br /><span>null </span><br /><span>Lincoln </span><span>IL </span></td></tr><tr><td>Date of birth</td><td><span>13 December 1922</span></td></tr></tbody></table></div>"},
	//"Dolores", ["Josemaria","L"], "home", "56 Flea Bottom", "Kings Landing", "IL", "90210", "male", "2015-10-31", true);
	//var entity = new Encounter("Encounter", "", "finished", "emergency", "556", "2015-10-26T20:12:00-04:00", "2015-10-26T20:12:00-04:00", 4, 6);
	//var entity = new Observation("Observation", "",	new Coding("http://loinc.org", "3141-9", "Body weight Measured"),	new ValueQuantity(77.1, "kg", "http://unitsofmeasure.org", "kg"),	"2015-10-26T20:12:00-04:00", "final", "ok", "556", "7346");
	//var entity = new Condition("Condition", "", "556", "7346", "5", new Coding("http://snomed.info/sct", "230265002", "Familial Alzheimer's disease of early onset"),	"Familial Alzheimer's disease of early onset, SNOMED-CT, 230265002", "confirmed", "2002-05-26T00:00:00-04:00");
	//There is no Create or Update Available for Medication object.

	//Create for Medication Dispense does not persist the quantity or the days supply
	//var entity = new MedicationDispense("MedicationDispense", "", "556", new Quantity(30, "mg"), 30, "19080129", "2005-07-24T18:16:00-04:00");
	//fhir.insertOrUpdateResource(entity, function(resp) { response.send(resp);});

	//UPDATE

	//var entity = new Patient("Patient", "699", {"status":"generated","div":"<div><div class=\"hapiHeaderText\"> Josemaria L <b>DOLORES </b></div><table class=\"hapiPropertyTable\"><tbody><tr><td>Address</td><td><span>1234 Rocky Mountain Drive </span><br /><span>null </span><br /><span>Lincoln </span><span>IL </span></td></tr><tr><td>Date of birth</td><td><span>13 December 1922</span></td></tr></tbody></table></div>"},
	//"Dolores", ["Josemaria","L"], "home", "56 Flea Bottom", "Kings Landing", "IL", "90210", "male", "2015-10-31", true);
	//fhir.insertOrUpdateResource(entity, function(resp) { response.send(resp);});

	//There appears to be a bug with Encounter Update in the FHIR Server so this doesn't work.
	//var entity = new Encounter("Encounter", "7489", "finished", "emergency", "556", "2015-11-26T20:12:00-04:00", "2015-11-26T20:12:00-04:00", 4, 6);

	//There appears to be a bug with Observation Update in the FHIR Server so this doesn't work.
	//var entity = new Observation("Observation", "41187",	new Coding("http://loinc.org", "3141-9", "Body weight Measured"),	new ValueQuantity(77.1, "kg", "http://unitsofmeasure.org", "kg"),	"2015-10-26T20:12:00-04:00", "final", "ok", "556", "7346");

	//There appears to be a bug with Observation Update in the FHIR Server so this doesn't work.
	//var entity = new Condition("Condition", "3189", "556", "7346", "5", new Coding("http://snomed.info/sct", "230265002", "Familial Alzheimer's disease of early onset"),	"Familial Alzheimer's disease of early onset, SNOMED-CT, 230265002", "confirmed", "2002-05-26T00:00:00-04:00");

	//There is no Create or Update Available for Medication object.

	//There appears to be a bug with Observation Update in the FHIR Server so this doesn't work.
	//var entity = new MedicationDispense("MedicationDispense", "2891", "556", new Quantity(30, "mg"), 15, "19080129", "2005-07-24T18:16:00-04:00");
	//fhir.insertOrUpdateResource(entity, function(resp) { response.send(resp);});

	//DELETE

	//var entity = new Patient("Patient", "696", {"status":"generated","div":"<div><div class=\"hapiHeaderText\"> Josemaria L <b>DOLORES </b></div><table class=\"hapiPropertyTable\"><tbody><tr><td>Address</td><td><span>1234 Rocky Mountain Drive </span><br /><span>null </span><br /><span>Lincoln </span><span>IL </span></td></tr><tr><td>Date of birth</td><td><span>13 December 1922</span></td></tr></tbody></table></div>"},
	//"Dolores", ["Josemaria","L"], "home", "56 Flea Bottom", "Kings Landing", "IL", "90210", "male", "2015-10-31", true);
	//var entity = new Encounter("Encounter", "7491", "finished", "emergency", "556", "2015-11-26T20:12:00-04:00", "2015-11-26T20:12:00-04:00", 4, 6);
	//var entity = new Observation("Observation", "41188",	new Coding("http://loinc.org", "3141-9", "Body weight Measured"),	new ValueQuantity(77.1, "kg", "http://unitsofmeasure.org", "kg"),	"2015-10-26T20:12:00-04:00", "final", "ok", "556", "7346");
	//var entity = new Condition("Condition", "3190", "556", "7346", "5", new Coding("http://snomed.info/sct", "230265002", "Familial Alzheimer's disease of early onset"),	"Familial Alzheimer's disease of early onset, SNOMED-CT, 230265002", "confirmed", "2002-05-26T00:00:00-04:00");
	//var entity = new Medication("Medication", "528264", new Text("empty", "<div>No narrative available - Error: Error during execution of processor 'ca.uhn.fhir.narrative.BaseThymeleafNarrativeGenerator$NarrativeAttributeProcessor' (medication:4)</div>"), "Immune Globulin (Human) 180 UNT/ML", new Coding("http://www.nlm.nih.gov/research/umls/rxnorm", "797551", "edu.gatech.i3l.fhir.dstu2.entities.MedicationConcept@70890f1b"));
	//var entity = new MedicationDispense("MedicationDispense", "2889", "556", new Quantity(30, "mg"), 15, "19080129", "2005-07-24T18:16:00-04:00");
	//fhir.deleteResource(entity, function(resp) { response.send(resp);});

});
app.listen(3000);
