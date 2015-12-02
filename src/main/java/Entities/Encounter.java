package Entities;

import org.json.*;

public class Encounter extends Entity {

	private static final String SERVICE_PROVIDER = "serviceProvider";
	private static final String ORGANIZATION = "Organization";
	private static final String SLASH = "/";
	private static final String PATIENT_LOWER = "patient";
	private static final String PATIENT_UPPER = "Patient";
	private static final String LOCATION_LOWER = "location";
	private static final String LOCATION_UPPER = "Location";
	private static final String PERIOD = "period";
	private static final String CLASS = "class";
	private static final String STATUS = "status";

	public Encounter(String resourceType, String id, String status, String clas,
			String patient, Period period, String locationId, String serviceProviderId) {
		super(resourceType, id, null);

		entity.put(STATUS, status);
		entity.put(CLASS, clas);
		entity.put(PATIENT_LOWER, new Reference(PATIENT_UPPER + SLASH + patient).getJSONObject());
		entity.put(PERIOD, period.getJSONObject());
		
		JSONObject locObj = new JSONObject();
		locObj.put(LOCATION_LOWER, new Reference(LOCATION_UPPER + SLASH + locationId).getJSONObject());
		JSONArray locArray = new JSONArray();
		locArray.put(locObj);
		entity.put(LOCATION_LOWER, locArray);
		
		entity.put(SERVICE_PROVIDER, new Reference(ORGANIZATION + SLASH + locationId).getJSONObject());
	}
	
	public Encounter(String json) {
		super(null, null, json);
	}
}
