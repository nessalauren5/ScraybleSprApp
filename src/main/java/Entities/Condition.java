package Entities;

import org.json.*;

public class Condition extends Entity {

	private static final String TEXT = "text";
	private static final String PRACTITIONER = "Practitioner";
	private static final String ASSERTER = "asserter";
	private static final String ONSET_DATE_TIME = "onsetDateTime";
	private static final String CLINICAL_STATUS = "clinicalStatus";
	private static final String SLASH = "/";
	private static final String PATIENT_LOWER = "patient";
	private static final String PATIENT_UPPER = "Patient";
	private static final String ENCOUNTER_LOWER = "encounter";
	private static final String ENCOUNTER_UPPER = "Encounter";
	private static final String CODE = "code";
	private static final String CODING = "coding";

	public Condition(String resourceType, String id, String patientId,
			String encounterId, String practitionerId,
			Coding coding, String text, String clinicalStatus, String onsetDateTime) {
		super(resourceType, id, null);

		entity.put(PATIENT_LOWER, new Reference(PATIENT_UPPER + SLASH + patientId).getJSONObject());
		entity.put(ENCOUNTER_LOWER, new Reference(ENCOUNTER_UPPER + SLASH + encounterId).getJSONObject());
		entity.put(ASSERTER, new Reference(PRACTITIONER + SLASH + practitionerId).getJSONObject());

		JSONObject codeJson = new JSONObject();
		JSONArray codeArray = new JSONArray();
		codeArray.put(coding.getJSONObject());
		codeJson.put(CODING, codeArray);
		codeJson.put(TEXT, text);
		entity.put(CODE, codeJson);
		
		entity.put(CLINICAL_STATUS, clinicalStatus);
		entity.put(ONSET_DATE_TIME, onsetDateTime);
	}
	
	public Condition(String json) {
		super(null, null, json);
	}
}
