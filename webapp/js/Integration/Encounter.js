var Reference = require("./Reference");
var Period = require("./Period");

function Encounter(resourceType, id, status, eClass, patientId, periodStart, periodEnd, locationId, serviceProvider) {
	this.resourceType = resourceType;
	this.id = id;
	this.status = status;
	this.class = eClass;
	this.patientId = patientId;
	this.periodStart = periodStart;
	this.periodEnd = periodEnd;
	this.locationId = locationId;
	this.serviceProvider = serviceProvider;
};

Encounter.prototype.prettyPrint = function() {
	return "Resource Type: " + this.resourceType
	+ " ID: " + this.id
	+ " Status: "+this.status
	+ " Class: "+this.class
	+ " Patient ID: "+this.patientId
	+ " Start Time: "+this.periodStart
	+ " End Time: "+this.periodEnd
	+ " Location ID: "+this.locationId
	+ " Service Provider: "+this.serviceProvider;
};

Encounter.prototype.jsonPrint = function() {
	var slash = "/";
	return {	resourceType: this.resourceType,
		id: this.id,
		status: this.status,
		class: this.class,
		patient: new Reference("Patient" + slash + this.patientId),
		period: new Period(this.periodStart, this.periodEnd),
		location: [{ location: new Reference("Location" + slash + this.locationId) }],
		serviceProvider: new Reference("Organization" + slash + this.serviceProvider)
	};
};

Encounter.prototype.getEncounterByText = function getEncounterByText(text) {
	var encounter = JSON.parse(text);
	var resourceType = encounter.resourceType;
	var id = encounter.id;
	var status = encounter.status;
	var eClass = encounter.class;
	var patientId = encounter.patient.reference.replace("Patient/","");
	var periodStart = encounter.period.start;
	var periodEnd = encounter.period.end;
	var locationId = encounter.location[0].location.reference.replace("Location/","");
	var serviceProvider = encounter.serviceProvider.reference.replace("Organization/","");
	return new Encounter(resourceType, id, status, eClass, patientId, periodStart, periodEnd, locationId, serviceProvider);
};

module.exports = Encounter;
