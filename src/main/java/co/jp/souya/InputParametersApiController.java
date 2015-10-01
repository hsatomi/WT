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

import co.jp.souya.jpa.ParametaValue;
import co.jp.souya.requestbody.ReqInputParameters;
import co.jp.souya.service.InputPatternSvc;
import co.jp.souya.service.ParametaValueSvc;
import co.jp.souya.tool.TTUtility;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "/InputParameters/api")
public class InputParametersApiController {

	private static final Logger logger = LoggerFactory
			.getLogger(InputParametersApiController.class);

	@Autowired
	private InputPatternSvc inputPatternSvc;

	@Autowired
	private ParametaValueSvc parametaValueSvc;

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean update(@RequestBody ReqInputParameters req)
			throws UnsupportedEncodingException {
		logger.info("update");

		// 登録 or 更新
		req.inputPattern = inputPatternSvc.update(req.inputPattern);
		if (req.inputPattern == null)
			return false;

		for (ParametaValue parametaValue : req.list) {
			// 入力チェック・自動除外
			if (parametaValue.getId() == null) {
				if (parametaValue.getエレメント型() == null
						|| parametaValue.getエレメント型().isEmpty()) {
					continue;
				}
//				if (parametaValue.getエレメント名() == null
//						|| parametaValue.getエレメント名().isEmpty()) {
//					continue;
//				}
//				if (parametaValue.getアクション() == null
//						|| parametaValue.getアクション().isEmpty()) {
//					continue;
//				}
			}
			// TODO:削除ロジック

			parametaValue.set入力パターンid(req.inputPattern.getId());
			parametaValue = parametaValueSvc.update(parametaValue);
			if (parametaValue == null)
				return false;
		}

		return true;
	}

	@RequestMapping(value = "/analyze", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean analyze(@RequestBody ReqInputParameters req)
			throws UnsupportedEncodingException {
		logger.info("analyze");

		// 解析
		if(req.seleniumCode==null) return false;
		if(req.seleniumCode.isEmpty()) return false;

		int idx = 0;

		List<String> codeList = TTUtility.splitByLineSeparator(req.seleniumCode);
		for (String code : codeList) {

			ParametaValue dao = new ParametaValue();
			dao.set入力パターンid(req.inputPattern.getId());
			dao.set備考("SeleniumIDE解析結果");


			String t="";
			try{
				t = TTUtility.fetchPatternChar("driver\\.findElement\\(By.+", code);
			}catch(Exception e){
			}
			if(t.isEmpty()) continue;

			try{
				t = TTUtility.fetchPatternChar("By\\..+", code);
			}catch(Exception e){
			}
			if(t.isEmpty()) continue;

			//By部分切り出し
			String by="";
			try{
				by = TTUtility.fetchPatternChar("By\\..+?\\(", t);
			}catch(Exception e){
			}
			if(by.isEmpty()) continue;


			//TODO:セレクターの文字列は定数化すること
			if("By.name(".equals(by)){
				dao.setエレメント型("By.name");
			}
			if("By.cssSelector(".equals(by)){
				dao.setエレメント型("By.cssSelector");
			}
			if("By.linkText(".equals(by)){
				dao.setエレメント型("By.linkText");
			}
			if("By.id(".equals(by)){
				dao.setエレメント型("By.id");
			}
			if("By.className(".equals(by)){
				dao.setエレメント型("By.className");
			}


			//要素名以降切り出し
			try{
				t = TTUtility.removePatternChar("By\\..+?\\(", t);
			}catch(Exception e){
			}
			if(t.isEmpty()) continue;

			String name="";
			try{
				name = TTUtility.fetchPatternChar("\\\".+?\\\"", t);
			}catch(Exception e){
			}
			name = TTUtility.removePatternChar("\"", name);
			if(name.isEmpty()) continue;

			dao.setエレメント名(name);



			//動詞部以降切り出し
			try{
				t = TTUtility.removePatternChar("\\\".+?\\\"\\)\\)", t);
			}catch(Exception e){
			}
			if(t.isEmpty()) continue;

			t = t.substring(1, t.length()-1);
			if(t.isEmpty()) continue;


			//TODO: clear() は無視することで大丈夫か確認
			if("clear()".equals(t)){
				continue;
			}



			//Action部分切り出し
			String action="";
			try{
				action = TTUtility.fetchPatternChar(".+?\\(", t);
			}catch(Exception e){
			}
			if(action.isEmpty()) continue;


			//TODO:セレクターの文字列は定数化すること
			if("sendKeys(".equals(action)){
				dao.setアクション("sendKeys");
			}
			if("click(".equals(action)){
				dao.setアクション("click");
			}


			//切り出し
			try{
				t = TTUtility.removePatternChar(".+?\\(", t);
			}catch(Exception e){
			}
			if(t.isEmpty()) continue;



			//値部分切り出し
			String value="";
			try{
				value = TTUtility.fetchPatternChar("\\\".+", t);
				if(!value.isEmpty()){
					value = value.substring(1, value.length()-2);
				}
			}catch(Exception e){
			}
			dao.set値(value);



			//登録
			dao.set実行順(idx);
			idx++;

			dao = parametaValueSvc.update(dao);

			logger.debug(t);

		}



		return true;
	}


}
