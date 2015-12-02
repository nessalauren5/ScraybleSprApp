var Reference = require("./Reference");
var Coding = require("./Coding");

function Condition(resourceType, id, patientId, encounterId, practitionerId,
	code, text, clinicalStatus, onsetDateTime) {
	this.resourceType = resourceType;
	this.id = id;
	this.patientId = patientId;
	this.encounterId = encounterId;
	this.practitionerId = practitionerId;
	this.code = code;
	this.text = text;
	this.clinicalStatus = clinicalStatus;
	this.onsetDateTime = onsetDateTime;
};

Condition.prototype.prettyPrint = function() {
	return "Resource Type: " + this.resourceType
	+ " ID: " + this.id
	+ " Patient ID: "+this.patientId
	+ " Encounter ID: "+this.encounterId
	+ " Practitioner ID: "+this.practitionerId
	+ " Code: "+JSON.stringify(this.code);
	+ " Text: "+this.text
	+ " Clinical Status: "+this.clinicalStatus
	+ " Onset Date & Time: "+this.onsetDateTime;
};

Condition.prototype.jsonPrint = function() {
	var slash = "/";
	return {	resourceType: this.resourceType,
		id: this.id,
		patient: new Reference("Patient" + slash + this.patientId),
		encounter: new Reference("Encounter" + slash + this.encounterId),
		asserter: new Reference("Practitioner" + slash + this.practitionerId),
		code: { coding: [this.code],  text: this.text},
		clinicalStatus: this.clinicalStatus,
		onsetDateTime: this.onsetDateTime
	};
};

Condition.prototype.getConditionByText = function getConditionByText(text) {
	var condition = JSON.parse(text);
	var resourceType = condition.resourceType;
	var id = condition.id;
	var patientId = condition.patient.reference.replace("Patient/","");
	var encounterId = condition.encounter.reference.replace("Encounter/","");
	var practitionerId = condition.asserter.reference.replace("Practitioner/","");
	var code = new Coding(condition.code.coding[0].system, condition.code.coding[0].code, condition.code.coding[0].display);
	var clinicalStatus = condition.clinicalStatus;
	var onsetDateTime = condition.onsetDateTime;
	return new Condition(resourceType, id, patientId, encounterId, practitionerId, code, condition.code.text, clinicalStatus, onsetDateTime);
};

module.exports = Condition;
