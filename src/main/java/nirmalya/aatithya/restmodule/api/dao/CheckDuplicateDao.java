package nirmalya.aatithya.restmodule.api.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

@Repository
public class CheckDuplicateDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	Logger logger = LoggerFactory.getLogger(CheckDuplicateDao.class);
	
	@SuppressWarnings("unchecked")
	public Boolean isPatientMobileNumberExist (Object object) {
		logger.info("Method : isPatientMobileNumberExist Dao starts");
		
		Boolean isExist =  false;

		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_mobile_number_exist").setParameter("mobile", object).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList != null) {
				isExist =  true;
			}
			
			if(dataList.size() > 0) {
				isExist =  true;
			} else {
				isExist =  false;
			}
			
		} catch (Exception e) {
			isExist =  false;
		}
		
		logger.info("Method : isPatientMobileNumberExist Dao ends");
		return isExist;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean isPatientEmailExist (Object object) {
		logger.info("Method : isPatientEmailExist Dao starts");
		
		Boolean isExist =  false;

		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_email_exist").setParameter("email", object).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList != null) {
				isExist =  true;
			}
			
			if(dataList.size() > 0) {
				isExist =  true;
			} else {
				isExist =  false;
			}
			
		} catch (Exception e) {
			isExist =  false;
		}
		
		logger.info("Method : isPatientEmailExist Dao ends");
		return isExist;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean isPatientAadharNumberExist (String aadhar) {
		logger.info("Method : isPatientAadharNumberExist Dao starts");
		
		Boolean isExist =  false;
		
		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_aadhar_number_exist").setParameter("aadhar", aadhar).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList != null) {
				isExist =  true;
			}
			
			if(dataList.size() > 0) {
				isExist =  true;
			} else {
				isExist =  false;
			}
			
		} catch (Exception e) {
			isExist =  false;
		}
		
		logger.info("Method : isPatientMobileNumberExist Dao ends");
		return isExist;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean isEContactAdded (String userid) {
		logger.info("Method : isEContactAdded Dao starts");
		
		Boolean isExist =  false;
		
		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_emergency_contact_exist").setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList != null) {
				isExist =  true;
			}
			
			if(dataList.size() > 0) {
				isExist =  true;
			} else {
				isExist =  false;
			}
			
		} catch (Exception e) {
			isExist =  false;
		}
		
		logger.info("Method : isEContactAdded Dao ends");
		return isExist;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean isFDoctorAdded (String userid) {
		logger.info("Method : isFDoctorAdded Dao starts");
		
		Boolean isExist =  false;
		
		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_family_doctor_exist").setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList != null) {
				isExist =  true;
			}
			
			if(dataList.size() > 0) {
				isExist =  true;
			} else {
				isExist =  false;
			}
			
		} catch (Exception e) {
			isExist =  false;
		}
		
		logger.info("Method : isFDoctorAdded Dao ends");
		return isExist;
	}
	
	@SuppressWarnings("unchecked")
	public String getCasePaperNumber (String userid) {
		logger.info("Method : getCasePaperNumber Dao starts");
		
		String casepaper =  null;
		
		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_casepaper_no").setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList.size() > 0) {
				casepaper = dataList.get(0).getName();
			} else {
				casepaper =  null;
			}
			
		} catch (Exception e) {
			casepaper =  null;
		}
		
		logger.info("Method : getCasePaperNumber Dao ends");
		return casepaper;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean isValidUHIDNumber (String uhid) {
		logger.info("Method : isValidUHIDNumber Dao starts");
		
		Boolean isExist =  false;

		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("uhid_number_exist").setParameter("uhid", uhid).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList != null) {
				isExist =  true;
			}
			
			if(dataList.size() > 0) {
				isExist =  true;
			} else {
				isExist =  false;
			}
			
		} catch (Exception e) {
			isExist =  false;
		}
		
		logger.info("Method : isValidUHIDNumber Dao ends");
		return isExist;
	}
	
	//To check allergy id and name present or not
	@SuppressWarnings("unchecked")
	public Boolean isAllergyExist(String userid, String allName, String allid) {
		logger.info("Method : isAllergyExist Dao starts");
		
		Boolean isExist =  false;

		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("check_allergies_available_api")
					.setParameter("userid", userid).setParameter("allnameid", allName)
					.setParameter("alltypeid", allid)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1],m[2]);
				dataList.add(dropDownModel);
			}
			
			if(dataList != null) {
				isExist =  false;
			}
			
			if(dataList.size() > 0) {
				isExist =  false;
			} else {
				isExist =  true;
			}
			
		} catch (Exception e) {
			isExist =  false;
		}
		
		logger.info("Method : isAllergyExist Dao ends");
		return isExist;
	}
	
	@SuppressWarnings("unchecked")
	public String getLTPHCLocation(String userid) {
		logger.info("Method : getLTPHCLocation Dao starts");
		
		String phclocation = null;
		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_lt_phc_location")
					.setParameter("userid", userid)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList.size() > 0) {
				phclocation =  dataList.get(0).getName();
			} else {
				phclocation =  null;
			}
			
		} catch (Exception e) {
			phclocation =  null;
		}
		
		logger.info("Method : getLTPHCLocation Dao ends");
		return phclocation;
	}
	
	@SuppressWarnings("unchecked")
	public String getUserPassword (String mobile) {
		logger.info("Method : getUserPassword Dao starts");
		
		String password =  null;
		
		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_password").setParameter("mobile", mobile).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList.size() > 0) {
				password = dataList.get(0).getName();
			} else {
				password =  null;
			}
			
		} catch (Exception e) {
			password =  null;
		}
		System.out.println("Get---Password==="+password);
		logger.info("Method : getUserPassword Dao ends");
		return password;
	}
}
