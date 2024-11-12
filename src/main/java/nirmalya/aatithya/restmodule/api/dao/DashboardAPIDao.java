package nirmalya.aatithya.restmodule.api.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.CureEasyDashboardModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyTestListAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyDoctorConsultationModel;
import nirmalya.aatithya.restmodule.api.model.DashboardAPIModel;
import nirmalya.aatithya.restmodule.api.model.DashboardlabDetails;
import nirmalya.aatithya.restmodule.api.model.RestPatientTestNameModelAPI;
import nirmalya.aatithya.restmodule.api.model.RestPatientTestResultModelAPI;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class DashboardAPIDao {

	@Autowired
	EntityManager em;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(DashboardAPIDao.class);

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DashboardAPIModel>>> testDetailsListDao(String userid, String mob,
			String name) {
		logger.info("Method : testDetailsListDao starts");
		List<DashboardAPIModel> patienttestdetails = new ArrayList<DashboardAPIModel>();
		JsonResponse<List<DashboardAPIModel>> jsonResponse = new JsonResponse<List<DashboardAPIModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("dashboard_get_test_report_api")
					.setParameter("userid", userid).setParameter("mob", mob).setParameter("name", name).getResultList();

			for (Object[] m : x) {

				DashboardAPIModel reqEdit = new DashboardAPIModel(m[0], m[1], m[2]);
				patienttestdetails.add(reqEdit);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (patienttestdetails.isEmpty() == false) {
			List<RestPatientTestNameModelAPI> testgroups = new ArrayList<RestPatientTestNameModelAPI>();
			String patientid = patienttestdetails.get(0).getPatientId();
			try {

				List<Object[]> x1 = em.createNamedStoredProcedureQuery("dashboard_patient_test_names_api")
						.setParameter("userid", patientid).setParameter("mob", mob).setParameter("name", name)
						.getResultList();
				for (Object[] m : x1) {

					RestPatientTestNameModelAPI testGrp = new RestPatientTestNameModelAPI(m[0], m[1]);
					testgroups.add(testGrp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (RestPatientTestNameModelAPI m : testgroups) {
				List<RestPatientTestResultModelAPI> testnames = new ArrayList<RestPatientTestResultModelAPI>();
				String groupname = m.getTestGroupName();
				try {

					List<Object[]> x2 = em.createNamedStoredProcedureQuery("dashboard_patient_test_results_api")
							.setParameter("groupname", groupname).setParameter("userid", patientid)
							.setParameter("mob", mob).setParameter("name", name).getResultList();
					for (Object[] n : x2) {

						RestPatientTestResultModelAPI result = new RestPatientTestResultModelAPI(n[0], n[1], n[2],
								n[3]);
						testnames.add(result);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				m.setTestlist(testnames);
			}
			patienttestdetails.get(0).setTestgroup(testgroups);
			jsonResponse.setBody(patienttestdetails);
		} else {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Data not found");
		}

		if (patienttestdetails.size() > 0) {
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} else {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Data not found");
		}
		ResponseEntity<JsonResponse<List<DashboardAPIModel>>> response = new ResponseEntity<JsonResponse<List<DashboardAPIModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : testDetailsListDao ends");

		return response;
	}

	// Doctor Dashboard list and lab list
	@SuppressWarnings("unchecked")
	public JsonResponse<CureEasyDashboardModel> doctorlist(String userId) {
		logger.info("Method in Dao: doctorlist Dao starts");
		CureEasyDashboardModel cureEasyDashboardModel = new CureEasyDashboardModel();
		// Top Doctor List
		List<CureeazyDoctorConsultationModel> frequenTestList = new ArrayList<CureeazyDoctorConsultationModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_dashboard_doctor_list").getResultList();
			for (Object[] m : x1) {
				CureeazyDoctorConsultationModel cureeazyDoctorConsultationModel = new CureeazyDoctorConsultationModel(
						m[0].toString(), m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], null, m[9], m[10]);
				frequenTestList.add(cureeazyDoctorConsultationModel);
				
				System.out.println("Doctor"+cureeazyDoctorConsultationModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Dashboard Lab Details

		List<DashboardlabDetails> dashboardList = new ArrayList<DashboardlabDetails>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("lab_dashboard_details")
					.setParameter("userid", userId).getResultList();
			for (Object[] m : x1) {
				DashboardlabDetails dashboardlabDetails = new DashboardlabDetails(m[0], m[1], m[2], m[3]);
				dashboardList.add(dashboardlabDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		cureEasyDashboardModel.setGetDashboardDoctorList(frequenTestList);
		cureEasyDashboardModel.setGetDashboardLabList(dashboardList);
		JsonResponse<CureEasyDashboardModel> resp = new JsonResponse<CureEasyDashboardModel>();
		resp.setBody(cureEasyDashboardModel);
		resp.setCode("success");
		resp.setMessage("Data Fetched Successfully");
		logger.info("Method in Dao: labTestList Dao ends");
		return resp;

	}


		@SuppressWarnings("unchecked")
		public JsonResponse<CureEasyTestListAPIModel> getCareHomeandPackageServices() {
			logger.info("Method in Dao: getCareHomeandPackageServices Dao starts");
			CureEasyTestListAPIModel cureEasyTestListAPIModel = new CureEasyTestListAPIModel();
			// Home service
			List<DropDownModel> getmostcarehomeservices = new ArrayList<DropDownModel>();
			try {
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_most_carehomeservices").getResultList();
				for (Object[] m : x1) {
					DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
					getmostcarehomeservices.add(dropDownModel);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Home package
			List<DropDownModel> getmostcarehomepackages = new ArrayList<DropDownModel>();
			try {
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_most_carehomepackages").getResultList();
				for (Object[] m : x1) {
					DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
					getmostcarehomepackages.add(dropDownModel);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			cureEasyTestListAPIModel.setGetHomeService(getmostcarehomeservices);
			cureEasyTestListAPIModel.setGetTestPackage(getmostcarehomepackages);
			JsonResponse<CureEasyTestListAPIModel> resp = new JsonResponse<CureEasyTestListAPIModel>();
			resp.setBody(cureEasyTestListAPIModel);
			resp.setCode("success");
			resp.setMessage("Data Fetched Successfully");
			logger.info("Method in Dao: getCareHomeandPackageServices Dao ends");
			return resp;
		}
}
