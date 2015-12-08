package Entities;

import java.time.LocalDate;

public class CarePlan extends Entity {
	
	private static final String NOTES = "notes";
	private static final String ELECTRO_TREATEMENT = "electroTreatement";
	private static final String HOME_EXERCISE_PROGRAM = "homeExerciseProgram";
	private static final String EVAL_OF_PCP = "evalofPcp";
	private static final String ULTRA_SOUND = "ultraSound";
	private static final String TRANSFER_TRAINING = "transferTraining";
	private static final String MUSCLE_REEDUCATION = "muscleReeducation";
	private static final String CARDIO_TREATMENT = "cardioTreatment";
	private static final String PROSTHETIC_TRAINING = "prostheticTraining";
	private static final String GAIT_TRAINING = "gaitTraining";
	private static final String PT_EVAL_DATE = "ptEvalDate";
	private static final String PT_EVAL = "ptEval";
	private static final String HOME_HEALTH_AIDE = "homeHealthAide";
	private static final String PHYSICIAN = "physician";
	private static final String CARE_PLAN = "CarePlan";

	private Patient patient;
	private LocalDate createDate;
	private LocalDate updateDate;

	public CarePlan(Patient patient) {
		super(CARE_PLAN, CARE_PLAN + patient.getId(), null);
		this.patient = patient;
		LocalDate now = LocalDate.now();
		createDate = now;
		setUpdateDate(now);
		setPhysician("");
		setHomeHealthAide("");
		setPtEval(false);
		setPtEvalDate(now.toString());
		setCardioTreatment(false);
		setProstheticTraining(false);
		setGaitTraining(false);
		setMuscleReeducation(false);
		setTransferTraining(false);
		setUltraSound(false);
		setEvalofPcp(false);
		setHomeExerciseProgram(false);
		setElectroTreatement(false);
		setNotes("");
	}

	public String getPhysician() { return entity.getString(PHYSICIAN); }
	public void setPhysician(String physician) {
		entity.put(PHYSICIAN, physician);
		this.setUpdateDate(LocalDate.now());
	}

	public String getHomeHealthAide() { return entity.getString(HOME_HEALTH_AIDE); }
	public void setHomeHealthAide(String homeHealthAide) {
		entity.put(HOME_HEALTH_AIDE, homeHealthAide);
		this.setUpdateDate(LocalDate.now());
	}

	public boolean isPtEval() {	return entity.getBoolean(PT_EVAL); }
	public void setPtEval(boolean ptEval) {
		entity.put(PT_EVAL, ptEval);
		this.setUpdateDate(LocalDate.now());
	}

	public String getPtEvalDate() {	return entity.getString(PT_EVAL_DATE); }
	public void setPtEvalDate(String ptEvalDate) {
		entity.put(PT_EVAL_DATE, ptEvalDate);
		this.setUpdateDate(LocalDate.now());
	}

	public boolean isGaitTraining() { return entity.getBoolean(GAIT_TRAINING);	}
	public void setGaitTraining(boolean gaitTraining) {
		entity.put(GAIT_TRAINING, gaitTraining);
		this.setUpdateDate(LocalDate.now());
	}

	public boolean isProstheticTraining() { return entity.getBoolean(PROSTHETIC_TRAINING);	}
	public void setProstheticTraining(boolean prostheticTraining) {
		entity.put(PROSTHETIC_TRAINING, prostheticTraining);
		this.setUpdateDate(LocalDate.now());
	}

	public boolean isCardioTreatment() { return entity.getBoolean(CARDIO_TREATMENT); }
	public void setCardioTreatment(boolean cardioTreatment) {
		entity.put(CARDIO_TREATMENT, cardioTreatment);
		this.setUpdateDate(LocalDate.now());
	}

	public boolean isMuscleReeducation() { return entity.getBoolean(MUSCLE_REEDUCATION); }
	public void setMuscleReeducation(boolean muscleReeducation) {
		entity.put(MUSCLE_REEDUCATION, muscleReeducation);
		this.setUpdateDate(LocalDate.now());
	}

	public boolean isTransferTraining() { return entity.getBoolean(TRANSFER_TRAINING); }
	public void setTransferTraining(boolean transferTraining) {
		entity.put(TRANSFER_TRAINING, transferTraining);
		this.setUpdateDate(LocalDate.now());
	}

	public boolean isUltraSound() { return entity.getBoolean(ULTRA_SOUND); }
	public void setUltraSound(boolean ultraSound) {
		entity.put(ULTRA_SOUND, ultraSound);
		this.setUpdateDate(LocalDate.now());
	}

	public boolean isEvalofPcp() { return entity.getBoolean(EVAL_OF_PCP); }
	public void setEvalofPcp(boolean evalofPcp) {
		entity.put(EVAL_OF_PCP, evalofPcp);
		this.setUpdateDate(LocalDate.now());
	}

	public boolean isHomeExerciseProgram() { return entity.getBoolean(HOME_EXERCISE_PROGRAM); }
	public void setHomeExerciseProgram(boolean homeExerciseProgram) {
		entity.put(HOME_EXERCISE_PROGRAM, homeExerciseProgram);
		this.setUpdateDate(LocalDate.now());
	}

	public boolean isElectroTreatement() { return entity.getBoolean(ELECTRO_TREATEMENT); }
	public void setElectroTreatement(boolean electroTreatement) {
		entity.put(ELECTRO_TREATEMENT, electroTreatement);
		this.setUpdateDate(LocalDate.now());
	}

	public String getNotes() { return entity.getString(NOTES); }
	public void setNotes(String notes) {
		entity.put(NOTES, notes);
		this.setUpdateDate(LocalDate.now());
	}

	public Patient getPatient() {
		return patient;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	private void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}
}
