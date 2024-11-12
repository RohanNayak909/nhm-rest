package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class CureEasyTestListAPIModel {
	private List<DropDownModel> getFrequentTestList;
	private List<DropDownModel> getConditionBasedTest;
	//private List<Object> getTestPackage;
	private List<DropDownModel> getTestPackage;
	private List<DropDownModel> getFeaturedTest;
	private List<DropDownModel> getHomeService;
	

	public CureEasyTestListAPIModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<DropDownModel> getGetFrequentTestList() {
		return getFrequentTestList;
	}
	public void setGetFrequentTestList(List<DropDownModel> getFrequentTestList) {
		this.getFrequentTestList = getFrequentTestList;
	}
	public List<DropDownModel> getGetConditionBasedTest() {
		return getConditionBasedTest;
	}
	public void setGetConditionBasedTest(List<DropDownModel> getConditionBasedTest) {
		this.getConditionBasedTest = getConditionBasedTest;
	}

	public List<DropDownModel> getGetTestPackage() {
		return getTestPackage;
	}
	public void setGetTestPackage(List<DropDownModel> getTestPackage) {
		this.getTestPackage = getTestPackage;
	}
	public List<DropDownModel> getGetFeaturedTest() {
		return getFeaturedTest;
	}
	public void setGetFeaturedTest(List<DropDownModel> getFeaturedTest) {
		this.getFeaturedTest = getFeaturedTest;
	}
	
	
	public List<DropDownModel> getGetHomeService() {
		return getHomeService;
	}
	public void setGetHomeService(List<DropDownModel> getHomeService) {
		this.getHomeService = getHomeService;
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
