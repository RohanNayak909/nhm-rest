package nirmalya.aatithya.restmodule.districtlevel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.model.RestAdminBlockModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestAdminChangePasswordDao;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestDistrictBlockListDao;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistBlockModel;

@RestController
@RequestMapping(value = "districtlevel/")
public class RestDistrictBlockListController {
	Logger logger = LoggerFactory.getLogger(RestDistrictBlockListController.class);

	@Autowired
	RestDistrictBlockListDao restDistrictBlockListDao;
	
	// View Details
				@GetMapping(value = "rest-viewBlockListDist")
				public JsonResponse<List<RestDistBlockModel>> viewBlockDetailsDist(@RequestParam Integer pageno,
						@RequestParam String district) {
					logger.info("Method :viewBlockDetailsDist starts");

					logger.info("Method :viewBlockDetailsDist endss");
					return restDistrictBlockListDao.viewBlockDetailsDist(pageno,district);

				}
				
				
				//edit
				@RequestMapping(value = "editBlockDetailsDist", method = { RequestMethod.GET })
				public ResponseEntity<JsonResponse<RestDistBlockModel>> editBlockDetailsDist(@RequestParam String id) {
					logger.info("Method : editBlockDetailsDist rest starts");

					logger.info("Method :editBlockDetailsDist rest ends");
					return restDistrictBlockListDao.editBlockDetailsDist(id);
				}
}
