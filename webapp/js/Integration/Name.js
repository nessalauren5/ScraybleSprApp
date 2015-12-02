function Name(family, given) {
	this.family = family;
	this.given = given;
};

Name.prototype.prettyPrint = function() {
	return "Given Name: " + this.given
	+ " Family Name: " + this.family;
};

Name.prototype.jsonPrint = function() {
	return { given: this.given,
				family: this.family
			};
};

module.exports = Name;
