function Quantity(value, units) {
	this.value = value;
	this.units = units;
};

Quantity.prototype.prettyPrint = function() {
	return "Value: " + this.value
	+ " Units: " + this.units;
};

Quantity.prototype.jsonPrint = function() {
	return { value: this.value,
	units: this.units
	};
};

module.exports = Quantity;
