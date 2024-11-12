package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIMajorSurgeryModel {

	private String uname;
	private String opid;
	private String opName;
	private String date;
	private String dnote;
	private String drName;

	public APIMajorSurgeryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIMajorSurgeryModel(Object uname, Object opid, Object opName, Object date, Object dnote, Object drName) {
		super();
		this.uname = (String) uname;
		this.opid = (String) opid;
		this.opName = (String) opName;
		this.date = (String) date;
		this.dnote = (String) dnote;
		this.drName = (String) drName;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
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

	public String getDrName() {
		return drName;
	}

	public void setDrName(String drName) {
		this.drName = drName;
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
