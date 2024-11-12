package nirmalya.aatithya.restmodule.dropdown;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.api.model.CountryModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "dropdown/")
public class DropdownController {
	
	Logger logger = LoggerFactory.getLogger(DropdownController.class);
	
	@Autowired
	DropdownDao dropdownDao;
	
	@GetMapping(value = "get-country-lists")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getCountryList() {
		logger.info("getCountryList rest controller");
		return dropdownDao.getContryList();
	}
	

}
