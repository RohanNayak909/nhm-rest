package nirmalya.aatithya.restmodule.dropdown;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nirmalya.aatithya.restmodule.api.model.CountryModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.Util;
@Service
public class DropdownDao {

	Logger logger = LoggerFactory.getLogger(DropdownDao.class);

	@Autowired
	EntityManager em;

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getContryList() {
		logger.info("Method : getCountryList Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("getCountryList").getResultList();
			if(!Util.isNull(x)) {
				for (Object[] m : x) {
					CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], m[2].toString());
					countryList.add(dropDownModel);
				}
				Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.success, "Data Fetched Successfully");
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, "Unable to fetch Country list");
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}

		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getCountryList Dao ends");

		return response;
	}

}
