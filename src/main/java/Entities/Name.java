package Entities;

import org.json.JSONArray;
import org.json.JSONObject;

public class Name {

	private JSONArray name;

	public Name(String family, String given) {
		
		JSONArray ga = new JSONArray();
		ga.put(given);
		name = new JSONArray();
		JSONObject nameO = new JSONObject();
		nameO.put("family", family);
		nameO.put("given", ga);
		name.put(nameO);
	}
	
	public JSONArray getJSONObject() {
		return name;
	}

//	public JSONArray getGiven() {
//		return given;
//	}
//
//	public void setGiven(JSONArray given) {
//		this.given = given;
//	}
//
//	public JSONArray getFamily() {
//		return family;
//	}
//
//	public void setFamily(JSONArray family) {
//		this.family = family;
//	}
}
