package Entities;

public class Reference extends Entity {

	private static final String REFERENCE = "reference";

	public Reference(String reference) {
		super(REFERENCE, null, null);
		entity.put(REFERENCE, reference);
	}
}
