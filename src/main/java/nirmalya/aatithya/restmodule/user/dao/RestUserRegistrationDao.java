package nirmalya.aatithya.restmodule.user.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.user.model.RestGuestRegdModel;
import nirmalya.aatithya.restmodule.user.model.RestUserRegistrationModel;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class RestUserRegistrationDao {
	Logger logger = LoggerFactory.getLogger(RestUserRegistrationDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;


	// Title list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> geTitleLists() {
		logger.info("Method : geTitleLists Dao starts");

		List<DropDownModel> geTitleList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_title_list").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				geTitleList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : geTitleLists Dao ends");

		return geTitleList;
	}

	// Country list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getCountryLists() {
		logger.info("Method : getCountryLists Dao starts");

		List<DropDownModel> getCountryList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_country_list").getResultList();

			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString(), m[2]);
				getCountryList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getCountryLists Dao ends");

		return getCountryList;
	}

	// Blood Group list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getBloodGrpLists() {
		logger.info("Method : getBloodGrpLists Dao starts");

		List<DropDownModel> bloodGrpList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_bloodGroup_list").getResultList();

			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				bloodGrpList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getBloodGrpLists Dao ends");

		return bloodGrpList;
	}

	// Role User list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getroleUserLists() {
		logger.info("Method : getroleUserList Dao starts");

		List<DropDownModel> uRoleList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_roleUser_list").getResultList();

			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				uRoleList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getroleUserList Dao ends");
		return uRoleList;
	}

	// Gender list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getGenderLists() {
		logger.info("Method : getGenderList Dao starts");

		List<DropDownModel> genderList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_profile_gender_drpdwn").getResultList();

			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				genderList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getGenderList Dao ends");
		return genderList;
	}
	// Speciality list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getSpecialityListDao(Integer id) {

		logger.info("Method : getSpecialityListDao starts");
		List<DropDownModel> splList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_speciality_list").setParameter("roleid", id)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				splList.add(dropDownModel);
			}
			resp.setBody(splList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getSpecialityListDao ends");
		return resp;
	}

	// State list
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getStateListDao(Integer id) {

		logger.info("Method : getStateListDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_state_list").setParameter("country", id)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString(), m[2].toString());
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getStateListDao ends");
		return resp;
	}
	// District list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getDistListDao(String state) {

		logger.info("Method : getDistListDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_district_list").setParameter("stateId", state)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getDistListDao ends");
		return resp;
	}
	// City list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getCityListDao(String dist) {

		logger.info("Method : getCityListDao starts");
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
		logger.info("Method : getCityListDao ends");
		return resp;
	}

	// AutoSearch
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestUserRegistrationModel>> getOrganizationAutoSearchListDao(String id, Integer role) {
		logger.info("Method : getOrganizationAutoSearchListDao starts");
		List<RestUserRegistrationModel> orgNameList = new ArrayList<RestUserRegistrationModel>();
		JsonResponse<List<RestUserRegistrationModel>> resp = new JsonResponse<List<RestUserRegistrationModel>>();
		try {
			System.out.println("SSSDfdfd" + role);
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_autoSearch_organization_list")
					.setParameter("searchValue", id).setParameter("roleid", role).getResultList();
			for (Object[] m : x) {
				RestUserRegistrationModel dropDownModel = new RestUserRegistrationModel(m[0], m[1]);
				orgNameList.add(dropDownModel);
			}
			resp.setBody(orgNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getOrganizationAutoSearchListDao" + resp);
		logger.info("Method : getOrganizationAutoSearchListDao ends");
		return resp;
	}

	// get card plan
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestUserRegistrationModel>> getPlanCardDao(String id) {

		logger.info("Method : getPlanCard Dao starts");
		List<RestUserRegistrationModel> plan = new ArrayList<RestUserRegistrationModel>();
		JsonResponse<List<RestUserRegistrationModel>> resp = new JsonResponse<List<RestUserRegistrationModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_plan_card").setParameter("descName", id)
					.getResultList();

			for (Object[] m : x) {

				RestUserRegistrationModel planCard = new RestUserRegistrationModel(m[0], m[1].toString(),
						m[2].toString(), m[3]);

				plan.add(planCard);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(plan);
		logger.info("Method : getPlanCard Dao ends");
		return resp;

	}

	/**
	 * DAO Function to Add User Registration
	 *
	 */
	// save
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> addUserRegistrationDao(RestUserRegistrationModel regdModel) {

		logger.info("Method : addUserRegistrationDao starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String interesList = "";
		logger.info("USER"+regdModel.getUserInterest());
		if(regdModel.getUserInterest()!= null) {
		if (regdModel.getUserInterest().size() > 0) {
				interesList = interesList + "(\'"+regdModel.getPatientId()
				+"\',\'"+regdModel.getIsInterest()+"\',\'"+regdModel.getCountry()+"\',\'"+ regdModel.getState()+"\',\'"+ regdModel.getDist()+"\',\'"
				+ regdModel.getCity()+"\',\'"+ regdModel.getGetUpdate()+"\',\'"+ regdModel.getPatientId()+"\'),";
			interesList = interesList.substring(0, interesList.length() - 1);
			logger.info("hyy1"+interesList);

		}
		else{
			interesList = "";
		}
		}
		String catagoryList = "";
		if(regdModel.getUserInterest()!= null) {
		if (regdModel.getUserInterest().size() > 0) {
			for (DropDownModel m : regdModel.getUserInterest()) {
				catagoryList = catagoryList + "(interest_id,\'"+m.getKey()+"\',\'"+m.getName()+"\' ),";
				
			}
			catagoryList = catagoryList.substring(0, catagoryList.length() - 1);
			logger.info("hyy2"+catagoryList);
		}else {
			catagoryList = "";
		}
		}
		try {
			if (regdModel.getCreatedBy() == null) {
				logger.info("hyy1"+interesList);
				logger.info("hyy2"+catagoryList);
				List<Object[]> x= em.createNamedStoredProcedureQuery("user_add_registration_new")
						.setParameter("p_profileImg", regdModel.getFileProfileimg())
						.setParameter("p_patientId", regdModel.getPatientId())
						.setParameter("p_title", regdModel.getTittle()).setParameter("p_fName", regdModel.getfName())
						.setParameter("p_lName", regdModel.getlName()).setParameter("p_gender", regdModel.getGender())
						.setParameter("p_countryName", regdModel.getCountryName())
						.setParameter("p_country", regdModel.getCountry())
						.setParameter("p_stateName", regdModel.getStateName())
						.setParameter("p_state", regdModel.getState()).setParameter("p_dist", regdModel.getDist())
						.setParameter("p_city", regdModel.getCity()).setParameter("p_mob", regdModel.getMob())
						.setParameter("p_mail", regdModel.getMail()).setParameter("p_userId", regdModel.getUserId())
						.setParameter("p_qrCode", regdModel.getqRFileName())
						.setParameter("p_password", regdModel.getPassword())
						.setParameter("interesList", interesList)
						.setParameter("catagoryList", catagoryList)
						.setParameter("abha_id", regdModel.getAbhaId())
						.setParameter("aadhaarNoAbha", regdModel.getAadhaarNoAbha()).getResultList();
				logger.info("hyy1"+interesList);
				logger.info("hyy2"+catagoryList);
				} else {
				em.createNamedStoredProcedureQuery("lab_add_walkinUser_registration")
						.setParameter("p_profileImg", regdModel.getFileProfileimg())
						.setParameter("p_patientId", regdModel.getPatientId())
						.setParameter("p_title", regdModel.getTittle()).setParameter("p_fName", regdModel.getfName())
						.setParameter("p_lName", regdModel.getlName()).setParameter("p_gender", regdModel.getGender())
						.setParameter("p_countryName", regdModel.getCountryName())
						.setParameter("p_country", regdModel.getCountry())
						.setParameter("p_stateName", regdModel.getStateName())
						.setParameter("p_state", regdModel.getState()).setParameter("p_dist", regdModel.getDist())
						.setParameter("p_city", regdModel.getCity()).setParameter("p_mob", regdModel.getMob())
						.setParameter("p_mail", regdModel.getMail()).setParameter("p_userId", regdModel.getUserId())
						.setParameter("p_password", regdModel.getPassword())
						.setParameter("p_createdBy", regdModel.getCreatedBy())
						.setParameter("p_qrCode", regdModel.getqRFileName())
						.setParameter("abha_id", regdModel.getAbhaId())
						.setParameter("aadhaarNoAbha", regdModel.getAadhaarNoAbha()).execute();
			}
			
		} catch (Exception e) {
			logger.info("addUserRegistrationDao: " + e );
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info("Method : addUserRegistrationDao ends");
		return resp;
	}

	/**
	 * DAO Function to Add User Registration
	 *
	 */

	// save
	@SuppressWarnings("unlikely-arg-type")
	public JsonResponse<Object> addProfRegistrationDao(RestUserRegistrationModel regdModel) {

		logger.info("Method : addProfRegistrationDao starts");

		System.out.println("regdModel===" + regdModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
	String interestlist = "";
	
	String drid ="";
	

	
	
		if(regdModel.getUserInterest()!= null) {
		if (regdModel.getUserInterest().size() > 0) {
			for (DropDownModel m : regdModel.getUserInterest()) {
				interestlist = interestlist + "(drid,\'"+m.getName()+"\',"+regdModel.getCountry()+","+ regdModel.getState()+","+ regdModel.getDist()+","
						+ regdModel.getCity()+"),";
				interestlist = interestlist.substring(0, interestlist.length() - 1);
		logger.info("lossssst"+interestlist);
			}
				
		}
		else{
			interestlist = "";
		}
		}
		
	
		
		if(regdModel.getRoleUser().equals(28)) {
			logger.info("role_id==="+regdModel.getRoleUser());
			logger.info("ifff"+interestlist);
			try {
				em.createNamedStoredProcedureQuery("user_add_vle_registration")
						.setParameter("p_profileImg", regdModel.getFileProfileimg())
						.setParameter("p_hsId", regdModel.getOrganizationId())
						.setParameter("p_title", regdModel.getTittle()).setParameter("p_name", regdModel.getName())
						.setParameter("p_userId", regdModel.getUserId()).setParameter("p_password", regdModel.getPassword())
						.setParameter("p_exp", regdModel.getExperience()).setParameter("p_role", regdModel.getRoleUser())
						.setParameter("p_speciality", regdModel.getSpeciality())
						.setParameter("p_jobType", regdModel.getJobType()).setParameter("p_gender", regdModel.getGender())
						.setParameter("p_address", regdModel.getAddress()).setParameter("p_country", regdModel.getCountry())
						.setParameter("p_state", regdModel.getState()).setParameter("p_dist", regdModel.getDist())
						.setParameter("p_city", regdModel.getCity()).setParameter("p_pin", regdModel.getPin())
						.setParameter("p_mob", regdModel.getMob()).setParameter("p_mail", regdModel.getMail())
						.setParameter("p_village", regdModel.getVillageName())
						.setParameter("p_block", regdModel.getBlockName())
						.setParameter("p_location", regdModel.getLocationName())
						.setParameter("p_file", regdModel.getFileUpload())
						.setParameter("p_fileName", regdModel.getDocumentName())
						.setParameter("interestlist", interestlist)
						.getResultList();
				logger.info("ifffnext"+interestlist);
			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);

					resp.setCode(err[0]);
					resp.setMessage(err[1]);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}else if (regdModel.getRoleUser().equals(8)) {
			logger.info("role_id==="+regdModel.getRoleUser());
			logger.info("elseee");
			logger.info("hyyyyfffff"+regdModel);
			try {
				em.createNamedStoredProcedureQuery("user_add_lab_prof_registration")
						.setParameter("p_profileImg", regdModel.getFileProfileimg())
						.setParameter("p_hsId", regdModel.getOrganizationId())
						.setParameter("p_title", regdModel.getTittle()).setParameter("p_name", regdModel.getName())
						.setParameter("p_userId", regdModel.getUserId()).setParameter("p_password", regdModel.getPassword())
						.setParameter("p_exp", regdModel.getExperience()).setParameter("p_role", regdModel.getRoleUser())
						.setParameter("p_speciality", regdModel.getSpeciality())
						.setParameter("p_jobType", regdModel.getJobType()).setParameter("p_gender", regdModel.getGender())
						.setParameter("p_address", regdModel.getAddress()).setParameter("p_country", regdModel.getCountry())
						.setParameter("p_state", regdModel.getState()).setParameter("p_dist", regdModel.getDist())
						.setParameter("p_city", regdModel.getCity()).setParameter("p_pin", regdModel.getPin())
						.setParameter("p_mob", regdModel.getMob()).setParameter("p_mail", regdModel.getMail())
						.setParameter("p_village", regdModel.getVillageName())
						.setParameter("p_block", regdModel.getBlockName())
						.setParameter("p_location", regdModel.getLocationName())
						.setParameter("p_file", regdModel.getFileUpload())
						.setParameter("p_fileName", regdModel.getDocumentName())
						.setParameter("p_createdBy", regdModel.getCreatedBy())
						.execute();
			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);

					resp.setCode(err[0]);
					resp.setMessage(err[1]);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}else {
			logger.info("role_id==="+regdModel.getRoleUser());
			logger.info("elseee");
			try {
				em.createNamedStoredProcedureQuery("user_add_prof_registration")
						.setParameter("p_profileImg", regdModel.getFileProfileimg())
						.setParameter("p_hsId", regdModel.getOrganizationId())
						.setParameter("p_title", regdModel.getTittle()).setParameter("p_name", regdModel.getName())
						.setParameter("p_userId", regdModel.getUserId()).setParameter("p_password", regdModel.getPassword())
						.setParameter("p_exp", regdModel.getExperience()).setParameter("p_role", regdModel.getRoleUser())
						.setParameter("p_speciality", regdModel.getSpeciality())
						.setParameter("p_jobType", regdModel.getJobType()).setParameter("p_gender", regdModel.getGender())
						.setParameter("p_address", regdModel.getAddress()).setParameter("p_country", regdModel.getCountry())
						.setParameter("p_state", regdModel.getState()).setParameter("p_dist", regdModel.getDist())
						.setParameter("p_city", regdModel.getCity()).setParameter("p_pin", regdModel.getPin())
						.setParameter("p_mob", regdModel.getMob()).setParameter("p_mail", regdModel.getMail())
						.setParameter("p_village", regdModel.getVillageName())
						.setParameter("p_block", regdModel.getBlockName())
						.setParameter("p_location", regdModel.getLocationName())
						.setParameter("p_file", regdModel.getFileUpload())
						.execute();
			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);

					resp.setCode(err[0]);
					resp.setMessage(err[1]);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
		
		logger.info("Method : addProfRegistrationDao ends");
		return resp;
	}

	// State list Organisation

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getStateListOrgDao(Integer id) {

		logger.info("Method : getStateListDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_state_list").setParameter("country", id)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString(), m[2].toString());
				stateList.add(dropDownModel);
				System.out.println("stateList" + stateList);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getStateListOrgDao" + resp);
		logger.info("Method : getStateListDao ends");
		return resp;
	}

	// District list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getDistLists(String state) {

		logger.info("Method : getDistListDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_district_list").setParameter("stateId", state)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getDistListDao ends");
		return resp;
	}

	// City list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getCityListsDao(String dist) {

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

	// Organisation Type list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> organisationTypeListLists() {
		logger.info("Method : organisationTypeListLists Dao starts");

		List<DropDownModel> organisationTypeListLists = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("organisation_type_drpdwn").getResultList();

			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				organisationTypeListLists.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : organisationTypeListLists Dao ends");
		return organisationTypeListLists;
	}

	/*
	 * Add Organisation
	 */
	/*
	 * @SuppressWarnings("unchecked") public JsonResponse<Object>
	 * addOrganisation(RestUserRegistrationModel restUserRegistrationModel) {
	 * logger.info("Method : addOrganisation starts"); JsonResponse<Object> resp =
	 * new JsonResponse<Object>(); long nowTime = new Date().getTime(); String
	 * orderid = restUserRegistrationModel.getOrgName() + nowTime; try { if
	 * (restUserRegistrationModel.getOrganizationModalId() == null) { List<Object[]>
	 * x = em.createNamedStoredProcedureQuery("user_add_organisation")
	 * .setParameter("p_uniqueid", orderid) .setParameter("p_orgName",
	 * restUserRegistrationModel.getOrgName()) .setParameter("p_orgAddress",
	 * restUserRegistrationModel.getOrgAddress()) .setParameter("p_orgTypeId",
	 * restUserRegistrationModel.getOrgTypeId()) .setParameter("p_orgRegdNo",
	 * restUserRegistrationModel.getOrgRegdNo()) .setParameter("p_orgGst",
	 * restUserRegistrationModel.getOrgGst()) .setParameter("p_getCountry",
	 * restUserRegistrationModel.getCountry()) .setParameter("p_getState",
	 * restUserRegistrationModel.getState()) .setParameter("p_getDist",
	 * restUserRegistrationModel.getDist()) .setParameter("p_getCity",
	 * restUserRegistrationModel.getCity()) .setParameter("p_getOrgPin",
	 * restUserRegistrationModel.getOrgPin()).getResultList(); if (x.get(0)[0] !=
	 * null) { List<RestPatientDocumentModel> docList =
	 * restUserRegistrationModel.getDocumentList();
	 * 
	 * String s = ""; for (RestPatientDocumentModel a : docList) { s = s + "(" +
	 * x.get(0)[0] + ",'" + a.getFileName() + "','" + a.getDocumnentName() + "'),";
	 * } if (s != "") { s = s.substring(0, s.length() - 1); }
	 * em.createNamedStoredProcedureQuery("user_add_organisation_document").
	 * setParameter("p_uniqueid", s) .execute(); } }
	 * 
	 * } catch (Exception e) { try { String[] err = serverDao.errorProcedureCall(e);
	 * resp.setCode(err[0]); resp.setMessage(err[1]);
	 * logger.error(resp.getMessage());
	 * 
	 * } catch (Exception e1) { e1.printStackTrace(); } }
	 * logger.info("Method : addOrganisation ends"); return resp; }
	 */

	/*
	 * 
	 * dao for Add Guest Registration
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> guestRegd(RestGuestRegdModel restGuestRegdModel) {
		// TODO Auto-generated method stub
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_add_guest")
					.setParameter("countryid", restGuestRegdModel.getCountry())
					.setParameter("stateid", restGuestRegdModel.getState())
					.setParameter("mobileno", restGuestRegdModel.getMobileno())
					.setParameter("name", restGuestRegdModel.getName())
					.setParameter("regdno", restGuestRegdModel.getRegdno())
					.setParameter("email", restGuestRegdModel.getEmail())
					.setParameter("uhidno", restGuestRegdModel.getUhidno())
					.setParameter("password", restGuestRegdModel.getPassWord())
					.setParameter("role", restGuestRegdModel.getUserRole()).getResultList();
			for (Object[] m : x) {
				DropDownModel o1 = new DropDownModel(m[0], null);
				resp.setBody(o1);
			}
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info("ADD DAO" + resp);
		logger.info("Method : addOrganisation ends");
		return resp;
	}

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestUserRegistrationModel>> getUserInterestDao() {
		logger.info("Method in Dao: getUserInterestDao starts");

		List<RestUserRegistrationModel> interestList = new ArrayList<RestUserRegistrationModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_user_interest_catagorylist").getResultList();

			List<String> categoryList = new ArrayList<String>();

			for (Object[] m : x) {
				if (!StringUtil.isNull(m[0])) {
					categoryList.add(m[0].toString());
				}
			}

			HashSet<String> uniqueCategoryList = new HashSet<String>(categoryList);
			
			logger.info("unique=="+uniqueCategoryList);
			
			for (String p : uniqueCategoryList) {
				RestUserRegistrationModel interest = new RestUserRegistrationModel();
				List<String> subCategory = new ArrayList<String>();
				for (Object[] m : x) {
					if (!StringUtil.isNull(m[0])) {
						interest.setCatagory(p);
						if (m[0].toString().equals(p) && !StringUtil.isNull(m[1])) {
							subCategory.add(m[1].toString());
						}
					}
					interest.setSubcatagoryList(subCategory);
				}
				interestList.add(interest);
			}

			logger.info("getUserInterestDao: " + interestList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserInterestDao: " + e.getMessage());
		}
		
		JsonResponse<List<RestUserRegistrationModel>> resp = new JsonResponse<List<RestUserRegistrationModel>>();
		
		Util.setJsonResponse(resp, interestList,ResponseStatus.success, ApiResponseMessage.DATA_FETCH_SUCCESS);
		
		logger.info("Method in Dao: getUserInterestDao ends");
		return resp;
	}
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> browserdDetails(String userId,String ip, String browserName) {
		logger.info("Method : browserdDetails starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (userId != null) {
				logger.info("userId"+userId);
				em.createNamedStoredProcedureQuery("patient_login_details_id").setParameter("p_userId", userId)
						.setParameter("p_ip", ip).setParameter("p_browserName", browserName).execute();
			} 

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		logger.info("Method : Login ends"+resp);
		return resp;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> browserdwrongcredential(String userId, String ip, String browserName, String wrong) {
		logger.info("Method : browserdwrongcredential starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (userId != null) {
				logger.info("userId" + userId);
				em.createNamedStoredProcedureQuery("patient_wrong_credentialdetail_details_id")
						.setParameter("p_userId", userId).setParameter("p_ip", ip)
						.setParameter("p_browserName", browserName).setParameter("p_wrong", wrong).execute();
			}
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		logger.info("Method : Login ends" + resp);
		return resp;
	}

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getAadhaarExistsForRegDao(String aadhaar) {

		logger.info("Method : getAadhaarExistsForRegDao starts");
		List<DropDownModel> aadhaarDetails = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("check_aadhar_id_exists")
					.setParameter("aadhaar", aadhaar).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1], m[2]);
				aadhaarDetails.add(dropDownModel);
			}
			

			if (aadhaarDetails.size() > 0) {
				Util.setJsonResponse(resp, aadhaarDetails, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(resp, aadhaarDetails, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			String[] err = serverDao.errorProcedureCall(e);
			logger.error("Error " + err[1]);
			e.printStackTrace();
			Util.setJsonResponse(resp, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
		}
		logger.info("Method : getAadhaarExistsForRegDao ends");
		return resp;
	}
	 

}
