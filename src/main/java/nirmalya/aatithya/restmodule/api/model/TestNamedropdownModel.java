package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestNamedropdownModel {
	
	private String testId;
	private String name;
	private String tat;
	private String description;
	public TestNamedropdownModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TestNamedropdownModel(Object testId, Object name, Object tat, Object description) {
		super();
		this.testId = (String) testId;
		this.name = (String) name;
		this.tat = (String) tat;
		this.description = (String) description;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTat() {
		return tat;
	}
	public void setTat(String tat) {
		this.tat = tat;
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
