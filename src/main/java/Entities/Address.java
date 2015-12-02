package Entities;

import org.json.*;

public class Address {

	private static final String POSTAL_CODE = "postalCode";
	private static final String STATE = "state";
	private static final String CITY = "city";
	private static final String LINE = "line";
	private static final String USE = "use";
	private JSONObject address;

	public Address(String use, String line, String city, String state, String postalCode) {
		address = new JSONObject();
		address.put(USE, use);
		JSONArray lineArray = new JSONArray();
    	lineArray.put(line);
		address.put(LINE, lineArray);
		address.put(CITY, city);
		address.put(STATE, state);
		address.put(POSTAL_CODE, postalCode);
	}
	
	public JSONObject getJSONObject() {
		return address;
	}
}