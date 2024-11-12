package nirmalya.aatithya.restmodule.user.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.DmDashboardRestModel;
import nirmalya.aatithya.restmodule.user.model.RestCmDashboardModel;

@Repository
public class CmDashboardDao {
	
	Logger logger = LoggerFactory.getLogger(CmDashboardDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@SuppressWarnings("unchecked")
	public List<RestCmDashboardModel> cmDashboardGetIdd(String id) {
		logger.info("Method : getCountryLists Dao starts");
		System.out.println(id);
		List<RestCmDashboardModel> cmDashboardGetIdd = new ArrayList<RestCmDashboardModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_get_idd").setParameter("cmid", id).getResultList();

			for (Object[] m : x) {

				RestCmDashboardModel dropDownModel = new RestCmDashboardModel(m[0].toString(), m[1], m[2],m[3],null,null,null,null);
				cmDashboardGetIdd.add(dropDownModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
//System.out.println("dmDashboardGetIdd========="+dmDashboardGetIdd);
		logger.info("Method : getCountryLists Dao ends");

		return cmDashboardGetIdd;
	}
	/*
	Dashboard  count list
	
	*/
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestCmDashboardModel>> viewcmDashboardCount( String stateId) {
		logger.info("Method : viewcmDashboardCount starts");

		RestCmDashboardModel req = new RestCmDashboardModel();
		JsonResponse<RestCmDashboardModel> resp = new JsonResponse<RestCmDashboardModel>();
		System.out.println("###"+stateId);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_view_count").
					setParameter("stateId", stateId).getResultList();

			for (Object[] m : x) {
				RestCmDashboardModel reqemp = new RestCmDashboardModel(null, m[0],m[1],m[2], m[3], m[4], null,null,
						null);
				System.out.println("reqemp========="+reqemp);	
				req = reqemp;
			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestCmDashboardModel>> response = new ResponseEntity<JsonResponse<RestCmDashboardModel>>(
				resp, responseHeaders, HttpStatus.CREATED);
		
		logger.info("Method : viewcmDashboardCount ends")	;
		return response;
	}
	/*
	 * Total registration details
	 *
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestCmDashboardModel>> viewcmDashboardTotalRegdDao(String stateId, String typeId) {
		logger.info("Method : viewcmDashboardTotalRegdDao starts");

		List<RestCmDashboardModel> req = new ArrayList<RestCmDashboardModel>();
		
		JsonResponse<List<RestCmDashboardModel>> resp = new JsonResponse<List<RestCmDashboardModel>>();
		System.out.println(stateId+typeId);
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_view_patientDetails")
			.setParameter("stateId", stateId).setParameter("typeId", typeId).getResultList();

			for (Object[] m : x) {
				
				/*Object regDt = null;
				if (m[4] != null) {
					regDt =DateFormatter.returnStringDate(m[4]);
				}*/
				RestCmDashboardModel reqemp = new RestCmDashboardModel(m[0],m[1],m[2], m[3],m[4].toString(), m[5]);
				req.add(reqemp);
				resp.setBody(req);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : viewcmDashboardTotalRegdDao ends")	;
		return resp;
	}
	// Age Wise
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestCmDashboardModel>> viewcmDashboardAgeWiseTotalTestDao(String stateId) {
		logger.info("Method : viewcmDashboardAgeWiseTotalTestDao starts");

		RestCmDashboardModel req = new RestCmDashboardModel();
		JsonResponse<RestCmDashboardModel> resp = new JsonResponse<RestCmDashboardModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_view_agewisetotaltest")
					.setParameter("stateId", stateId).getResultList();

			for (Object[] m : x) {
				RestCmDashboardModel reqemp = new RestCmDashboardModel(m[0].toString(),m[1].toString(),m[2].toString(), m[3].toString());
				
				req = reqemp;
				System.out.println("req"+req);
			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestCmDashboardModel>> response = new ResponseEntity<JsonResponse<RestCmDashboardModel>>(
				resp, responseHeaders, HttpStatus.CREATED);
		//System.out.println("age wise total test========="+response);
		
		logger.info("Method : viewdmDashboardAgeWiseTotalTestDao ends")	;
		return response;
	}
	// Area Wise
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> viewcmDashboardAreaWiseTotalTestDao(String stateId) {
		logger.info("Method : viewcmDashboardAreaWiseTotalTestDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_view_areawisetotaltest")
					.setParameter("stateId", stateId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("area wise total test========="+resp);
		logger.info("Method : viewcmDashboardAreaWiseTotalTestDao ends");
		return resp;	
	}
	// disease wise total high risk
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> viewcmDashboardDiseaseWiseTotalHighRiskDao(String stateId) {
		logger.info("Method : viewcmDashboardDiseaseWiseTotalHighRiskDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_view_diseasewisetotalhighrisk")
				.setParameter("stateId", stateId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("disease wise total high risk========="+resp);
		logger.info("Method : viewcmDashboardDiseaseWiseTotalHighRiskDao ends");
		return resp;
	}
	// disease wise total abnormal
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> viewcmDashboardDiseaseWiseTotalAbnormalDao(String stateId) {
		logger.info("Method : viewcmDashboardDiseaseWiseTotalAbnormalDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_view_diseasewisetotalabnormal").
					setParameter("stateId", stateId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("disease wise total abnormal========="+resp);
		logger.info("Method : viewcmDashboardDiseaseWiseTotalAbnormalDao ends");
		return resp;
	}
	/*************************************************
	 * Rest function to get pie chart for male and female in dm dashboard
	 */
	public JsonResponse<List<DropDownModel>> viewcmDashboardMaleFemaleDao(String stateId) {
		logger.info("Method : viewcmDashboardMaleFemaleDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_view_total_checkupMaleFemale")
					.setParameter("stateId", stateId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				stateList.add(dropDownModel);
				System.out.println("stateList@@@@@@@"+stateList);
			}

			resp.setBody(stateList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewcmDashboardMaleFemaleDao ends");
		return resp;
	}
	
	/*************************************************
	 * Rest function to get pie chart for normal and abnormal in dm dashboard
	 */
	public JsonResponse<List<DropDownModel>> viewcmDashboardNormalAbNormalDao(String stateId) {
		logger.info("Method : viewcmDashboardNormalAbNormalDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_view_total_checkupNormalAbnormal")
					.setParameter("stateId", stateId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1], m[2]);
				stateList.add(dropDownModel);
				System.out.println("@@@@@@@"+stateList);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewcmDashboardNormalAbNormalDao ends");
		return resp;
	}
	
	/*************************************************
	 * Rest function to get pie chart for Area wise in cm dashboard
	 */
	public JsonResponse<List<DropDownModel>> viewcmDashboardAreaWiseCheckUpDao(String stateId) {
		logger.info("Method : viewcmDashboardAreaWiseCheckUpDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_view_total_checkupAreaWise")
					.setParameter("stateId", stateId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("area wise total test=========" + resp);
		logger.info("Method : viewcmDashboardAreaWiseCheckUpDao ends");
		return resp;
	}
	public JsonResponse<List<DropDownModel>> viewcmDashboardAreaWiseNormalCheckUpDao(String stateId) {
		logger.info("Method : viewcmDashboardAreaWiseNormalCheckUpDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_view_total_areawisenormal").setParameter("stateId", stateId)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : viewcmDashboardAreaWiseNormalCheckUpDao ends");
		return resp;
	}
	/*************************************************
	 * Rest function to get pie chart for Highrisk Area wise in dm dashboard
	 */
	public JsonResponse<List<DropDownModel>> viewcmDashboardAreaWiseHighriskCheckUpDao(String stateId) {
		logger.info("Method : viewcmDashboardAreaWiseHighriskCheckUpDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_view_total_areawisehighrisk")
				.setParameter("stateId", stateId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : viewcmDashboardAreaWiseHighriskCheckUpDao ends");
		return resp;
	}
	
	
}
