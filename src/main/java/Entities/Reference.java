package Entities;

import org.json.JSONObject;

public class Reference {

	private static final String REFERENCE = "reference";
	private JSONObject entity;

	public Reference(String reference) {
		entity.put(REFERENCE, reference);
	}
	
	public JSONObject getJSONObject() {
		return entity;
	}
}
