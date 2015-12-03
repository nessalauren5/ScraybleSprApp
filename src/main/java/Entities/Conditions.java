package Entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Conditions extends HashMap<String, ArrayList<Condition>> {

	private static final long serialVersionUID = 1L;
	private Conditions() {}
	private static Conditions conditions = new Conditions();
	public static Conditions GetConditions() {
		return conditions;
	}
}
