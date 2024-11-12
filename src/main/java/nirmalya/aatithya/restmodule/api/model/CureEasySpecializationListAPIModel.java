package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class CureEasySpecializationListAPIModel {
	private List<DropDownModel> getConsultedSymptomList;
	private List<DropDownModel> getCommonSymptomList;
	private List<DropDownModel> getSpecializationList;
	public CureEasySpecializationListAPIModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<DropDownModel> getGetConsultedSymptomList() {
		return getConsultedSymptomList;
	}
	public void setGetConsultedSymptomList(List<DropDownModel> getConsultedSymptomList) {
		this.getConsultedSymptomList = getConsultedSymptomList;
	}
	public List<DropDownModel> getGetCommonSymptomList() {
		return getCommonSymptomList;
	}
	public void setGetCommonSymptomList(List<DropDownModel> getCommonSymptomList) {
		this.getCommonSymptomList = getCommonSymptomList;
	}
	public List<DropDownModel> getGetSpecializationList() {
		return getSpecializationList;
	}
	public void setGetSpecializationList(List<DropDownModel> getSpecializationList) {
		this.getSpecializationList = getSpecializationList;
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
