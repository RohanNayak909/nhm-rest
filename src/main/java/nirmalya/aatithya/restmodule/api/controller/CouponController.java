package nirmalya.aatithya.restmodule.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.api.dao.CouponDao;
import nirmalya.aatithya.restmodule.api.model.CouponApiModel;
import nirmalya.aatithya.restmodule.api.model.CureEasySpecializationListAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyTestListAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyLabTestModel;
import nirmalya.aatithya.restmodule.api.model.PrescriptionModel;
import nirmalya.aatithya.restmodule.api.model.WalletModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.util.DocumentUpload;

@RestController
@RequestMapping(value = "api")
public class CouponController {
	@Autowired
	EnvironmentVaribles env;

	@Autowired
	DocumentUpload documentUpload;

	@Autowired
	CouponDao couponDao;

	Logger logger = LoggerFactory.getLogger(CouponController.class);
	
	// Coupon List
	@GetMapping(value = "/get-CouponList")
	public JsonResponse<List<CouponApiModel>> getCouponList(@RequestParam String userid) {
		logger.info("Method :view getCouponList starts");
		logger.info("Method :view getCouponList ends");
		return couponDao.getCouponList(userid);
	}

	// Coupon List
		@GetMapping(value = "/get-CouponCode")
		public JsonResponse<List<CouponApiModel>> getCouponCode(@RequestParam String userid,@RequestParam String couponCode) {
			logger.info("Method :view getCouponCode starts");
			logger.info("Method :view getCouponCode ends");
			return couponDao.getCouponCode(userid,couponCode);
		}
		// Wallet Price
		@GetMapping(value = "/get-wallet-price")
		public ResponseEntity<JsonResponse<WalletModel>> getWalletPrice(@RequestParam String userId) {
			logger.info("Method :view getWalletPrice starts");
			logger.info("Method :view getWalletPrice ends");
			return couponDao.getWalletPrice(userId);
		}
}
