package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NurseAideModel {

	private String nursingId;
	private String name;
	private String age;
	private String gender;
	private String contactNumber;
	private String foodHabit;
	private String familyMemberNo;
	private String occupation;
	private String typeOfResidence;
	private String policeStation;
	private String hospital;
	private String busStop;
	private String address;
	private String fluentCommunication;
	private String aideRest;
	private String foodArrangement;
	private String workHygienic;
	private String toiletsFacilities;
	private String nursingAideProvided;
	private String nursingAideHostility;
	private String drugAbuse;
	private String femaleEmployeeThreat;
	private String stayAvailable;
	private String longDurationWork;
	private String riskWise;
	private String homeserviceOrderId;
	
	
	public  NurseAideModel() {
		super();
	}





	public String getNursingId() {
		return nursingId;
	}





	public void setNursingId(String nursingId) {
		this.nursingId = nursingId;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getAge() {
		return age;
	}





	public void setAge(String age) {
		this.age = age;
	}





	public String getGender() {
		return gender;
	}





	public void setGender(String gender) {
		this.gender = gender;
	}





	public String getContactNumber() {
		return contactNumber;
	}





	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}





	public String getFoodHabit() {
		return foodHabit;
	}





	public void setFoodHabit(String foodHabit) {
		this.foodHabit = foodHabit;
	}





	public String getFamilyMemberNo() {
		return familyMemberNo;
	}





	public void setFamilyMemberNo(String familyMemberNo) {
		this.familyMemberNo = familyMemberNo;
	}





	public String getOccupation() {
		return occupation;
	}





	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}





	public String getTypeOfResidence() {
		return typeOfResidence;
	}





	public void setTypeOfResidence(String typeOfResidence) {
		this.typeOfResidence = typeOfResidence;
	}





	public String getPoliceStation() {
		return policeStation;
	}





	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}





	public String getHospital() {
		return hospital;
	}





	public void setHospital(String hospital) {
		this.hospital = hospital;
	}





	public String getBusStop() {
		return busStop;
	}





	public void setBusStop(String busStop) {
		this.busStop = busStop;
	}





	public String getAddress() {
		return address;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public String getFluentCommunication() {
		return fluentCommunication;
	}





	public void setFluentCommunication(String fluentCommunication) {
		this.fluentCommunication = fluentCommunication;
	}





	public String getAideRest() {
		return aideRest;
	}





	public void setAideRest(String aideRest) {
		this.aideRest = aideRest;
	}





	public String getFoodArrangement() {
		return foodArrangement;
	}





	public void setFoodArrangement(String foodArrangement) {
		this.foodArrangement = foodArrangement;
	}





	public String getWorkHygienic() {
		return workHygienic;
	}





	public void setWorkHygienic(String workHygienic) {
		this.workHygienic = workHygienic;
	}





	public String getToiletsFacilities() {
		return toiletsFacilities;
	}





	public void setToiletsFacilities(String toiletsFacilities) {
		this.toiletsFacilities = toiletsFacilities;
	}





	public String getNursingAideProvided() {
		return nursingAideProvided;
	}





	public void setNursingAideProvided(String nursingAideProvided) {
		this.nursingAideProvided = nursingAideProvided;
	}





	public String getNursingAideHostility() {
		return nursingAideHostility;
	}





	public void setNursingAideHostility(String nursingAideHostility) {
		this.nursingAideHostility = nursingAideHostility;
	}





	public String getDrugAbuse() {
		return drugAbuse;
	}





	public void setDrugAbuse(String drugAbuse) {
		this.drugAbuse = drugAbuse;
	}





	public String getFemaleEmployeeThreat() {
		return femaleEmployeeThreat;
	}





	public void setFemaleEmployeeThreat(String femaleEmployeeThreat) {
		this.femaleEmployeeThreat = femaleEmployeeThreat;
	}





	public String getStayAvailable() {
		return stayAvailable;
	}





	public void setStayAvailable(String stayAvailable) {
		this.stayAvailable = stayAvailable;
	}





	public String getLongDurationWork() {
		return longDurationWork;
	}





	public void setLongDurationWork(String longDurationWork) {
		this.longDurationWork = longDurationWork;
	}


	public String getRiskWise() {
		return riskWise;
	}





	public void setRiskWise(String riskWise) {
		this.riskWise = riskWise;
	}





	public String getHomeserviceOrderId() {
		return homeserviceOrderId;
	}





	public void setHomeserviceOrderId(String homeserviceOrderId) {
		this.homeserviceOrderId = homeserviceOrderId;
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
