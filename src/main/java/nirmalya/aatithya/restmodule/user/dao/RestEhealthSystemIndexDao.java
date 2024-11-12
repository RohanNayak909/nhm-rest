package nirmalya.aatithya.restmodule.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
@Repository
public class RestEhealthSystemIndexDao {
	Logger logger = LoggerFactory.getLogger(RestEhealthSystemIndexDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	// State list
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getState() {
		logger.info("Method : getState Dao starts");

		List<DropDownModel> getState = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("ehealth_state_list").getResultList();

			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1],m[2],m[3],null);
				getState.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getState" + getState);
		logger.info("Method : getCountryLists Dao ends");

		return getState;
	}
	 

		// District list

		@SuppressWarnings("unchecked")
		public JsonResponse<List<DropDownModel>> getDistrictLists(Integer id) {

			logger.info("Method : getDistrictLists starts");
			List<DropDownModel> stateList = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("ehealth_district_list").setParameter("state", id)
						.getResultList();
				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1], null,null,null);
					stateList.add(dropDownModel);
					System.out.println("dropDownModel"+dropDownModel);
				}

				resp.setBody(stateList);

			} catch (Exception e) {
				e.printStackTrace();
			}
System.out.println("DAODISTRICT"+resp);
			logger.info("Method : getDistrictLists ends");
			return resp;
		}
		
		// City list

		@SuppressWarnings("unchecked")
		public JsonResponse<List<DropDownModel>> getCityLists(String dist) {

			logger.info("Method : getCityLists starts");
			List<DropDownModel> stateList = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("user_city_list").setParameter("districtId", dist)
						.getResultList();
				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
					stateList.add(dropDownModel);
				}

				resp.setBody(stateList);

			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("Method : getCityLists ends");
			return resp;
		}
}
