package Entities;

import org.json.JSONObject;

public class ValueQuantity {

	private static final String CODE = "code";
	private static final String SYSTEM = "system";
	private static final String UNITS = "units";
	private static final String VALUE = "value";
	
	private JSONObject entity;
	
	public ValueQuantity(double value, String units, String system, String code) {
		entity = new JSONObject();
		entity.put(VALUE, value);
		entity.put(UNITS, units);
		entity.put(SYSTEM, system);
		entity.put(CODE, code);
	}
	public JSONObject getJSONObject() {
		return entity;
	}
}