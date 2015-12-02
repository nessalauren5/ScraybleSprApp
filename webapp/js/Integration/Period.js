function Period(start, end) {
	this.start = start;
	this.end = end;
};

Period.prototype.prettyPrint = function() {
	return "Start: " + this.start
	+ " End: " + this.end;
};

Period.prototype.jsonPrint = function() {
	return { start: this.start,
		end: this.end
	};
};

module.exports = Period;
