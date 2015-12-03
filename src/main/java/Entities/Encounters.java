package Entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Encounters extends HashMap<String, ArrayList<Encounter>> {

	private static final long serialVersionUID = 1L;
	private Encounters() {}
	private static Encounters encounters = new Encounters();
	public static Encounters GetEncounters() {
		return encounters;
	}
}
