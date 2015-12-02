package Entities;

public class Text extends Entity {

	private static final String DIV = "div";
	private static final String STATUS = "status";
	private static final String TEXT = "Text";

	public Text(String status, String div) {
		super(TEXT, null, null);
		entity.put(STATUS, status);
		entity.put(DIV, div);
	}
}