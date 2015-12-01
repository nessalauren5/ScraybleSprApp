package Entities;

public class ValueQuantity {
	private double value;
	private String units;
	private String system;
	private String code;

	public ValueQuantity(double value, String units, String system, String code) {
		this.value = value;
		this.units = units;
		this.system = system;
		this.code = code;
	}

	public String prettyPrint() {
		return "Value: " + this.value
				+ " Units: " + this.units
				+ " System: " + this.system
				+ " Code: " + this.code;
	}
			
	public String jsonPrint() {
		return "{ value: " + this.value
			+ ", " + "units: " + this.units
			+ ", " + "system: " + this.system
			+ ", " + "code: " + this.code
			+ "}";
	}
}