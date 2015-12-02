package Entities;

import org.json.*;

public class Observation extends Entity {

	private static final String SLASH = "/";
	private static final String PATIENT = "Patient";
	private static final String ENCOUNTER = "encounter";
	private static final String SUBJECT = "subject";
	private static final String RELIABILITY = "reliability";
	private static final String STATUS = "status";
	private static final String APPLIES_DATE_TIME = "appliesDateTime";
	private static final String VALUE_QUANTITY = "valueQuantity";
	private static final String CODE = "code";
	private static final String CODING = "coding";

	public Observation(String resourceType, String id, Coding coding, ValueQuantity valueQuantity,
			String appliesDateTime, String status, String reliability, String subject, String encounterId) {
		super(resourceType, id, null);

		JSONObject codeJson = new JSONObject();
		JSONArray codeArray = new JSONArray();
		codeArray.put(coding.getJSONObject());
		codeJson.put(CODING, codeArray);
		entity.put(CODE, codeJson);
		
		entity.put(VALUE_QUANTITY, valueQuantity.getJSONObject());
		entity.put(APPLIES_DATE_TIME, appliesDateTime);
		entity.put(STATUS, status);
		entity.put(RELIABILITY, reliability);
		entity.put(SUBJECT, new Reference(PATIENT + SLASH + subject).getJSONObject());
		entity.put(ENCOUNTER, new Reference(encounterId).getJSONObject());
	}
	
	public Observation(String json) {
		super(null, null, json);
	}
}
