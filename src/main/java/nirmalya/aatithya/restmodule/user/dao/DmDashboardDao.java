package nirmalya.aatithya.restmodule.user.dao;

import java.util.List;

import java.util.ArrayList;
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


@Repository
public class DmDashboardDao {
	Logger logger = LoggerFactory.getLogger(DmDashboardDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	/*
	Dashboard  count list
	
	*/
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<DmDashboardRestModel>> viewdmDashboardCount(String countryId,String stateId,String districtId) {
		logger.info("Method : viewdmDashboardCount starts");

		DmDashboardRestModel req = new DmDashboardRestModel();
		JsonResponse<DmDashboardRestModel> resp = new JsonResponse<DmDashboardRestModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("dm_dashboard_view_count")
					.setParameter("countryId", countryId).setParameter("stateId", stateId).setParameter("districtId", districtId).getResultList();
			
			
			for (Object[] m : x) {
				DmDashboardRestModel reqemp = new DmDashboardRestModel(null, m[0],m[1],m[2], m[3], m[4], m[5],null,
						null);
				
				req = reqemp;
			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<DmDashboardRestModel>> response = new ResponseEntity<JsonResponse<DmDashboardRestModel>>(
				resp, responseHeaders, HttpStatus.CREATED);
		
		logger.info("Method : viewdmDashboardCount ends")	;
		return response;
	}
	
	// Dashboard  GetIdd list
				@SuppressWarnings("unchecked")
				public List<DmDashboardRestModel> dmDashboardGetIdd(String id) {
				
					logger.info("Method : getCountryLists Dao starts");
					List<DmDashboardRestModel> dmDashboardGetIdd = new ArrayList<DmDashboardRestModel>();

					try {

						List<Object[]> x = em.createNamedStoredProcedureQuery("dm_dashboard_get_idd").setParameter("collectorid", id).getResultList();

						for (Object[] m : x) {

							DmDashboardRestModel dropDownModel = new DmDashboardRestModel(m[0].toString(), m[1], m[2],m[3],m[4],m[5],m[6],m[7]);
							dmDashboardGetIdd.add(dropDownModel);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					logger.info("Method : getCountryLists Dao ends");

					return dmDashboardGetIdd;
				}
	

				
				// age wise test report
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<DmDashboardRestModel>> viewdmDashboardAgeWiseTotalTestDao(String countryId,String stateId,String districtId) {
					logger.info("Method : viewdmDashboardAgeWiseTotalTestDao starts");

					DmDashboardRestModel req = new DmDashboardRestModel();
					JsonResponse<DmDashboardRestModel> resp = new JsonResponse<DmDashboardRestModel>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("dm_dashboard_view_agewisetotaltest")
								.setParameter("countryId", countryId).setParameter("stateId", stateId).setParameter("districtId", districtId).getResultList();

						for (Object[] m : x) {
							DmDashboardRestModel reqemp = new DmDashboardRestModel(m[0].toString(),m[1].toString(),m[2].toString(), m[3].toString());
							
							req = reqemp;
						}

						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					}

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");

					ResponseEntity<JsonResponse<DmDashboardRestModel>> response = new ResponseEntity<JsonResponse<DmDashboardRestModel>>(
							resp, responseHeaders, HttpStatus.CREATED);
					
					logger.info("Method : viewdmDashboardAgeWiseTotalTestDao ends")	;
					return response;
				}
				// Area Wise

					@SuppressWarnings("unchecked")
					public JsonResponse<List<DropDownModel>> viewdmDashboardAreaWiseTotalTestDao(String countryId,String stateId,String districtId) {

						logger.info("Method : viewdmDashboardAreaWiseTotalTestDao starts");
						List<DropDownModel> stateList = new ArrayList<DropDownModel>();
						JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
						try {
							List<Object[]> x = em.createNamedStoredProcedureQuery("dm_dashboard_view_areawisetotaltest")
									.setParameter("countryId", countryId).setParameter("stateId", stateId).setParameter("districtId", districtId).getResultList();
							for (Object[] m : x) {
								DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
								stateList.add(dropDownModel);
							}

							resp.setBody(stateList);

						} catch (Exception e) {
							e.printStackTrace();
						}
						logger.info("Method : viewdmDashboardAreaWiseTotalTestDao ends");
						return resp;
					}
					// disease wise total abnormal

					@SuppressWarnings("unchecked")
					public JsonResponse<List<DropDownModel>> viewdmDashboardDiseaseWiseTotalAbnormalDao(String countryId,String stateId,String districtId) {

						logger.info("Method : viewdmDashboardDiseaseWiseTotalAbnormalDao starts");
						List<DropDownModel> stateList = new ArrayList<DropDownModel>();
						JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
						try {
							List<Object[]> x = em.createNamedStoredProcedureQuery("dm_dashboard_view_diseasewisetotalabnormal")
									.setParameter("countryId", countryId).setParameter("stateId", stateId).setParameter("districtId", districtId).getResultList();
							for (Object[] m : x) {
								DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
								stateList.add(dropDownModel);
							}

							resp.setBody(stateList);

						} catch (Exception e) {
							e.printStackTrace();
						}
						logger.info("Method : viewdmDashboardDiseaseWiseTotalAbnormalDao ends");
						return resp;
					}
					// disease wise total high risk

					@SuppressWarnings("unchecked")
					public JsonResponse<List<DropDownModel>> viewdmDashboardDiseaseWiseTotalHighRiskDao(String countryId,String stateId,String districtId) {

						logger.info("Method : viewdmDashboardDiseaseWiseTotalHighRiskDao starts");
						List<DropDownModel> stateList = new ArrayList<DropDownModel>();
						JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
						try {
							List<Object[]> x = em.createNamedStoredProcedureQuery("dm_dashboard_view_diseasewisetotalhighrisk")
									.setParameter("countryId", countryId).setParameter("stateId", stateId).setParameter("districtId", districtId).getResultList();
							for (Object[] m : x) {
								DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
								stateList.add(dropDownModel);
							}

							resp.setBody(stateList);

						} catch (Exception e) {
							e.printStackTrace();
						}
						logger.info("Method : viewdmDashboardDiseaseWiseTotalHighRiskDao ends");
						return resp;
					}				
				
	//////////////////////////////////////////////////////////// Dao//////////////////////////////////////////////////////////////////////////////////

	/*************************************************
	 * Rest function to get pie chart for male and female in dm dashboard
	 */
	public JsonResponse<List<DropDownModel>> viewdmDashboardMaleFemaleDao(String countryId, String stateId,
			String districtId) {
		logger.info("Method : viewdmDashboardMaleFemaleDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("dm_dashboard_view_total_checkupMaleFemale")
					.setParameter("countryId", countryId).setParameter("stateId", stateId)
					.setParameter("districtId", districtId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewdmDashboardMaleFemaleDao ends");
		return resp;
	}

	/*************************************************
	 * Rest function to get pie chart for male and female in dm dashboard
	 */

	public JsonResponse<List<DropDownModel>> viewdmDashboardNormalAbNormalDao(String countryId, String stateId,
			String districtId) {
		logger.info("Method : viewdmDashboardMaleFemaleDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("dm_dashboard_view_total_checkupNormalAbnormal")
					.setParameter("countryId", countryId).setParameter("stateId", stateId)
					.setParameter("districtId", districtId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1], m[2]);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewdmDashboardMaleFemaleDao ends");
		return resp;
	}

	/*************************************************
	 * Rest function to get pie chart for Area wise in dm dashboard
	 */
	public JsonResponse<List<DropDownModel>> viewdmDashboardAreaWiseCheckUpDao(String countryId, String stateId,
			String districtId) {
		logger.info("Method : viewdmDashboardAreaWiseCheckUpDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("dm_dashboard_view_total_checkupAreaWise")
					.setParameter("countryId", countryId).setParameter("stateId", stateId)
					.setParameter("districtId", districtId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewdmDashboardAreaWiseCheckUpDao ends");
		return resp;
	}
	/*************************************************
	 * Rest function to get pie chart for Normal Area wise in dm dashboard
	 */
	public JsonResponse<List<DropDownModel>> viewdmDashboardAreaWiseNormalCheckUpDao(String countryId,
			String stateId, String districtId) {
		logger.info("Method : viewdmDashboardAreaWiseNormalCheckUpDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("dm_dashboard_view_total_areawisenormal")
					.setParameter("countryId", countryId).setParameter("stateId", stateId)
					.setParameter("districtId", districtId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : viewdmDashboardAreaWiseNormalCheckUpDao ends");
		return resp;
	}	
	/*************************************************
	 * Rest function to get pie chart for Highrisk Area wise in dm dashboard
	 */
	public JsonResponse<List<DropDownModel>> viewdmDashboardAreaWiseHighriskCheckUpDao(String countryId,
			String stateId, String districtId) {
		logger.info("Method : viewdmDashboardAreaWiseHighriskCheckUpDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("dm_dashboard_view_total_areawisehighrisk")
					.setParameter("countryId", countryId).setParameter("stateId", stateId)
					.setParameter("districtId", districtId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : viewdmDashboardAreaWiseHighriskCheckUpDao ends");
		return resp;
	}
	/************************************************ function to get pie chart for normal and abnormal in dm dashboard */
	public JsonResponse<List<DropDownModel>> genderWiseDiseaseCount(String countryId, String stateId,
			String districtId) {
		logger.info("Method : genderWiseDiseaseCount starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("dm_dashboard_genderwise_disease")
					.setParameter("countryid", countryId).
					setParameter("stateid", stateId).
					setParameter("districtid", districtId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1], m[2]);
				stateList.add(dropDownModel);
			}
	
			resp.setBody(stateList);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : genderWiseDiseaseCount ends");
		return resp;
	}

/*
 * Total registration details
 *
 */
				@SuppressWarnings("unchecked")
				public JsonResponse<List<DmDashboardRestModel>> viewdmDashboardTotalRegdDao(String countryId,String stateId,String districtId,String typeId) {
					logger.info("Method : viewdmDashboardTotalRegdDao starts");

					List<DmDashboardRestModel> req = new ArrayList<DmDashboardRestModel>();
					
					JsonResponse<List<DmDashboardRestModel>> resp = new JsonResponse<List<DmDashboardRestModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("dm_dashboard_view_patientDetails")
								.setParameter("countryId", countryId).setParameter("stateId", stateId).setParameter("districtId", districtId).setParameter("typeId", typeId).getResultList();

						for (Object[] m : x) {
							DmDashboardRestModel reqemp = new DmDashboardRestModel(m[0],m[1],m[2], m[3],m[4].toString(), null);
							
							req.add(reqemp);
							
							resp.setBody(req);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					logger.info("Method : viewdmDashboardTotalRegdDao ends"+resp);
					return resp;
				}				
				
}
