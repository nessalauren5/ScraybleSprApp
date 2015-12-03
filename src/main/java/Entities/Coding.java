package Entities;

import org.json.JSONObject;

public class Coding {
	
	private static final String SYSTEM = "system";
	private static final String CODE = "code";
	private static final String DISPLAY = "display";
	private JSONObject entity;
	
	public Coding(String system, String code, String display) {
		entity = new JSONObject();
		entity.put(SYSTEM, system);
		entity.put(CODE, code);
		entity.put(DISPLAY, display);
	}
	
	public JSONObject getJSONObject() {
		return entity;
	}
}
