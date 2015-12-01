package Entities;

public class Quantity {

	private double value;
	private String units;

	public Quantity(double value, String units) {
		this.value = value;
		this.units = units;
	}

	public String prettyPrint() {
		return "Value: " + this.value
			+ " Units: " + this.units;
	};

	public String jsonPrint() {
		return "{ value: " + this.value
				+ ", " + "units: " + this.units
				+ "}";
	};
}
