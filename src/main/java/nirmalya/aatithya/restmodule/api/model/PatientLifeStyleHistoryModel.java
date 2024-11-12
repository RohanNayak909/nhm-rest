package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientLifeStyleHistoryModel {

	private String patientId;
	private String patientName;
	private String smokingId;
	private String smokingName;
	private String alcoholId;
	private String alcoholName;
	private String diet;
	private String exercise;
	private String occupation;
	private String pets;
	private String petName;

	public PatientLifeStyleHistoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientLifeStyleHistoryModel(Object patientId, Object patientName, Object smokingId, Object smokingName,
			Object alcoholId, Object alcoholName, Object diet, Object exercise, Object occupation, Object pets, Object petName) {
		super();
		this.patientId = (String) patientId;
		this.patientName = (String) patientName;
		this.smokingId = (String) smokingId;
		this.smokingName = (String) smokingName;
		this.alcoholId = (String) alcoholId;
		this.alcoholName = (String) alcoholName;
		this.diet = (String) diet;
		this.exercise = (String) exercise;
		this.occupation = (String) occupation;
		this.pets = (String) pets;
		this.petName = (String) petName;
		
	}

	
	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getSmokingId() {
		return smokingId;
	}

	public void setSmokingId(String smokingId) {
		this.smokingId = smokingId;
	}

	public String getAlcoholId() {
		return alcoholId;
	}

	public void setAlcoholId(String alcoholId) {
		this.alcoholId = alcoholId;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPets() {
		return pets;
	}

	public void setPets(String pets) {
		this.pets = pets;
	}

	public String getSmokingName() {
		return smokingName;
	}

	public void setSmokingName(String smokingName) {
		this.smokingName = smokingName;
	}

	public String getAlcoholName() {
		return alcoholName;
	}

	public void setAlcoholName(String alcoholName) {
		this.alcoholName = alcoholName;
	}

	@Override
	public String toString() {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = mapperObj.writeValueAsString(this);
		} catch (IOException ex) {

			jsonStr = ex.toString();
		}
		return jsonStr;
	}

}
