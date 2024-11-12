package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CatagoryModel {

	private String catagory;
	private List<String> subCatagoryList;

	public CatagoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public CatagoryModel(Object catagory, Object subCatagoryList) {
		super();
		this.catagory = (String) catagory;
		this.subCatagoryList = (List<String>) subCatagoryList;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public List<String> getSubCatagoryList() {
		return subCatagoryList;
	}

	public void setSubCatagoryList(List<String> subCatagoryList) {
		this.subCatagoryList = subCatagoryList;
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
