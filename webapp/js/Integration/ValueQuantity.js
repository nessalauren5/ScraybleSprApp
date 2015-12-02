function ValueQuantity(value, units, system, code) {
	this.value = value;
	this.units = units;
	this.system = system;
	this.code = code;
};

ValueQuantity.prototype.prettyPrint = function() {
	return "Value: " + this.value
	+ " Units: " + this.units
	+ " System: " + this.system
	+ " Code: " + this.code;
};

ValueQuantity.prototype.jsonPrint = function() {
	return { value: this.value,
	units: this.units,
	system: this.system,
	code: this.code };
};

module.exports = ValueQuantity;
