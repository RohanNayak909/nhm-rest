package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestModel {
	
	private String id;
	private String appontstatus;

	private String regNo;
	private String appontdt;
	private String apponttime;

	public TestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestModel(Object regNo, Object appontdt, Object apponttime) {
		super();
		this.regNo = (String) regNo;
		this.appontdt = (String) appontdt;
		this.apponttime = (String) apponttime;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppontstatus() {
		return appontstatus;
	}

	public void setAppontstatus(String appontstatus) {
		this.appontstatus = appontstatus;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getAppontdt() {
		return appontdt;
	}

	public void setAppontdt(String appontdt) {
		this.appontdt = appontdt;
	}

	public String getApponttime() {
		return apponttime;
	}

	public void setApponttime(String apponttime) {
		this.apponttime = apponttime;
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
