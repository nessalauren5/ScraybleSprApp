var Reference = require("./Reference");
var Quantity = require("./Quantity");

function MedicationDispense(resourceType, id, patientId, quantity, daysSupply, medicationId, whenPrepared) {
	this.resourceType = resourceType;
	this.id = id;
	this.patientId = patientId;
	this.quantity = quantity;
	this.daysSupply = daysSupply;
	this.medicationId = medicationId;
	this.whenPrepared = whenPrepared;
};

MedicationDispense.prototype.prettyPrint = function() {
	return "Resource Type: " + this.resourceType
	+ " ID: " + this.id
	+ " Patient ID: "+this.patientId
	+ " Quantity: "+JSON.stringify(this.quantity);
	+ " Days Supply: "+this.daysSupply
	+ " Medication ID: "+this.medicationId
	+ " When Prepared: "+this.whenPrepared;
};

MedicationDispense.prototype.jsonPrint = function() {
	var slash = "/";
	return {	resourceType: this.resourceType,
		id: this.id,
		patient: new Reference("Patient" + slash + this.patientId),
		quantity: this.quantity,
		daysSupply: { value: this.daysSupply },
		medication: new Reference("Medication" + slash + this.medicationId),
		whenPrepared: this.whenPrepared
	};
};

MedicationDispense.prototype.getMedicationDispenseByText = function getMedicationDispenseByText(text) {
	var md = JSON.parse(text);
	var resourceType = md.resourceType;
	var id = md.id;
	var patientId = md.patient.reference.replace("Patient/","");
	var quantity = new Quantity(md.quantity.value, md.quantity.units);
	var daysSupply = md.daysSupply.value;
	var medicationId = md.medication.reference.replace("Medication/","");
	var whenPrepared = md.whenPrepared;
	return new MedicationDispense(resourceType, id, patientId, quantity, daysSupply, medicationId, whenPrepared);
};

module.exports = MedicationDispense;
