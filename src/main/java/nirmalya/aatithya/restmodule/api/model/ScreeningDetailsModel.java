package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ScreeningDetailsModel {

	private String pocType;
	private PocResultModel pocResult = new PocResultModel();

	public ScreeningDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ScreeningDetailsModel(Object pocType, Object pocResult) {
		super();
		this.pocType = (String) pocType;
		this.pocResult = (PocResultModel) pocResult;
	}

	public String getPocType() {
		return pocType;
	}

	public void setPocType(String pocType) {
		this.pocType = pocType;
	}

	public PocResultModel getPocResult() {
		return pocResult;
	}

	public void setPocResult(PocResultModel pocResult) {
		this.pocResult = pocResult;
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
