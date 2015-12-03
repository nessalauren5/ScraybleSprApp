package Entities;

import org.json.JSONObject;

public class Period {

	private static final String END = "end";
	private static final String START = "start";
	private JSONObject entity;
	
	public Period(String start, String end) {
		entity = new JSONObject();
		entity.put(START, start);
		entity.put(END, end);
	}
	public JSONObject getJSONObject() {
		return entity;
	}
}
