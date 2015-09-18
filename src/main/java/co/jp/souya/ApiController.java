package co.jp.souya;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
import co.jp.souya.requestbody.ReqTestCaseAdminGenerate;
import co.jp.souya.requestbody.ReqUpdateTestResult;
import co.jp.souya.service.DaoSvc;
import co.jp.souya.service.InputPatternSvc;
import co.jp.souya.service.TestCaseAdminSvc;

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
	TestCaseAdminSvc testCaseAdminSvc;

	@Autowired
	private GenerateTestSource generateTestSource;

	@Autowired
	private DaoSvc daoSvc;


	/**
	 * テスト結果（正解値）をアップデートする テストケース初回のみ実行すること
	 * 補足：テストユニットから呼び出される
	 *
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/updateResult", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean updateResult(@RequestBody ReqUpdateTestResult req)
			throws UnsupportedEncodingException {
		logger.info("updateTestResult");
		boolean result = false;
		result = inputPatternSvc.updateResult(req.id, req.html, req.db);
		return result;
	}



	/**
	 * テスト結果をアップデートする
	 * 補足：テストユニットから呼び出される
	 *
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/updateTestResult", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean updateTestResult(@RequestBody ReqUpdateTestResult req)
			throws UnsupportedEncodingException {
		logger.info("updateResult");
		boolean result = false;
		// TODO:RestClient(on firefox)だと文字化けしないのに、RestTemplateだと文字化けするのでこの対応
		req.jobStatus = URLDecoder.decode(req.jobStatus, "UTF-8");
		result = inputPatternSvc.updateTestResult(req.id, req.execTimes, req.testResult,
				req.jobStatus, req.snapshot, req.html, req.db);
		return result;

	}

	/**
	 * テストケースをリセットする（回数リセット）
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/resetTestCase", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean resetTestCase(@RequestBody ReqTestCaseAdminGenerate req) {
		logger.info("resetTestCase");
		boolean result = false;
		if (req == null || req.id == null) {
			logger.warn("idがnull");
			return false;
		}

		for (Integer id : req.input_ids) {
			result = inputPatternSvc.reset(id);
			if(!result) return false;
		}

		return result;
	}


	/**
	 * テストユニットを自動生成する
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/generateTestCase", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean generateTestCase(@RequestBody ReqTestCaseAdminGenerate req) {
		logger.info("generateTestCase");
		boolean result = false;
		if (req == null || req.id == null) {
			logger.warn("idがnull");
			return false;
		}
		result = generateTestSource.generate(req.id, req.input_ids);

		result = generateTestSource.gitpush();

		result = inputPatternSvc.updateTestStatus(req.input_ids);

		return result;
	}


	/**
	 * jenkins jobを起動する
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/execJenkins", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean execJenkins(@RequestBody ReqTestCaseAdminGenerate req) {
		logger.info("execJenkins");
		boolean result = false;
		if (req == null || req.id == null) {
			logger.warn("idがnull");
			return false;
		}

		result = testCaseAdminSvc.execJenkins(req.id);

		return result;
	}







}
