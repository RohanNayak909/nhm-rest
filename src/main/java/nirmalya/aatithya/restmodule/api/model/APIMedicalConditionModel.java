package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIMedicalConditionModel {

	private String diagnosisId;
	private String uid;
	private String description;

	public APIMedicalConditionModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIMedicalConditionModel(Object diagnosisId, Object uid, Object description) {
		super();
		this.diagnosisId = (String) diagnosisId;
		this.uid = (String) uid;
		this.description = (String) description;
	}

	public String getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(String diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
