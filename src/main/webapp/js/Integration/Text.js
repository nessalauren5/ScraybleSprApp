function Text(status, div) {
	this.status = status;
	this.div = div;
};

Text.prototype.prettyPrint = function() {
	return "Status: " + this.status
	+ " Div: " + this.div;
};

Text.prototype.jsonPrint = function() {
	return {	status: this.status,
		div: this.div
	};
};

module.exports = Text;
