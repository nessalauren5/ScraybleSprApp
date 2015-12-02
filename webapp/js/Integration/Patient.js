var Name = require("./Name");
var Address = require("./Address");

function Patient(resourceType, id, text, familyName, givenName, addrUse, addrLine, addrCity, addrState, addrPO, gender, birthDate, active) {
	this.resourceType = resourceType;
	this.id = id;
	this.text = text;
	this.name = new Name(familyName, givenName);
	this.address = new Address(addrUse, addrLine, addrCity, addrState, addrPO);
	this.gender = gender;
	this.birthDate = birthDate;
	this.active = active;
};

Patient.prototype.prettyPrint = function() {
	return "Resource Type: " + this.resourceType
	+ " ID: " + this.id
	+ " Text: " +  JSON.stringify(this.text)
	+ " Given Name: " + this.name.given
	+ " Family Name: " + this.name.family
	+ " Address: " + this.address.use+" "+this.address.line[0].trim()+" "+this.address.city+" "+this.address.state+" "+this.address.postalCode
	+ " Gender: " + this.gender
	+ " Birth Date: " + this.birthDate
	+ " Active: " + this.active;
};

Patient.prototype.jsonPrint = function() {
	var output = { resourceType: this.resourceType,
				id: this.id,
				text: this.text,
				name: [this.name],
				gender: this.gender,
				birthDate: this.birthDate,
				address: [this.address],
				active: this.active
			};
	if (this.id == "" || this.id == null) {
		output = { resourceType: this.resourceType,
					text: this.text,
					name: [this.name],
					gender: this.gender,
					birthDate: this.birthDate,
					address: [this.address],
					active: this.active
				};
	}
	return output;
};

Patient.prototype.getPatientByText = function getPatientByText(text) {
    var patient = JSON.parse(text);
		var resourceType = patient.resourceType;
		var id = patient.id;
		var text = patient.text;
		var name = patient.name[0];
	  var address = patient.address[0];
		var active = patient.active;
	  return new Patient(resourceType, id, text, [name.family], name.given,
			address.use, [address.line], address.city, address.state, address.postalCode,
			patient.gender, patient.birthDate, active);
};

module.exports = Patient;
