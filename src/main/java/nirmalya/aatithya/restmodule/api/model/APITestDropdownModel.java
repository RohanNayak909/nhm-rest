package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APITestDropdownModel {

	private String key;
	private String name;
	private String code;
	private String groupId;
	private String testNameId;

	public APITestDropdownModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APITestDropdownModel(Object key, Object code,Object name, Object groupId, Object testNameId) {
		super();
		this.key = (String) key;
		this.name = (String) name;
		this.code = (String) code;
		this.groupId = (String) groupId;
		this.testNameId = (String) testNameId;
	}


	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTestNameId() {
		return testNameId;
	}

	public void setTestNameId(String testNameId) {
		this.testNameId = testNameId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
