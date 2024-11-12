package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIPatientHistoryModel {
	private List<APIPatientMedicalHistoryModel> medicalHistory = new ArrayList<APIPatientMedicalHistoryModel>();
	private List<APIMajorSurgeryModel> majorsurgery = new ArrayList<APIMajorSurgeryModel>();
	private List<APIMedicalConditionModel> medicalCondition = new ArrayList<APIMedicalConditionModel>();

	public APIPatientHistoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<APIPatientMedicalHistoryModel> getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(List<APIPatientMedicalHistoryModel> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public List<APIMajorSurgeryModel> getMajorsurgery() {
		return majorsurgery;
	}

	public void setMajorsurgery(List<APIMajorSurgeryModel> majorsurgery) {
		this.majorsurgery = majorsurgery;
	}

	public List<APIMedicalConditionModel> getMedicalCondition() {
		return medicalCondition;
	}

	public void setMedicalCondition(List<APIMedicalConditionModel> medicalCondition) {
		this.medicalCondition = medicalCondition;
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
