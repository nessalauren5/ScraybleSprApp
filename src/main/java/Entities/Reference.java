package Entities;

public class Reference {

	private String reference;
	
	public Reference(String reference) {
		this.reference = reference;
	}
	
	public String prettyPrint() {
		return "Reference: " + this.reference;
	};

	public String jsonPrint() {
		return "{ reference: " + this.reference
				+ "}";
	};
}
