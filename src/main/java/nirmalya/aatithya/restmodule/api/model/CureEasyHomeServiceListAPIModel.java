package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class CureEasyHomeServiceListAPIModel {
	private List<DropDownModel> getFrequentlyBookedServiceList;
	private List<DropDownModel> getHomecareServiceList;
	private List<DropDownModel> getPPackagesList;
	private List<DropDownModel> getFeaturedServiceList;
	public CureEasyHomeServiceListAPIModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<DropDownModel> getGetFrequentlyBookedServiceList() {
		return getFrequentlyBookedServiceList;
	}
	public void setGetFrequentlyBookedServiceList(List<DropDownModel> getFrequentlyBookedServiceList) {
		this.getFrequentlyBookedServiceList = getFrequentlyBookedServiceList;
	}
	public List<DropDownModel> getGetHomecareServiceList() {
		return getHomecareServiceList;
	}
	public void setGetHomecareServiceList(List<DropDownModel> getHomecareServiceList) {
		this.getHomecareServiceList = getHomecareServiceList;
	}
	public List<DropDownModel> getGetPPackagesList() {
		return getPPackagesList;
	}
	public void setGetPPackagesList(List<DropDownModel> getPPackagesList) {
		this.getPPackagesList = getPPackagesList;
	}
	public List<DropDownModel> getGetFeaturedServiceList() {
		return getFeaturedServiceList;
	}
	public void setGetFeaturedServiceList(List<DropDownModel> getFeaturedServiceList) {
		this.getFeaturedServiceList = getFeaturedServiceList;
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
