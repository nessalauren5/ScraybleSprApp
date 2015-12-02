function Address(use, line, city, state, postalCode) {
	this.use = use;
	this.line = line;
	this.city = city;
	this.state = state;
	this.postalCode = postalCode;
};

Address.prototype.prettyPrint = function() {
	return "Use: " + this.use
	+ " Street: " + this.line
	+ " City: " + this.city
	+ " State: " + this.state
	+ " Postal Code: " + this.postalCode;
};

Address.prototype.jsonPrint = function() {
	return { use: this.use,
				line: this.line,
				city: this.city,
				state: this.state,
				postalCode: this.postalCode
			};
};

module.exports = Address;
