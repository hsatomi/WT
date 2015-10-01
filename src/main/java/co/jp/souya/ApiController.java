package co.jp.souya;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

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
import co.jp.souya.dto.TestCaseAdminDTO;
import co.jp.souya.jpa.InputPattern;
import co.jp.souya.requestbody.ReqTestCaseAdminGenerate;
import co.jp.souya.requestbody.ReqUpdateTestResult;
import co.jp.souya.service.DaoSvc;
import co.jp.souya.service.InputPatternSvc;
import co.jp.souya.service.TestCaseAdminSvc;
import co.jp.souya.tool.GetIdwithNekoHTML;
import co.jp.souya.tool.TTConst;
import co.jp.souya.tool.TTUtility;

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
	 * テスト結果をアップデートする 補足：テストユニットから呼び出される
	 *
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/updateTestResult", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean updateTestResult(@RequestBody ReqUpdateTestResult req)
			throws UnsupportedEncodingException {
		logger.info("updateTestResult");

		if (req.id == null)
			return false;
		InputPattern dao = inputPatternSvc.get(req.id);
		if (dao == null)
			return false;

		// 状態評価を全てAPI側で判定する
		{
			if (dao.get実行回数() <= 0) {
				// 初回のみ正解値を格納
				dao = inputPatternSvc.updateResult(req.id, req.html,
						req.db,req.snapshot);
			}
			boolean bTestResult=true;
			if(dao.getHtml正解()==null) dao.setHtml正解("");
			if(dao.getDb正解()==null) dao.setDb正解("");
			if(req.html==null) req.html = "";
			if(req.db==null) req.db = "";

			String strExpectWeb = URLDecoder.decode(dao.getHtml正解(), "UTF-8");
			String strResultWeb = URLDecoder.decode(req.html, "UTF-8");
			String strWebDif = TTUtility.validateWeb(strExpectWeb, strResultWeb);
			if(!strWebDif.isEmpty()) bTestResult=false;

			String strExpectDB = URLDecoder.decode(dao.getDb正解(), "UTF-8");
			String strResultDB = URLDecoder.decode(req.db, "UTF-8");
			String strDBDif = TTUtility.validateWeb(strExpectDB, strResultDB);
			if(!strDBDif.isEmpty()) bTestResult=false;

			req.jobStatus = TTConst.JOB_STATUS_FINISH;
			req.html_dif = URLEncoder.encode(strWebDif, "UTF-8");
			req.db_dif = URLEncoder.encode(strDBDif, "UTF-8");
			req.testResult = bTestResult ? TTConst.TEST_RESULT_OK : TTConst.TEST_RESULT_NG;
		}

		InputPattern newdao = inputPatternSvc.updateTestResult(req.id,
				req.testResult, req.jobStatus, req.snapshot, req.html, req.db,
				req.html_dif, req.db_dif);

		if (newdao == null)
			return false;
		return true;
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
			if (!result)
				return false;
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
		result = inputPatternSvc.updateTestStatus(req.input_ids,
				TTConst.JOB_STATUS_START);

		return result;
	}

	/**
	 * テストユニットを実行する（jenkins job起動）
	 *
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

		result = inputPatternSvc.updateTestStatus(req.input_ids,
				TTConst.JOB_STATUS_EXEC);
		result = testCaseAdminSvc.execJenkins(req.id);

		return result;
	}

	/**
	 * Jenkinsジョブ状態をポーリングする 状態が何か変更すれば応答をクライアントに返す ajaxからの利用を前提とする
	 *
	 * @param req
	 * @return
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/pollingJenkins", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean pollingJenkins(@RequestBody ReqTestCaseAdminGenerate req)
			throws InterruptedException {
		logger.info("pollingJenkins");
		boolean result = false;
		if (req == null || req.id == null) {
			logger.warn("idがnull");
			return false;
		}

		// 3秒待機
		Thread.sleep(3000);

		int counter = 0;
		while (true) {
			TestCaseAdminDTO dto = testCaseAdminSvc.getDTO(req.id);
			int cnt実行中 = 0;
			for (InputPattern inputPattern : dto.get入力パターンリスト()) {
				if (TTConst.JOB_STATUS_EXEC.equals(inputPattern.getJob状況())) {
					cnt実行中++;
				}
			}
			if (cnt実行中 == 0) {
				break;
			}

			// 3秒待機
			Thread.sleep(3000);

			counter++;
			if (counter > 30) {
				logger.warn("TIMEOUT! JOB状態待機が指定回数を超過しました");
				break;
			}
		}

		result = true;
		return result;
	}


	@RequestMapping(value = "/analyze", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean analyze(@RequestBody ReqTestCaseAdminGenerate id)
			throws InterruptedException {
		logger.info("analyze");

		try {
			GetIdwithNekoHTML neko = new GetIdwithNekoHTML();
			@SuppressWarnings("unused")
			List<List<String>> list = neko.getId("http://192.168.0.142:8080/ts/");

			logger.info("a");


		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return true;

	}




}
