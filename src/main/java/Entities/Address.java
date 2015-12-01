package Entities;

import org.json.*;

public class Address {

	private JSONObject address;
//	private String use;
//	private JSONArray line;
//	private String city;
//	private String state;
//	private String postalCode;

//	public Address(String use, JSONArray line, String city, String state, String postalCode) {
//		address = new JSONObject();
//		address.put("use", use);
//		address.put("line", line);
//		address.put("city", city);
//		address.put("state", state);
//		address.put("postalCode", postalCode);
//		this.use = use;
//		this.line = line;
//		this.city = city;
//		this.state = state;
//		this.postalCode = postalCode;
//	}

	public Address(String use, String line, String city, String state, String postalCode) {
		address = new JSONObject();
		address.put("use", use);
    	JSONArray lineArray = new JSONArray();
    	lineArray.put(line);
		address.put("line", lineArray);
		address.put("city", city);
		address.put("state", state);
		address.put("postalCode", postalCode);
//		this.use = use;
//		this.line = line;
//		this.city = city;
//		this.state = state;
//		this.postalCode = postalCode;
	}
	
	public JSONObject getJSONObject() {
		return address;
	}
	
//	public String prettyPrint() {
//		return "Use: " + this.use
//				+ " Street: " + this.line.toString()
//				+ " City: " + this.city
//				+ " State: " + this.state
//				+ " Postal Code: " + this.postalCode;
//	};

	//public String jsonPrint() {
		//return address.toString();
//		return "{ use: " + this.use
//				+ ", " + "line: " + this.line.toString()
//				+ ", " + "city: " + this.city
//				+ ", " + "state: " + this.state
//				+ ", " + "postalCode: " + this.postalCode
//				+ "}";
	//};
}
