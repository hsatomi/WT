package co.jp.souya;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.jp.souya.core.GenerateTestSource;
import co.jp.souya.requestbody.TestCaseAdminGenerate;
import co.jp.souya.requestbody.UpdateTestResult;
import co.jp.souya.service.InputPatternSvc;

/**
 * テストツール機能を外部から利用するためのAPIを提供する
 */
@RestController
@RequestMapping(value = "/api")
public class ApiController {

	private static final Logger logger = LoggerFactory
			.getLogger(ApiController.class);

	@Autowired
	private InputPatternSvc inputPatternSvc;

	@Autowired
	private GenerateTestSource generateTestSource;

	@RequestMapping(value = "/updateTestResult", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean updateTestResult(@RequestBody UpdateTestResult req) {
		logger.info("updateTestResult");
		boolean result = false;
		result = inputPatternSvc.updateTestResult(req.id, req.Job状況, req.判定結果);
		return result;
	}

	@RequestMapping(value = "/generateTestCase", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean generateTestCase(@RequestBody TestCaseAdminGenerate req) {
		logger.info("generateTestCase");
		boolean result = false;

		req.id=1;

		if (req == null || req.id == null) {
			logger.warn("idがnull");
			return false;
		}

		result = generateTestSource.generate(req.id,req.input_ids);
		if (!result)
			return false;

		return result;
	}

}
