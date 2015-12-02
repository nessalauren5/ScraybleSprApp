var Reference = require("./Reference");
var ValueQuantity = require("./ValueQuantity");
var Coding = require("./Coding");

function Observation(resourceType, id, code, valueQuantity,
		appliesDateTime, status, reliability, subject, encounter) {
	this.resourceType = resourceType;
	this.id = id;
	this.code = code;
	this.valueQuantity = valueQuantity;
	this.appliesDateTime = appliesDateTime;
	this.status = status;
	this.reliability = reliability;
	this.subject = subject;
	this.encounter = encounter;
};

Observation.prototype.prettyPrint = function() {
	return "Resource Type: " + this.resourceType
	+ " ID: " + this.id
	+ " Code: "+JSON.stringify(this.code);
	+ " Value Quantity: "+JSON.stringify(this.code);
	+ " Applies Date Time: "+this.appliesDateTime
	+ " Status: "+this.status
	+ " Reliability: "+this.reliability
	+ " Patient ID: "+this.subject
	+ " Encounter ID: "+this.encounter;
};

Observation.prototype.jsonPrint = function() {
	var slash = "/";
	return {	resourceType: this.resourceType,
		id: this.id,
		code: { coding: [this.code] },
		valueQuantity: this.valueQuantity,
		appliesDateTime: this.appliesDateTime,
		status: this.status,
		reliability: this.reliability,
		subject: new Reference("Patient" + slash + this.subject),
		encounter: new Reference(this.encounter)
	};
};

Observation.prototype.getObservationByText = function getObservationByText(text) {
	var observation = JSON.parse(text);
	var resourceType = observation.resourceType;
	var id = observation.id;
	var code = new Coding(observation.code.coding[0].system, observation.code.coding[0].code, observation.code.coding[0].display);
	var valueQuantity = new ValueQuantity(observation.valueQuantity.value, observation.valueQuantity.units, observation.valueQuantity.system, observation.valueQuantity.code);
	var appliesDateTime = observation.appliesDateTime;
	var status = observation.status;
	var reliability = observation.reliability;
	var subject = observation.subject.reference.replace("Patient/","");
	var encounter = observation.encounter.reference.replace("Encounter/","");
	return new Observation(resourceType, id, code, valueQuantity, appliesDateTime, status, reliability, subject, encounter);
};

module.exports = Observation;
