package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIAiplMasterNadiModel {

	APIAiplNadiModel ehsJsonReport = new APIAiplNadiModel();
	
	public APIAiplMasterNadiModel() {
		super();
	}

	public APIAiplNadiModel getEhsJsonReport() {
		return ehsJsonReport;
	}

	public void setEhsJsonReport(APIAiplNadiModel ehsJsonReport) {
		this.ehsJsonReport = ehsJsonReport;
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
