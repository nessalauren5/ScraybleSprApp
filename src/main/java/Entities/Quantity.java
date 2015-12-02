package Entities;

public class Quantity extends Entity {

	private static final String UNITS = "units";
	private static final String VALUE = "value";
	private static final String QUANTITY = "Quantity";

	public Quantity(double value, String units) {
		super(QUANTITY, null, null);
		entity.put(VALUE, value);
		entity.put(UNITS, units);
	}
}