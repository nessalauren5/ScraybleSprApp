function Reference(reference) {
	this.reference = reference;
};

Reference.prototype.prettyPrint = function() {
	return "Reference: " + this.reference;
};

Reference.prototype.jsonPrint = function() {
	return { reference: this.reference };
};

module.exports = Reference;
