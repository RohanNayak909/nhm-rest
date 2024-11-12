package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDetailsAPIModel {

	private String countryId;
	private String countryName;
	private String stateId;
	private String stateName;

	public UserDetailsAPIModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDetailsAPIModel(Object countryId, Object countryName, Object stateId, Object stateName) {
		super();
		this.countryId = (String) countryId;
		this.countryName = (String) countryName;
		this.stateId = (String) stateId;
		this.stateName = (String) stateName;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
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
