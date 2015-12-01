package Entities;

import java.util.HashMap;

public class Patients extends HashMap<String, Patient> {

	private static final long serialVersionUID = 1L;
	private Patients() {}
	private static Patients patients = new Patients();
	public static Patients GetPatients() {
		return patients;
	}
}
