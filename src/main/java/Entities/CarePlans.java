package Entities;

import java.util.HashMap;

public class CarePlans extends HashMap<String, CarePlan> {

	private static final long serialVersionUID = 1L;
	private CarePlans() {}
	private static CarePlans carePlans = new CarePlans();
	public static CarePlans GetCarePlans() {
		return carePlans;
	}
}
