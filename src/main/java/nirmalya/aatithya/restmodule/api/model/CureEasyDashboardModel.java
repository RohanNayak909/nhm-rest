package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CureEasyDashboardModel {
	private List<CureeazyDoctorConsultationModel> getDashboardDoctorList;
	private List<DashboardlabDetails> getDashboardLabList;
	
	public CureEasyDashboardModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<CureeazyDoctorConsultationModel> getGetDashboardDoctorList() {
		return getDashboardDoctorList;
	}

	public void setGetDashboardDoctorList(List<CureeazyDoctorConsultationModel> getDashboardDoctorList) {
		this.getDashboardDoctorList = getDashboardDoctorList;
	}
	

	public List<DashboardlabDetails> getGetDashboardLabList() {
		return getDashboardLabList;
	}

	public void setGetDashboardLabList(List<DashboardlabDetails> getDashboardLabList) {
		this.getDashboardLabList = getDashboardLabList;
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
