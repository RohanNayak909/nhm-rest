package nirmalya.aatithya.restmodule.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.ModulesAccessModel;

@Repository
public class ModulesAccessDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	Logger logger = LoggerFactory.getLogger(ModulesAccessDao.class);

	@SuppressWarnings("unchecked")
	public List<ModulesAccessModel> getModuleListForAccess() {
		logger.info("Method : getModuleListForAccess starts");

		List<ModulesAccessModel> locationTypeList = new ArrayList<ModulesAccessModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("moduleRoutines")
					.setParameter("actionType", "getModules").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				ModulesAccessModel dropDownModel = new ModulesAccessModel(m[0], m[1], m[2], null, null);
				locationTypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getModuleListForAccess ends");
		return locationTypeList;
	}

	@SuppressWarnings("unchecked")
	public List<ModulesAccessModel> getComponentListForAccess(String id) {
		logger.info("Method : getComponentListForAccess starts");

		List<ModulesAccessModel> locationTypeList = new ArrayList<ModulesAccessModel>();

		try {
			String value = "SET @P_Module='" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("moduleRoutines")
					.setParameter("actionType", "getComponents").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				ModulesAccessModel dropDownModel = new ModulesAccessModel(m[0], m[1], m[2], null, null);
				locationTypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getComponentListForAccess ends");
		return locationTypeList;
	}

	@SuppressWarnings("unchecked")
	public List<ModulesAccessModel> getSubComponentListForAccess(String id, String comp) {
		logger.info("Method : getSubComponentListForAccess starts");

		List<ModulesAccessModel> locationTypeList = new ArrayList<ModulesAccessModel>();

		try {
			String value = "SET @P_Module='" + id + "', @P_Component='" + comp + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("moduleRoutines")
					.setParameter("actionType", "getSubComponents").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				ModulesAccessModel dropDownModel = new ModulesAccessModel(m[0], m[1], m[2], null, null);
				locationTypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getSubComponentListForAccess ends");
		return locationTypeList;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ModulesAccessModel>> updateActivity(String id, String status,
			String modifiedBy) {
		logger.info("Method : updateActivity starts");

		JsonResponse<ModulesAccessModel> resp = new JsonResponse<ModulesAccessModel>();
		resp.setMessage("");
		resp.setCode("");
		List<ModulesAccessModel> dataList = new ArrayList<ModulesAccessModel>();

		try {
			String values = "SET @P_Id='" + id + "', @P_Status=" + status + ", @P_ModifedBy='" + modifiedBy + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("moduleRoutines")
					.setParameter("actionType", "updateActivity").setParameter("actionValue", values).getResultList();

			for (Object[] m : x) {
				ModulesAccessModel dropDownModel = new ModulesAccessModel(m[0], m[1], m[2], m[3], null);
				dataList.add(dropDownModel);
			}
			resp.setBody(dataList.get(0));
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<ModulesAccessModel>> response = new ResponseEntity<JsonResponse<ModulesAccessModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : updateActivity ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ModulesAccessModel>> updateFunction(String id, String status,
			String modifiedBy) {
		logger.info("Method : updateFunction starts");

		JsonResponse<ModulesAccessModel> resp = new JsonResponse<ModulesAccessModel>();
		resp.setMessage("");
		resp.setCode("");
		List<ModulesAccessModel> dataList = new ArrayList<ModulesAccessModel>();

		try {
			String values = "SET @P_Id='" + id + "', @P_Status=" + status + ", @P_ModifedBy='" + modifiedBy + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("moduleRoutines")
					.setParameter("actionType", "updateFunction").setParameter("actionValue", values).getResultList();

			for (Object[] m : x) {
				ModulesAccessModel dropDownModel = new ModulesAccessModel(m[0], m[1], m[2], m[3], null);
				dataList.add(dropDownModel);
			}
			resp.setBody(dataList.get(0));

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<ModulesAccessModel>> response = new ResponseEntity<JsonResponse<ModulesAccessModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : updateFunction ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ModulesAccessModel>> updateModule(String id, String status, String modifiedBy) {
		logger.info("Method : updateModule starts");

		JsonResponse<ModulesAccessModel> resp = new JsonResponse<ModulesAccessModel>();
		resp.setMessage("");
		resp.setCode("");
		List<ModulesAccessModel> dataList = new ArrayList<ModulesAccessModel>();

		try {
			String values = "SET @P_Id='" + id + "', @P_Status=" + status + ", @P_ModifedBy='" + modifiedBy + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("moduleRoutines")
					.setParameter("actionType", "updateModule").setParameter("actionValue", values).getResultList();

			for (Object[] m : x) {
				ModulesAccessModel dropDownModel = new ModulesAccessModel(m[0], m[1], m[2], m[3], null);
				dataList.add(dropDownModel);
			}
			resp.setBody(dataList.get(0));

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<ModulesAccessModel>> response = new ResponseEntity<JsonResponse<ModulesAccessModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : updateModule ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ModulesAccessModel>> updateData(ModulesAccessModel id) {
		logger.info("Method : updateData starts");

		JsonResponse<ModulesAccessModel> resp = new JsonResponse<ModulesAccessModel>();
		resp.setMessage("");
		resp.setCode("");
		List<ModulesAccessModel> dataList = new ArrayList<ModulesAccessModel>();

		try {

			Integer statusVal = 0;

			if (id.getStatus()) {
				statusVal = 1;
			} else {
				statusVal = 0;
			}

			String values = "SET @P_Id='" + id.getKey() + "', @P_Status=" + statusVal + ", @P_ModifedBy='"
					+ id.getCreatedBy() + "', @P_DisplayName='" + id.getName() + "';";

			String action = "";

			if (id.getValue().contentEquals("1")) {
				action = "updateModule";
			} else if (id.getValue().contentEquals("2")) {
				action = "updateFunction";
			} else if (id.getValue().contentEquals("3")) {
				action = "updateActivity";
			}
			List<Object[]> x = em.createNamedStoredProcedureQuery("moduleRoutines").setParameter("actionType", action)
					.setParameter("actionValue", values).getResultList();

			for (Object[] m : x) {
				ModulesAccessModel dropDownModel = new ModulesAccessModel(m[0], m[1], m[2], m[3], null);
				dataList.add(dropDownModel);
			}
			resp.setBody(dataList.get(0));

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<ModulesAccessModel>> response = new ResponseEntity<JsonResponse<ModulesAccessModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : updateData ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ModulesAccessModel>>> getAvailableFunctionLists() {
		logger.info("Method : getAvailableFunctionLists starts");

		JsonResponse<List<ModulesAccessModel>> resp = new JsonResponse<List<ModulesAccessModel>>();
		resp.setMessage("");
		resp.setCode("");
		List<ModulesAccessModel> locationTypeList = new ArrayList<ModulesAccessModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("moduleRoutines")
					.setParameter("actionType", "getAvlFunctionLists").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				ModulesAccessModel dropDownModel = new ModulesAccessModel(m[0], m[1], null, m[2], m[3]);
				locationTypeList.add(dropDownModel);
			}
			resp.setBody(locationTypeList);
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<List<ModulesAccessModel>>> response = new ResponseEntity<JsonResponse<List<ModulesAccessModel>>>(
				resp, HttpStatus.CREATED);
		
		logger.info("Method : getAvailableFunctionLists ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ModulesAccessModel>> editViewSliderDetails(String id, String name) {
		logger.info("Method : editViewSliderDetails starts");

		JsonResponse<ModulesAccessModel> resp = new JsonResponse<ModulesAccessModel>();

		resp.setMessage("");
		resp.setCode("");

		List<ModulesAccessModel> locationTypeList = new ArrayList<ModulesAccessModel>();

		try {

			String value = "SET @P_ID = '" + id + "', @P_Name = '" + name + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("moduleRoutines")
					.setParameter("actionType", "editViewSliderDetails").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {
				ModulesAccessModel dropDownModel = new ModulesAccessModel(m[0], m[1], m[2], m[3], null);
				locationTypeList.add(dropDownModel);
			}
			resp.setBody(locationTypeList.get(0));
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<ModulesAccessModel>> response = new ResponseEntity<JsonResponse<ModulesAccessModel>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getAvailableFunctionLists ends");
		return response;
	}

}
