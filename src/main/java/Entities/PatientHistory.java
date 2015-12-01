package Entities;

import java.time.LocalTime;

public class PatientHistory extends Entity {
	
	private static final String LAST_HOME_VISIT = "lastHomeVisit";
	private static final String EMERGENCY_CONTACT_RELATION = "emergencyContactRelation";
	private static final String LANGUAGE = "language";
	private static final String HOME_NURSE_AIDE = "homeNurseAide";
	private static final String WAS_HOSPITALIZED = "wasHospitalized";
	private static final String IS_IMMUNIZED = "isImmunized";
	private static final String IS_ALCOHOL = "isAlcohol";
	private static final String IS_TOBACCO = "isTobacco";
	private static final String IS_CAFFEINE = "isCaffeine";
	private static final String WEIGHT = "weight";
	private static final String HEIGHT = "height";
	private static final String EMERGENCY_CONTACT_ADDRESS = "emergencyContactAddress";
	private static final String EMPLOYER_ADDRESS = "employerAddress";
	private static final String EMERGENCY_CONTACT = "emergencyContact";
	private static final String EMPLOYER = "employer";
	
	public PatientHistory(String id) {
		super("PatientHistory", id, null);
		setEmployer("");
		setEmergencyContact("");
		setEmployerAddress("");
		setEmergencyContactAddress("");
		setHeight(0.0);
		setWeight(0.0);
		setCaffeine(false);
		setTobacco(false);
		setAlcohol(false);
		setImmunized(false);
		setWasHospitalized(false);
		setHomeNurseAide("");
		setLanguage("");
		setEmergencyContactRelation("");
		setLastHomeVisit(LocalTime.now().toString());
	}

	public String getEmployer() { return entity.getString(EMPLOYER); }
	public void setEmployer(String employer) { entity.put(EMPLOYER, employer); }

	public String getEmergencyContact() { return entity.getString(EMERGENCY_CONTACT); }
	public void setEmergencyContact(String emergencyContact) { entity.put(EMERGENCY_CONTACT, emergencyContact); }

	public String getEmployerAddress() { return entity.getString(EMPLOYER_ADDRESS); }
	public void setEmployerAddress(String employerAddress) { entity.put(EMPLOYER_ADDRESS, employerAddress); }

	public String getEmergencyContactAddress() { return entity.getString(EMERGENCY_CONTACT_ADDRESS); }
	public void setEmergencyContactAddress(String emergencyContactAddress) { entity.put(EMERGENCY_CONTACT_ADDRESS, emergencyContactAddress); }

	public double getHeight() { return entity.getDouble(HEIGHT); }
	public void setHeight(double height) { entity.put(HEIGHT, height); }

	public double getWeight() { return entity.getDouble(WEIGHT); }
	public void setWeight(double weight) { entity.put(WEIGHT, weight); }

	public boolean isCaffeine() { return entity.getBoolean(IS_CAFFEINE); }
	public void setCaffeine(boolean isCaffeine) { entity.put(IS_CAFFEINE, isCaffeine); }

	public boolean isTobacco() { return entity.getBoolean(IS_TOBACCO); }
	public void setTobacco(boolean isTobacco) { entity.put(IS_TOBACCO, isTobacco); }

	public boolean isAlcohol() { return entity.getBoolean(IS_ALCOHOL); }
	public void setAlcohol(boolean isAlcohol) { entity.put(IS_ALCOHOL, isAlcohol); }

	public boolean isImmunized() { return entity.getBoolean(IS_IMMUNIZED); }
	public void setImmunized(boolean isImmunized) { entity.put(IS_IMMUNIZED, isImmunized); }

	public boolean isWasHospitalized() { return entity.getBoolean(WAS_HOSPITALIZED); }
	public void setWasHospitalized(boolean wasHospitalized) { entity.put(WAS_HOSPITALIZED, wasHospitalized); }

	public String getHomeNurseAide() { return entity.getString(HOME_NURSE_AIDE); }
	public void setHomeNurseAide(String homeNurseAide) { entity.put(HOME_NURSE_AIDE, homeNurseAide); }

	public String getLanguage() { return entity.getString(LANGUAGE); }
	public void setLanguage(String language) { entity.put(LANGUAGE, language); }

	public String getEmergencyContactRelation() { return entity.getString(EMERGENCY_CONTACT_RELATION); }
	public void setEmergencyContactRelation(String emergencyContactRelation) { entity.put(EMERGENCY_CONTACT_RELATION, emergencyContactRelation); }

	public String getLastHomeVisit() { return entity.getString(LAST_HOME_VISIT); }
	public void setLastHomeVisit(String lastHomeVisit) { entity.put(LAST_HOME_VISIT, lastHomeVisit); }
}
