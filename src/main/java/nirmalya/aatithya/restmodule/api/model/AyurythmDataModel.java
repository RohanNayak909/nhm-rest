package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AyurythmDataModel {

	private String type;
	private String userId;
	private List<SparshnaResultDesc> sparshna_result_desc = new ArrayList<SparshnaResultDesc>();
	SparshnaMasterModel sparshna_master = new SparshnaMasterModel();
	private List<AyurythmFoodModel> food_types = new ArrayList<AyurythmFoodModel>();
	private List<AyurythmHerbModel> herbs = new ArrayList<AyurythmHerbModel>();
	private List<SparshnaActivitiesModel> kriya = new ArrayList<SparshnaActivitiesModel>();
	private List<SparshnaActivitiesModel> mudra = new ArrayList<SparshnaActivitiesModel>();
	private List<SparshnaActivitiesModel> meditation = new ArrayList<SparshnaActivitiesModel>();
	private List<SparshnaActivitiesModel> pranayama = new ArrayList<SparshnaActivitiesModel>();
	private List<SparshnaActivitiesModel> yogasana = new ArrayList<SparshnaActivitiesModel>();

	public AyurythmDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<SparshnaResultDesc> getSparshna_result_desc() {
		return sparshna_result_desc;
	}

	public void setSparshna_result_desc(List<SparshnaResultDesc> sparshna_result_desc) {
		this.sparshna_result_desc = sparshna_result_desc;
	}

	public SparshnaMasterModel getSparshna_master() {
		return sparshna_master;
	}

	public void setSparshna_master(SparshnaMasterModel sparshna_master) {
		this.sparshna_master = sparshna_master;
	}

	public List<AyurythmFoodModel> getFood_types() {
		return food_types;
	}

	public void setFood_types(List<AyurythmFoodModel> food_types) {
		this.food_types = food_types;
	}

	public List<AyurythmHerbModel> getHerbs() {
		return herbs;
	}

	public void setHerbs(List<AyurythmHerbModel> herbs) {
		this.herbs = herbs;
	}

	public List<SparshnaActivitiesModel> getKriya() {
		return kriya;
	}

	public void setKriya(List<SparshnaActivitiesModel> kriya) {
		this.kriya = kriya;
	}

	public List<SparshnaActivitiesModel> getMudra() {
		return mudra;
	}

	public void setMudra(List<SparshnaActivitiesModel> mudra) {
		this.mudra = mudra;
	}

	public List<SparshnaActivitiesModel> getMeditation() {
		return meditation;
	}

	public void setMeditation(List<SparshnaActivitiesModel> meditation) {
		this.meditation = meditation;
	}

	public List<SparshnaActivitiesModel> getPranayama() {
		return pranayama;
	}

	public void setPranayama(List<SparshnaActivitiesModel> pranayama) {
		this.pranayama = pranayama;
	}

	public List<SparshnaActivitiesModel> getYogasana() {
		return yogasana;
	}

	public void setYogasana(List<SparshnaActivitiesModel> yogasana) {
		this.yogasana = yogasana;
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
