package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AyurythmFoodModel {

	private String food_type;
	private String favour_foods;
	private String avoid_foods;

	public AyurythmFoodModel() {
		super();
	}
	
	public AyurythmFoodModel(Object food_type, Object favour_foods, Object avoid_foods) {
		super();
		this.food_type = (String) food_type;
		this.favour_foods = (String) favour_foods;;
		this.avoid_foods = (String) avoid_foods;;
	}

	public String getFood_type() {
		return food_type;
	}

	public void setFood_type(String food_type) {
		this.food_type = food_type;
	}

	public String getFavour_foods() {
		return favour_foods;
	}

	public void setFavour_foods(String favour_foods) {
		this.favour_foods = favour_foods;
	}

	public String getAvoid_foods() {
		return avoid_foods;
	}

	public void setAvoid_foods(String avoid_foods) {
		this.avoid_foods = avoid_foods;
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
