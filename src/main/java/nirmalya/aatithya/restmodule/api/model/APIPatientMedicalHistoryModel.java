package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIPatientMedicalHistoryModel {

	private String drName;
	private String uname;
	private String date;
	private String dnote;
	private String opid;
	private String opName;

	public APIPatientMedicalHistoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIPatientMedicalHistoryModel(Object uname,  Object date,Object drName
			) {
		super();
		this.drName = (String) drName;
		this.uname = (String) uname;
		this.date = (String) date;
	}


	public String getOpid() {
		return opid;
	}

	public void setOpid(String opid) {
		this.opid = opid;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public String getDrName() {
		return drName;
	}

	public void setDrName(String drName) {
		this.drName = drName;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDnote() {
		return dnote;
	}

	public void setDnote(String dnote) {
		this.dnote = dnote;
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
