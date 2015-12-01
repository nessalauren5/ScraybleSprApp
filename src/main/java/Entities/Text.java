package Entities;

public class Text {

	private String status;
	private String div;

	public Text(String status, String div) {
		this.status = status;
		this.div = div;
	}

	public String prettyPrint() {
		return "Status: " + this.status
		+ " Div: " + this.div;
	};

	public String jsonPrint() {
		return "{ status: " + this.status
				+ ", " + "div: " + this.div
				+ "}";
	};
}