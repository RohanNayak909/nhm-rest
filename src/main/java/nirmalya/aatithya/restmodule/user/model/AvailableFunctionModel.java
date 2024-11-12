package nirmalya.aatithya.restmodule.user.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AvailableFunctionModel {

	private String avlFunction;
	private String perLevel;
	private String perLevelCode;
	private String dataFilter;
	private String dataFilterCode;
	private String roleId;
	private String createdBy;
	private String headingOne;
	private String headingTwo;
	private String headingThree;
	
	public AvailableFunctionModel() {
		super();
	}

	public AvailableFunctionModel(Object avlFunction, Object perLevel, Object perLevelCode, Object dataFilter,
			Object dataFilterCode, Object roleId, Object headingOne, Object headingTwo, Object headingThree) {
		super();
		this.avlFunction = (String) avlFunction;
		this.perLevel = (String) perLevel;
		this.perLevelCode = (String) perLevelCode;
		this.dataFilter = (String) dataFilter;
		this.dataFilterCode = (String) dataFilterCode;
		this.roleId = (String) roleId;
		this.headingOne = (String) headingOne;
		this.headingTwo = (String) headingTwo;
		this.headingThree = (String) headingThree;
	}

	public String getAvlFunction() {
		return avlFunction;
	}

	public void setAvlFunction(String avlFunction) {
		this.avlFunction = avlFunction;
	}

	public String getPerLevel() {
		return perLevel;
	}

	public void setPerLevel(String perLevel) {
		this.perLevel = perLevel;
	}

	public String getPerLevelCode() {
		return perLevelCode;
	}

	public void setPerLevelCode(String perLevelCode) {
		this.perLevelCode = perLevelCode;
	}

	public String getDataFilter() {
		return dataFilter;
	}

	public void setDataFilter(String dataFilter) {
		this.dataFilter = dataFilter;
	}

	public String getDataFilterCode() {
		return dataFilterCode;
	}

	public void setDataFilterCode(String dataFilterCode) {
		this.dataFilterCode = dataFilterCode;
	}
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getHeadingOne() {
		return headingOne;
	}

	public void setHeadingOne(String headingOne) {
		this.headingOne = headingOne;
	}

	public String getHeadingTwo() {
		return headingTwo;
	}

	public void setHeadingTwo(String headingTwo) {
		this.headingTwo = headingTwo;
	}

	public String getHeadingThree() {
		return headingThree;
	}

	public void setHeadingThree(String headingThree) {
		this.headingThree = headingThree;
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
