package Entities;

import org.json.*;

public class Patient extends Entity {

	private static final String ACTIVE = "active";
	private static final String BIRTH_DATE = "birthDate";
	private static final String GENDER = "gender";
	private static final String ADDRESS = "address";
	private static final String NAME = "name";
	private static final String TEXT = "text";
	
	private User user;
	private PatientHistory patientHistory;

	public Patient(String resourceType, String id,
			JSONObject text, Name name, Address address,
			String gender, String birthDate, boolean active) {
		
		super(resourceType, id, null);
		
		if(text != null) { super.entity.put(TEXT, text); }
		if(name != null) { super.entity.put(NAME, name.getJSONObject()); }
		if(address != null) { super.entity.put(ADDRESS, address.getJSONObject()); }
		super.entity.put(GENDER, gender);
		super.entity.put(BIRTH_DATE, birthDate);
		super.entity.put(ACTIVE, active);
	}
	
	public Patient(String json) {
		super(null, null, json);	
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void sync(Patient patientFromFHIR) {
		if(patientFromFHIR == null) { return; }
		JSONObject text = patientFromFHIR.getText();
		if(text != null) { super.entity.put(TEXT, text); }
		JSONArray name = patientFromFHIR.getName();
		if(name != null) { super.entity.put(NAME, name); }
		JSONArray address = patientFromFHIR.getAddress();
		if(address != null) { super.entity.put(ADDRESS, address); }
		super.entity.put(GENDER, patientFromFHIR.getGender());
		super.entity.put(BIRTH_DATE, patientFromFHIR.getBirthDate());
		super.entity.put(ACTIVE, patientFromFHIR.isActive());
	}

	public JSONObject getText() { return (JSONObject) super.entity.get(TEXT); }
	public void setText(JSONObject text) { super.entity.put(TEXT, text); }

	public JSONArray getName() { return (JSONArray) super.entity.get(NAME); }
	public void setName(JSONArray name) { super.entity.put(NAME, name); }

	public JSONArray getAddress() { return (JSONArray) super.entity.get(ADDRESS); }
	public void setAddress(JSONArray address) { super.entity.put(ADDRESS, address); }

	public String getGender() { return super.entity.getString(GENDER); }
	public String getBirthDate() { return super.entity.getString(BIRTH_DATE); }
	public boolean isActive() { return super.entity.getBoolean(ACTIVE); }

	public PatientHistory getPatientHistory() {
		return patientHistory;
	}

	public void setPatientHistory(PatientHistory patientHistory) {
		this.patientHistory = patientHistory;
	}
}
