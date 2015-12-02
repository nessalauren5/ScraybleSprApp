package Scrayble;

import org.springframework.web.bind.annotation.RestController;
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
	private static Logger log = Logger.getLogger(ScraybleController.class.getName());
	
	private void initializeDummyData() {
		log.info("Initializing dummy data.");
		users.put("Sally", new User("Sally", "Sally Aide", "Sally", "Aide"));
		users.put("Peter", new User("Peter", "Peter Primary", "Peter", "PCP"));
		users.put("Sandra", new User("Sandra", "Sandra Specialist", "Sandra", "Specialist"));
		Patient p = new Patient("Patient", "", null, new Name("Patient", "Bill"),
				new Address("home", "123 Alzheimers Lane", "Atlanta", "GA", "90210"),
				"male", "2015-12-01", true);
		String billId = GaTechProxy.post(p);
		p.setId(billId);
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
		System.out.println("Bill ID: " + billId);
		users.put(billId, new User("Bill", "Bill Patient", "Bill", "Patient"));
		patients.put(billId, p);
		
		//Associate Bill the user with Bill the Patient.
		p.setUser(users.get("Bill"));
		
		//Associate Bill with his Care Plan.
		CarePlan cp = new CarePlan(p);//carePlans.get("CarePlan"+billId);
		cp.setPhysician("Peter Primary");
		cp.setHomeHealthAide("Sally Aide");
		cp.setPtEval(true);
		cp.setCardioTreatment(true);
		cp.setUltraSound(true);
		cp.setEvalofPcp(true);
		cp.setHomeExerciseProgram(true);
		cp.setNotes("Bill You're Doing Great!");
		
		carePlans.put("CarePlan" + billId, cp);
	}

	public ScraybleController() {
		initializeDummyData();
	}

	@RequestMapping(value = "/user/login", method=RequestMethod.GET)
	public String userLogin() {
		log.info("GET RequestMapping: /user/login");
    	return "User Login";
    }

    @RequestMapping(value = "/user/logout", method=RequestMethod.GET)
	public String userLogout() {
		log.info("GET RequestMapping: /user/logout");
    	return "User Logout";
    }

    @RequestMapping(value = "/user/{Id}", method=RequestMethod.GET)
	public String getUserById(@PathVariable("Id") String id) {
		log.info("GET RequestMapping: /user/"+id);
    	User u = users.get(id);
    	if(u != null) {
        	return u.getJSONObject().toString();
    	}
    	return "{}";
    }

    @RequestMapping(value = "/user/{Id}", method=RequestMethod.POST)
	public String updateUserById(@PathVariable("Id") String id) {
		log.info("POST RequestMapping: /user/"+id);
    	return "Update User By User ID";
    }

    @RequestMapping(value = "/user/{Id}", method=RequestMethod.DELETE)
	public String deleteUserByName(@PathVariable("Id") String id) {
		log.info("DELETE RequestMapping: /user/"+id);
    	return "Delete User By User ID";
    }

    @RequestMapping(value = "/Condition/{Id}", method=RequestMethod.GET)
	public String getCondition(@PathVariable("Id") String id) {
		log.info("GET RequestMapping: /Condition/"+id);
    	return "Condition: " + id;
    }

    @RequestMapping(value = "/Encounter/{Id}", method=RequestMethod.GET)
	public String getEncounter(@PathVariable("Id") String id) {
		log.info("GET RequestMapping: /Encounter/"+id);
    	return "Encounter: " + id;
    }

    @RequestMapping(value = "/Medication/{Id}", method=RequestMethod.GET)
	public String getMedication(@PathVariable("Id") String id) {
		log.info("GET RequestMapping: /Medication/"+id);
    	return "Medication: " + id;
    }

    @RequestMapping(value = "/MedicationDispense/{Id}", method=RequestMethod.GET)
	public String getMedicationDispense(@PathVariable("Id") String id) {
		log.info("GET RequestMapping: /MedicationDispense/"+id);
    	return "MedicationDispense: " + id;
    }

    @RequestMapping(value = "/MedicationPrescription/{Id}", method=RequestMethod.GET)
	public String getMedicationPrescription(@PathVariable("Id") String id) {
    	log.info("GET RequestMapping: /MedicationPrescription/"+id);
    	return "MedicationPrescription: " + id;
    }

    @RequestMapping(value = "/Observation/{Id}", method=RequestMethod.GET)
	public String getObservation(@PathVariable("Id") String id) {
    	log.info("GET RequestMapping: /Observation/"+id);
    	return "Observation: " + id;
    }

    @RequestMapping(value = "/Patient/{Id}", method=RequestMethod.GET)
	public String getPatient(@PathVariable("Id") String id) {
    	log.info("GET RequestMapping: /Patient/"+id);
    	String json = GaTechProxy.get("Patient", id);
    	log.debug("Gatech Proxy returned this JSON: " + json);
    	Patient patientFromFHIR = new Patient(json);
    	log.info("Patient FHIR object created.");
    	Patient patientFromLocal = patients.get(id);
    	log.info("Patient Scrayble object created.");
    	if(patientFromLocal != null) {
    		patientFromLocal.sync(patientFromFHIR);
    		log.info("Patient Scrayble & FHIR synced.");
        	return patientFromLocal.getJSONObject().toString();
    	}
		log.info("Patient Scrayble & FHIR NOT synced.");
       	return patientFromFHIR.getJSONObject().toString();
    }

    @RequestMapping(value = "/PatientHistory/{Id}", method=RequestMethod.GET)
	public String getPatientHistory(@PathVariable("Id") String id) {
    	log.info("GET RequestMapping: /PatientHistory/"+id);
    	Patient patientFromLocal = patients.get(id);
    	if(patientFromLocal != null) {
        	return patientFromLocal.getPatientHistory().getJSONObject().toString();
    	}
       	return "{}";
    }

    @RequestMapping(value = "/Patient/{Id}/CarePlan", method=RequestMethod.GET)
	public String getPatientCarePlan(@PathVariable("Id") String id) {
    	log.info("GET RequestMapping: /CarePlan/"+id);
    	CarePlan cp = carePlans.get("CarePlan"+id);
    	if(cp != null) {
        	return cp.getJSONObject().toString();
    	}
    	return "{}";
    }

    @RequestMapping(value = "/PatientHistory", method=RequestMethod.POST)
	public @ResponseBody PatientHistory createPatientHistory(PatientHistory ph, int patientId) {
    	log.info("POST RequestMapping: /PatientHistory");
    	return ph;
    }
    
    @RequestMapping(value = "/CarePlan", method=RequestMethod.POST)
	public @ResponseBody CarePlan createPatient(CarePlan cp, int patientId) {
    	log.info("POST RequestMapping: /CarePlan");
    	return cp;
    }
 }