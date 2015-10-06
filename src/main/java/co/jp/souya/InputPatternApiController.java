package co.jp.souya;

import java.io.UnsupportedEncodingException;
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

import co.jp.souya.dto.InputPatternDTO;
import co.jp.souya.dto.TestCaseAdminDTO;
import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.ParametaValue;
import co.jp.souya.requestbody.ReqInputPattern;
import co.jp.souya.service.InputPatternSvc;
import co.jp.souya.service.ParametaValueSvc;
import co.jp.souya.service.TestCaseAdminSvc;
import co.jp.souya.tool.TTConst;
import co.jp.souya.tool.TTUtility;

/**
 * 入力パターン画面用API
 * @author hsatomi
 *
 */
@RestController
@RequestMapping(value = "/InputPattern/api")
public class InputPatternApiController {

	private static final Logger logger = LoggerFactory
			.getLogger(InputPatternApiController.class);

	@Autowired
	private TestCaseAdminSvc testCaseAdminSvc;

	@Autowired
	private InputPatternSvc inputPatternSvc;

	@Autowired
	private ParametaValueSvc parametaValueSvc;


	private static String BYNAME="By.name";
	private static String BYID="By.id";
	private static String BYCLASSNAME="By.className";
	private static String BYCSSSELECTOR="By.cssSelector";
	private static String BYLINKTEXT="By.linkText";
	private static String BYXPATH="By.xpath";


	/**
	 * 入力パターン　登録・更新
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean update(@RequestBody ReqInputPattern req)
			throws UnsupportedEncodingException {
		logger.info("update");

		// 入力パターン(新規)は自動でNoと名前をセット
		if(req.inputPattern.getId() == null){
			if(req.inputPattern.getテストケース管理id()==null) return false;
			Integer id_TestCase = req.inputPattern.getテストケース管理id();

			Integer maxNo = 0;
			TestCaseAdminDTO dto = testCaseAdminSvc.getDTO(id_TestCase);
			for (InputPattern inputPattern : dto.get入力パターンリスト()) {
				if(inputPattern.getNo()==null) continue;
				if(inputPattern.getNo() > maxNo){
					maxNo = inputPattern.getNo();
				}
			}
			maxNo++;

			req.inputPattern.setNo(maxNo);
			req.inputPattern.set入力パターン名("");
		}

		// 登録 or 更新
		req.inputPattern = inputPatternSvc.update(req.inputPattern);
		if (req.inputPattern == null)
			return false;

		for (ParametaValue parametaValue : req.list) {
			// 入力チェック・自動除外
			if (parametaValue.get実行順() == null) {
				continue;
			}
			if (parametaValue.getエレメント型() == null
					|| parametaValue.getエレメント型().isEmpty()) {
				continue;
			}

			parametaValue.set入力パターンid(req.inputPattern.getId());
			parametaValue = parametaValueSvc.update(parametaValue);
			if (parametaValue == null)
				return false;
		}

		// 更新時は自動でリセット扱いとする(No16対応)
		{
			boolean result = inputPatternSvc.reset(req.inputPattern.getId());
			if(!result) return false;
		}

		return true;
	}

	/**
	 * 入力パターン　削除
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean delete(@RequestBody ReqInputPattern req)
			throws UnsupportedEncodingException {
		logger.info("delete");

		if(req.parametaValueId==null) return false;
		boolean result = parametaValueSvc.delete(req.parametaValueId);
		return result;
	}

	/**
	 * JUNIT4 Javaコードを解析してパラメータ化する
	 * <img src="./doc-files/analyzeSelenium.gif"/>
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/analyze", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean analyze(@RequestBody ReqInputPattern req)
			throws UnsupportedEncodingException {
		logger.info("analyze");

		// 解析
		if(req.seleniumCode==null) return false;
		if(req.seleniumCode.isEmpty()) return false;


		int n実行順 = 0;
		{
			InputPatternDTO dto = inputPatternSvc.getDTO(req.inputPattern.getId());
			List<ParametaValue> list = dto.getパラメタ値リスト();
			for (ParametaValue parametaValue : list) {
				Integer _n実行順 = parametaValue.get実行順();
				if(_n実行順 == null) _n実行順 = 0;
				if( _n実行順 > n実行順 ){
					n実行順 = _n実行順;
				}
			}
		}
		n実行順++;

		List<String> codeList = TTUtility.splitByLineSeparator(req.seleniumCode);
		for (String code : codeList) {

			ParametaValue dao = new ParametaValue();
			dao.set入力パターンid(req.inputPattern.getId());
			dao.set備考("SeleniumIDE解析結果");

			{
				String strSelector="";
				try{
					//検索	driver\.findElement\(.+\)\.
					strSelector = TTUtility.fetchPatternChar("driver\\.findElement\\(.+\\)\\.", code);
				}catch(Exception e){
					e.printStackTrace();
				}
				if(strSelector.isEmpty()) continue;

				try{
					//除去	driver\.findElement\(
					strSelector = TTUtility.removePatternChar("driver\\.findElement\\(", strSelector);
				}catch(Exception e){
				}
				if(strSelector.isEmpty()) continue;

				try{
					//除去	\)\.
					strSelector = TTUtility.removePatternChar("\\)\\.", strSelector);
				}catch(Exception e){
				}
				if(strSelector.isEmpty()) continue;

				{
					//エレメント型
					String strSelectorBy="";
					try{
						//検索	By\.[^(]+
						strSelectorBy = TTUtility.fetchPatternChar("By\\.[^(]+", strSelector);
					}catch(Exception e){
					}
					if(strSelectorBy.isEmpty()) continue;


					//TODO:セレクターの文字列は定数化すること
					if(BYNAME.equals(strSelectorBy)){
						dao.setエレメント型(BYNAME);
					}
					if(BYCSSSELECTOR.equals(strSelectorBy)){
						dao.setエレメント型(BYCSSSELECTOR);
					}
					if(BYLINKTEXT.equals(strSelectorBy)){
						dao.setエレメント型(BYLINKTEXT);
					}
					if(BYID.equals(strSelectorBy)){
						dao.setエレメント型(BYID);
					}
					if(BYCLASSNAME.equals(strSelectorBy)){
						dao.setエレメント型(BYCLASSNAME);
					}
					if(BYXPATH.equals(strSelectorBy)){
						dao.setエレメント型(BYXPATH);
					}
				}

				{
					//エレメント名
					String strSelectorName="";
					try{
						//除去	By\.[^(]+\("
						strSelectorName = TTUtility.removePatternChar("By\\.[^(]+\\(\"", strSelector);
					}catch(Exception e){
					}
					try{
						//除去	\"\)$
						strSelectorName = TTUtility.removePatternChar("\\\"\\)$", strSelectorName);
					}catch(Exception e){
					}
					if(strSelectorName.isEmpty()) continue;
					//TODO:エンコードは不要か？
					dao.setエレメント名(strSelectorName);
				}

			}


			{
				String strMethod = "";
				try{
					strMethod = TTUtility.removePatternChar("driver\\.findElement\\(.+\\)\\.", code);
				}catch(Exception e){
				}
				if(strMethod.isEmpty()) continue;

				{
					//アクション
					String strAction = "";
					try{
						//検索	^[^(]+
						strAction = TTUtility.fetchPatternChar("^[^(]+", strMethod);
					}catch(Exception e){
					}
					if(strAction.isEmpty()) continue;

					if(TTConst.ACTION_CLEAR.equals(strAction))
					{
						//クリアは保存しない
						continue;
					}
					dao.setアクション(strAction);
				}

				if(TTConst.ACTION_SENDKEYS.equals(dao.getアクション()))
				{
					//値
					String strValue="";
					try{
						//除去	.+\(\"
						strValue = TTUtility.removePatternChar(".+\\(\\\"", strMethod);
					}catch(Exception e){
					}
					if(strValue.isEmpty()) continue;
					try{
						//除去	\s+$
						strValue = TTUtility.removePatternChar("\\s+$", strValue);
					}catch(Exception e){
					}
					if(strValue.isEmpty()) continue;
					try{
						//除去	\"\);$
						strValue = TTUtility.removePatternChar("\\\"\\);$", strValue);
					}catch(Exception e){
					}
					if(strValue.isEmpty()) continue;
					dao.set値(strValue);
				}
			}

			//登録
			dao.set実行順(n実行順);
			n実行順++;
			dao = parametaValueSvc.update(dao);

			logger.debug("登録　n実行順=" + n実行順);
		}



		return true;
	}


}
