function MedicationPrescription(id, text, status, dateWritten, patientId, practitionerId,
	encounterId, medicationId, dosageInstruction, dispenseId,
	validityPeriod, numberOfRepeatsAllowed, quantity) {
	this.id = id;
	this.text = text;
	this.status = status;
	this.dateWritten = dateWritten;
	this.patientId = patientId;
	this.practitionerId = practitionerId;
	this.encounterId = encounterId;
	this.medicationId = medicationId;
	this.dosageInstruction = dosageInstruction;
	this.dispenseId = dispenseId;
	this.validityPeriod = validityPeriod;
	this.numberOfRepeatsAllowed = numberOfRepeatsAllowed;
	this.quantity = quantity;
};

MedicationPrescription.prototype.prettyPrint = function() {
	return "ID: " + this.id
	+ " Text: "+this.text
	+ " Status: "+this.status
	+ " Date Written: "+this.dateWritten
	+ " Patient ID: "+this.patientId
	+ " Practitioner ID: "+this.practitionerId
	+ " Encounter ID: "+this.encounterId
	+ " Medication ID: "+this.medicationId
	+ " Dosage Instruction: "+this.dosageInstruction
	+ " Dispense ID: "+this.dispenseId
	+ " Validity Period: "+this.validityPeriod
	+ " Number of Repeats Allowed: "+this.numberOfRepeatsAllowed
	+ " Quantity: "+this.quantity;
};

MedicationPrescription.prototype.jsonPrint = function() {
	return {	id: this.id,
				text: this.text,
				status: this.status,
				dateWritten: this.dateWritten,
				patientId: this.patientId,
				practitionerId: this.practitionerId,
				encounterId: this.encounterId,
				medicationId: this.medicationId,
				dosageInstruction: this.dosageInstruction,
				dispenseId: this.dispenseId,
				validityPeriod: this.validityPeriod,
				numberOfRepeatsAllowed: this.numberOfRepeatsAllowed,
				quality: this.quality
			};
};

MedicationPrescription.prototype.getMedicationPrescriptionByText = function getMedicationPrescriptionByText(text) {
	var mp = JSON.parse(text);
	var id = mp.id;
	var text = mp.text.div;
	var status = mp.text.status;
	var dateWritten = mp.dateWritten;
	var patientId = mp.patient.reference.replace("Patient/","");
	var practitionerId = mp.prescriber.reference.replace("Practitioner/","");
	var encounterId = mp.encounter.reference.replace("Encounter/","");
	var medicationId = mp.medication.reference.replace("Medication/","");
	var dosageInstruction = mp.dosageInstruction[0].doseQuantity.value;
	var dispenseId = mp.dispense.medication.reference.replace("Medication/","");
	var validityPeriod =  mp.dispense.validityPeriod.start;
	var numberOfRepeatsAllowed = mp.dispense.numberOfRepeatsAllowed;
	var quantity = mp.dispense.quantity.value;
	return new MedicationPrescription(id, text, status, dateWritten, patientId, practitionerId,
		encounterId, medicationId, dosageInstruction, dispenseId,
		validityPeriod, numberOfRepeatsAllowed, quantity);
};

module.exports = MedicationPrescription;
