package nirmalya.aatithya.restmodule.user.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ActivityAvlFunctionModel {

	private String avlFunctionId;
	private String avlFunction;
	private String avlFuncAttr;
	private String avlFuncValue;
	
	public ActivityAvlFunctionModel() {
		super();
	}

	public ActivityAvlFunctionModel(Object avlFunctionId, Object avlFunction, Object avlFuncAttr, Object avlFuncValue) {
		super();
		this.avlFunctionId = (String) avlFunctionId;
		this.avlFunction = (String) avlFunction;
		this.avlFuncAttr = (String) avlFuncAttr;
		this.avlFuncValue = (String) avlFuncValue;
	}

	public String getAvlFunctionId() {
		return avlFunctionId;
	}

	public void setAvlFunctionId(String avlFunctionId) {
		this.avlFunctionId = avlFunctionId;
	}

	public String getAvlFunction() {
		return avlFunction;
	}

	public void setAvlFunction(String avlFunction) {
		this.avlFunction = avlFunction;
	}

	public String getAvlFuncAttr() {
		return avlFuncAttr;
	}

	public void setAvlFuncAttr(String avlFuncAttr) {
		this.avlFuncAttr = avlFuncAttr;
	}

	public String getAvlFuncValue() {
		return avlFuncValue;
	}

	public void setAvlFuncValue(String avlFuncValue) {
		this.avlFuncValue = avlFuncValue;
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
