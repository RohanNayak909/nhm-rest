package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class APIAdminModel {
	private List<DropDownModel> banner1;
	private List<DropDownModel> banner2;
	private List<DropDownModel> banner3;
	
	public APIAdminModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<DropDownModel> getBanner1() {
		return banner1;
	}

	public void setBanner1(List<DropDownModel> banner1) {
		this.banner1 = banner1;
	}

	public List<DropDownModel> getBanner2() {
		return banner2;
	}

	public void setBanner2(List<DropDownModel> banner2) {
		this.banner2 = banner2;
	}

	public List<DropDownModel> getBanner3() {
		return banner3;
	}

	public void setBanner3(List<DropDownModel> banner3) {
		this.banner3 = banner3;
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
