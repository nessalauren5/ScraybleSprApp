package Entities;

public class Period {

	private String start;
	private String end;

	public Period(String start, String end) {
		this.start = start;
		this.end = end;
	}

	public String prettyPrint() {
		return "Start: " + this.start
		+ " End: " + this.end;
	};

	public String jsonPrint() {
		return "{ start: " + this.start
				+ ", " + "end: " + this.end
				+ "}";
	};
}
