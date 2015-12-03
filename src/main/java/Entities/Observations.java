package Entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Observations extends HashMap<String, ArrayList<Observation>> {

	private static final long serialVersionUID = 1L;
	private Observations() {}
	private static Observations observations = new Observations();
	public static Observations GetObservations() {
		return observations;
	}
}
