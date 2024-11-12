package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class IsEContactFDoctorValidModel {

	private Boolean isEContactAdded;
	private Boolean isFDoctorAdded;
	
	public IsEContactFDoctorValidModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IsEContactFDoctorValidModel(Object isEContactAdded, Object isFDoctorAdded) {
		super();
		this.isEContactAdded = (Boolean) isEContactAdded;
		this.isFDoctorAdded = (Boolean) isFDoctorAdded;
	}

	public Boolean getIsEContactAdded() {
		return isEContactAdded;
	}

	public void setIsEContactAdded(Boolean isEContactAdded) {
		this.isEContactAdded = isEContactAdded;
	}

	public Boolean getIsFDoctorAdded() {
		return isFDoctorAdded;
	}

	public void setIsFDoctorAdded(Boolean isFDoctorAdded) {
		this.isFDoctorAdded = isFDoctorAdded;
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
