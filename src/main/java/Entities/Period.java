package Entities;

public class Period extends Entity {

	private static final String END = "end";
	private static final String START = "start";
	private static final String PERIOD = "Period";

	public Period(String start, String end) {
		super(PERIOD, null, null);
		entity.put(START, start);
		entity.put(END, end);
	}
}
