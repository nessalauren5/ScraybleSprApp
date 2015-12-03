package Entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Medications extends HashMap<String, ArrayList<Medication>> {

	private static final long serialVersionUID = 1L;
	private Medications() {}
	private static Medications medications = new Medications();
	public static Medications GetMedications() {
		return medications;
	}
}
