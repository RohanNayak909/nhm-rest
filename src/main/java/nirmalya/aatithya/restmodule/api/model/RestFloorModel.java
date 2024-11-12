package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class RestFloorModel {
	private String floorId;
	private String floorName;
	private List<DropDownModel> floorWiseStatusList;
	public RestFloorModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestFloorModel(Object floorId, Object floorName) {
		super();
		this.floorId = (String)floorId;
		this.floorName = (String)floorName;
	}
	public String getFloorId() {
		return floorId;
	}
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	
	
	
	public List<DropDownModel> getFloorWiseStatusList() {
		return floorWiseStatusList;
	}
	public void setFloorWiseStatusList(List<DropDownModel> floorWiseStatusList) {
		this.floorWiseStatusList = floorWiseStatusList;
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
