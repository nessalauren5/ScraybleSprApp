package Scrayble;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;

import Entities.*;
import Fhir.*;

@RestController
public class ScraybleController {
	
	private Users users = Users.GetUsers();
	private Patients patients = Patients.GetPatients();
	private CarePlans carePlans = CarePlans.GetCarePlans();
	private Encounters encounters = Encounters.GetEncounters();
	private Observations observations = Observations.GetObservations();
	private Conditions conditions = Conditions.GetConditions();
	private Medications medications = Medications.GetMedications();
	
	private static Logger log = Logger.getLogger(ScraybleController.class.getName());
	
	private void initializeDummyData() {
		log.info("Initializing dummy data.");
		users.put("Sally", new User("Sally", "Sally Aide", "Sally", "Aide"));
		users.put("Peter", new User("Peter", "Peter Primary", "Peter", "PCP"));
		users.put("Sandra", new User("Sandra", "Sandra Specialist", "Sandra", "Specialist"));
		Patient p = new Patient("Patient", "", null, new Name("Patient", "Bill"),
				new Address("home", "123 Alzheimers Lane", "Atlanta", "GA", "90210"),
				"male", "2015-12-01", true);
		
		String billId = "";
		try {
			billId = GaTechProxy.post(p);
			log.info("Bill ID: " + billId);
				
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		if(billId.equalsIgnoreCase("")) {
        	log.info("Gatech Proxy empty string for billId. It must be down?");
			log.info("Assign a default Bill ID");
			billId = "99999";//5 9s, like Scrayble Uptime :)
			log.info("Bill ID: " + billId);
		}
		
		//Update patient
		p.setId(billId);
		
		//Create Patient History
		PatientHistory ph = new PatientHistory(billId);
		ph.setEmployer("Georgia Tech");
		ph.setEmergencyContact("Mark Braunstein");
		ph.setEmployerAddress("123 Atlanta Drive");
		ph.setEmergencyContactAddress("123 Georgia Drive");
		ph.setHeight(5.5);
		ph.setWeight(172.2);
		ph.setCaffeine(true);
		ph.setTobacco(true);
		ph.setAlcohol(true);
		ph.setImmunized(true);
		ph.setWasHospitalized(true);
		ph.setHomeNurseAide("Sally Aide");
		ph.setLanguage("English");
		ph.setEmergencyContactRelation("Friend");
		ph.setLastHomeVisit("2015-12-01");
		p.setPatientHistory(ph);
		
		//Update Users
		users.put(billId, new User("Bill", "Bill Patient", "Bill", "Patient"));
		
		//Update Patients
		patients.put(billId, p);
		
		//Associate Bill the user with Bill the Patient.
		p.setUser(users.get("Bill"));
		
		//Create Care Plan
		CarePlan cp = new CarePlan(p);//carePlans.get("CarePlan"+billId);
		cp.setPhysician("Peter Primary");
		cp.setHomeHealthAide("Sally Aide");
		cp.setPtEval(true);
		cp.setCardioTreatment(true);
		cp.setUltraSound(true);
		cp.setEvalofPcp(true);
		cp.setHomeExerciseProgram(true);
		cp.setNotes("Bill You're Doing Great!");
		
		//Update Care Plans
		carePlans.put("CarePlan" + billId, cp);
		
		//Create an Observation for Bill
		//Create a Medication for Bill
		//Create an Encounter for Bill
		String encounterId = "";
		//Create a Condition for Bill
		Condition c = new Condition("Condition", null, billId,
				encounterId, "5",
				new Coding("http://snomed.info/sct", "230265002", "Familial Alzheimer's disease of early onset"),
				"Familial Alzheimer's disease of early onset, SNOMED-CT, 230265002",
				"confirmed",
				"2002-05-26T00:00:00-04:00");
		String conditionId = "";
		try {
			conditionId = GaTechProxy.post(c);
			log.info("Alzheimers Condition ID: " + conditionId);
				
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		if(conditionId.equalsIgnoreCase("")) {
        	log.info("Gatech Proxy empty string for conditionId. It must be down?");
			log.info("Assign a default Condition");
			conditionId = "99999";//5 9s, like Scrayble Uptime :)
			log.info("Condition ID: " + conditionId);
		}
		
		//Update Condition
		c.setId(conditionId);

		//Update Conditions
		ArrayList<Condition> conds = new ArrayList<Condition>();
		conds.add(c);
		conditions.put(billId, conds);
	}

	public ScraybleController() {
		initializeDummyData();
	}

	@CrossOrigin
	@RequestMapping(value = "/user/login", method=RequestMethod.GET)
	public String userLogin() {
		log.info("GET RequestMapping: /user/login");
    	return "User Login";
    }

	@CrossOrigin
    @RequestMapping(value = "/user/logout", method=RequestMethod.GET)
	public String userLogout() {
		log.info("GET RequestMapping: /user/logout");
    	return "User Logout";
    }

	@CrossOrigin
    @RequestMapping(value = "/user/{Id}", method=RequestMethod.GET)
	public String getUserById(@PathVariable("Id") String id) {
		log.info("GET RequestMapping: /user/"+id);
    	User u = users.get(id);
    	if(u != null) {
        	return u.getJSONObject().toString();
    	}
    	return "{}";
    }

	@CrossOrigin
    @RequestMapping(value = "/user/{Id}", method=RequestMethod.POST)
	public String updateUserById(@PathVariable("Id") String id) {
		log.info("POST RequestMapping: /user/"+id);
    	return "Update User By User ID";
    }

	@CrossOrigin
    @RequestMapping(value = "/user/{Id}", method=RequestMethod.DELETE)
	public String deleteUserByName(@PathVariable("Id") String id) {
		log.info("DELETE RequestMapping: /user/"+id);
    	return "Delete User By User ID";
    }

	@CrossOrigin
    @RequestMapping(value = "/Condition/{Id}", method=RequestMethod.GET)
	public String getCondition(@PathVariable("Id") String id) {
		log.info("GET RequestMapping: /Condition/"+id);
    	Condition conditionFromFHIR = null;
    	try {
    		String json = GaTechProxy.get("Condition", id);
    		if(!json.equalsIgnoreCase("")) {
            	log.info("Gatech Proxy returned this JSON: " + json);
            	conditionFromFHIR = new Condition(json);
            	log.info("Condition FHIR object created.");
    		} else {
            	log.info("Gatech Proxy returned this empty string JSON. It must be down?");
    		}
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
    	
       	if(conditionFromFHIR != null) {
    		return conditionFromFHIR.getJSONObject().toString();
       	}
		return "{}";
    }

	@CrossOrigin
    @RequestMapping(value = "/Encounter/{Id}", method=RequestMethod.GET)
	public String getEncounter(@PathVariable("Id") String id) {
		log.info("GET RequestMapping: /Encounter/"+id);
    	Encounter encounterFromFHIR = null;
    	try {
    		String json = GaTechProxy.get("Encounter", id);
    		if(!json.equalsIgnoreCase("")) {
            	log.info("Gatech Proxy returned this JSON: " + json);
            	encounterFromFHIR = new Encounter(json);
            	log.info("Encounter FHIR object created.");
    		} else {
            	log.info("Gatech Proxy returned this empty string JSON. It must be down?");
    		}
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
    	
       	if(encounterFromFHIR != null) {
    		return encounterFromFHIR.getJSONObject().toString();
       	}
		return "{}";
    }

	@CrossOrigin
    @RequestMapping(value = "/Medication/{Id}", method=RequestMethod.GET)
	public String getMedication(@PathVariable("Id") String id) {
		log.info("GET RequestMapping: /Medication/"+id);
    	return "Medication: " + id;
    }

	@CrossOrigin
    @RequestMapping(value = "/MedicationDispense/{Id}", method=RequestMethod.GET)
	public String getMedicationDispense(@PathVariable("Id") String id) {
		log.info("GET RequestMapping: /MedicationDispense/"+id);
    	return "MedicationDispense: " + id;
    }

	@CrossOrigin
    @RequestMapping(value = "/MedicationPrescription/{Id}", method=RequestMethod.GET)
	public String getMedicationPrescription(@PathVariable("Id") String id) {
    	log.info("GET RequestMapping: /MedicationPrescription/"+id);
    	return "MedicationPrescription: " + id;
    }

	@CrossOrigin
    @RequestMapping(value = "/Observation/{Id}", method=RequestMethod.GET)
	public String getObservation(@PathVariable("Id") String id) {
    	log.info("GET RequestMapping: /Observation/"+id);
    	Observation observationFromFHIR = null;
    	try {
    		String json = GaTechProxy.get("Observation", id);
    		if(!json.equalsIgnoreCase("")) {
            	log.info("Gatech Proxy returned this JSON: " + json);
            	observationFromFHIR = new Observation(json);
            	log.info("Observation FHIR object created.");
    		} else {
            	log.info("Gatech Proxy returned this empty string JSON. It must be down?");
    		}
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
    	
       	if(observationFromFHIR != null) {
    		return observationFromFHIR.getJSONObject().toString();
       	}
		return "{}";
    }

	@CrossOrigin
    @RequestMapping(value = "/Patient/{Id}", method=RequestMethod.GET)
	public String getPatient(@PathVariable("Id") String id) {
    	log.info("GET RequestMapping: /Patient/"+id);
    	Patient patientFromFHIR = null;
    	try {
    		String json = GaTechProxy.get("Patient", id);
    		if(!json.equalsIgnoreCase("")) {
            	log.info("Gatech Proxy returned this JSON: " + json);
            	patientFromFHIR = new Patient(json);
            	log.info("Patient FHIR object created.");
    		} else {
            	log.info("Gatech Proxy returned this empty string JSON. It must be down?");
    		}
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
    	
    	Patient patientFromLocal = patients.get(id);
    	log.info("Patient Scrayble object created.");
    	if(patientFromLocal != null) {
    		patientFromLocal.sync(patientFromFHIR);
    		log.info("Patient Scrayble & FHIR synced.");
        	return patientFromLocal.getJSONObject().toString();
    	}
		log.info("Patient Scrayble & FHIR NOT synced.");
       	if(patientFromFHIR != null) {
    		return patientFromFHIR.getJSONObject().toString();
       	}
		return "{}";
    }

	@CrossOrigin
    @RequestMapping(value = "/PatientHistory/{Id}", method=RequestMethod.GET)
	public String getPatientHistory(@PathVariable("Id") String id) {
    	log.info("GET RequestMapping: /PatientHistory/"+id);
    	Patient patientFromLocal = patients.get(id);
    	if(patientFromLocal != null) {
        	return patientFromLocal.getPatientHistory().getJSONObject().toString();
    	}
       	return "{}";
    }

	@CrossOrigin
    @RequestMapping(value = "/Patient/{Id}/CarePlan", method=RequestMethod.GET)
	public String getPatientCarePlan(@PathVariable("Id") String id) {
    	log.info("GET RequestMapping: /CarePlan/"+id);
    	CarePlan cp = carePlans.get("CarePlan"+id);
    	if(cp != null) {
        	return cp.getJSONObject().toString();
    	}
    	return "{}";
    }

	@CrossOrigin
    @RequestMapping(value = "/Patient/{Id}/Conditions", method=RequestMethod.GET)
	public String getPatientConditions(@PathVariable("Id") String id) {
    	log.info("GET RequestMapping: /Patient/"+id+"/Conditions");
    	ArrayList<Condition> conds = conditions.get(id);
    	StringBuilder sb = new StringBuilder();
    	for (Condition cond :conds) {
    		sb.append(cond.getJSONObject().toString()).append(" ");
    	}
    	if(sb.toString().equalsIgnoreCase("")) {
    		return "{}";	
    	} else {
    		return sb.toString();
    	}
    }
	
	@CrossOrigin
    @RequestMapping(value = "/Patient", method=RequestMethod.POST)
	public @ResponseBody String createPatientHistory(Patient p) {
    	log.info("POST RequestMapping: /Patient for " + p.getId());
    	String id = GaTechProxy.post(p);
    	return id;
    }
    
	@CrossOrigin
    @RequestMapping(value = "/PatientHistory", method=RequestMethod.POST)
	public @ResponseBody PatientHistory createPatientHistory(PatientHistory ph, int patientId) {
    	log.info("POST RequestMapping: /PatientHistory for " + patientId);
		Patient p = patients.get(Integer.toString(patientId));
		if (p != null) {
			p.setPatientHistory(ph);
		}
		else{
			p = new Patient(null,Integer.toString(patientId),null,null,null,null,null,true);
			p.setPatientHistory(ph);
		}
		patients.put(Integer.toString(patientId), p);
    	return ph;
    }
    
	@CrossOrigin
    @RequestMapping(value = "/CarePlan", method=RequestMethod.POST)
	public @ResponseBody CarePlan createPatient(CarePlan cp, int patientId) {
    	log.info("POST RequestMapping: /CarePlan for " + patientId);
		carePlans.put(Integer.toString(patientId), cp);
    	return cp;
    }
 }