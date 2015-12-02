function Coding(system, code, display) {
	this.system = system;
	this.code = code;
	this.display = display;
};

Coding.prototype.prettyPrint = function() {
	return "System: " + this.system
	+ " Code: " + this.code
	+ " Display: " + this.display;
};

Coding.prototype.jsonPrint = function() {
	return { system: this.system,
	code: this.code,
	display: this.display };
};

module.exports = Coding;
