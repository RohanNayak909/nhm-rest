package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MedicineApiModel {

	
		private String medicineid; 
		private String medicineName; 
		private String quantity;
		private String strength;
		private String duration;
		private String dose;
		public MedicineApiModel() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		public MedicineApiModel(Object medicineid, Object medicineName, Object quantity, Object strength,
				Object duration, Object dose) {
			super();
			this.medicineid = (String)medicineid;
			this.medicineName = (String)medicineName;
			this.quantity = (String)quantity;
			this.strength = (String)strength;
			this.duration = (String)duration;
			this.dose = (String)dose;
		}


		public String getMedicineid() {
			return medicineid;
		}
		public void setMedicineid(String medicineid) {
			this.medicineid = medicineid;
		}
		public String getMedicineName() {
			return medicineName;
		}
		public void setMedicineName(String medicineName) {
			this.medicineName = medicineName;
		}
		public String getQuantity() {
			return quantity;
		}
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}
		public String getStrength() {
			return strength;
		}
		public void setStrength(String strength) {
			this.strength = strength;
		}
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		public String getDose() {
			return dose;
		}
		public void setDose(String dose) {
			this.dose = dose;
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
