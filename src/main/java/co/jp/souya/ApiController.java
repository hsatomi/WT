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
import co.jp.souya.jpa.TestCaseAdmin;
import co.jp.souya.requestbody.ReqTestCaseAdminGenerate;
import co.jp.souya.requestbody.ReqUpdateTestResult;
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
	public boolean updateTestResult(@RequestBody ReqUpdateTestResult req) {
		logger.info("updateTestResult");
		boolean result = false;
		result = inputPatternSvc.updateTestResult(req.id, req.jobStatus, req.testResult);
		return result;
	}

	@RequestMapping(value = "/generateTestCase", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean generateTestCase(@RequestBody ReqTestCaseAdminGenerate req) {
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

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public TestCaseAdmin test(@RequestBody ReqUpdateTestResult req) {

		TestCaseAdmin ad = new TestCaseAdmin();
		return ad;
	}
	@RequestMapping(value = "/test2", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean test2(@RequestBody ReqUpdateTestResult req) {
		return true;
	}


}
