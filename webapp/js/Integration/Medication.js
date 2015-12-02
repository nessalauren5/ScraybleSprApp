var Coding = require("./Coding");
var Text = require("./Text");

function Medication(resourceType, id, text, name, code) {
	this.resourceType = resourceType;
	this.id = id;
	this.text = text;
	this.name = name;
	this.code = code;
};

Medication.prototype.prettyPrint = function() {
	return "Resource Type: " + this.resourceType
	+ " ID: " + this.id
	+ " Text: "+JSON.stringify(this.text);
	+ " Name: "+this.name
	+ " Code: "+JSON.stringify(this.code);
};

Medication.prototype.jsonPrint = function() {
	return {	resourceType: this.resourceType,
		id: this.id,
		text: this.text,
		name: this.name,
		code: { coding: [this.code] }
	};
};

Medication.prototype.getMedicationByText = function getMedicationByText(text) {
	var medication = JSON.parse(text);
	var resourceType = medication.resourceType;
	var id = medication.id;
	var name = medication.name;
	var code = new Coding(medication.code.coding[0].system, medication.code.coding[0].code, medication.code.coding[0].display);
	return new Medication(resourceType, id, new Text(medication.text.status, medication.text.div), name, code);
};

module.exports = Medication;
