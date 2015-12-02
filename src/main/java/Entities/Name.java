package Entities;

import org.json.JSONArray;
import org.json.JSONObject;

public class Name {

	private static final String GIVEN = "given";
	private static final String FAMILY = "family";
	private JSONArray name;

	public Name(String family, String given) {
		
		JSONArray ga = new JSONArray();
		ga.put(given);
		name = new JSONArray();
		JSONObject nameO = new JSONObject();
		nameO.put(FAMILY, family);
		nameO.put(GIVEN, ga);
		name.put(nameO);
	}
	
	public JSONArray getJSONObject() {
		return name;
	}
}
