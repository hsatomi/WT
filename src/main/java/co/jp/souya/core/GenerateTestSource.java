package co.jp.souya.core;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.jp.souya.jpa.DisplayAdmin;
import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.MovePatternAdmin;
import co.jp.souya.jpa.MovePatternDetail;
import co.jp.souya.jpa.ParametaValue;
import co.jp.souya.jpa.ProjectAdmin;
import co.jp.souya.jpa.TestCaseAdmin;
import co.jp.souya.service.DaoSvc;
import co.jp.souya.tool.TTConst;

@Service
public class GenerateTestSource {
	private static final Logger logger = LoggerFactory
			.getLogger(GenerateTestSource.class);

	private String sep = System.lineSeparator();

	public GenerateTestSource() {
		logger.info(this.getClass().getName());
	}

	@Autowired
	private DaoSvc daoSvc;


	/**
	 * Git Pushをする
	 * @return
	 */
	public boolean gitpush() {
		boolean result = false;

		try {
			Runtime r = Runtime.getRuntime();
			Process process = r.exec("C:\\Users\\hsatomi\\git\\wt\\auto_git_sh.cmd");

			process.waitFor();
			int ret = process.exitValue();
			System.out.println("戻り値：" + ret);



		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return result;
	}


	/**
	 * 指定された設定でユニットテストコードを自動生成する
	 * @param id
	 * @param input_ids
	 * @return
	 */
	public boolean generate(Integer id, List<Integer> input_ids) {
		boolean result = false;

		try{
			// ----------------必要情報取得----------------
			// TODO: EntityRelationから自動取得を実装できればよい
			TestCaseAdmin daoテストケース管理
			= daoSvc.getTestCaseAdmin(id);
			MovePatternAdmin dao遷移パターン管理
			= daoSvc.getMovePatternAdmin(daoテストケース管理.get遷移パターン管理id());
			DisplayAdmin dao画面管理
			= daoSvc.getDisplayAdmin(daoテストケース管理.get画面管理id());
			ProjectAdmin daoプロジェクト管理
			= daoSvc.getProjectAdmin(dao画面管理.getプロジェクトid());
			//TODO:順に取得していることを確認
			List<MovePatternDetail> dao遷移パターン明細リスト
			= daoSvc.getMovePatternDetailList(dao遷移パターン管理.getId());
			List<InputPattern> dao入力パターンリスト = new ArrayList<InputPattern>();
			for (Integer id_input : input_ids) {
				InputPattern dao = daoSvc.getInputPattern(id_input);
				dao入力パターンリスト.add(dao);
			}


			// ----------------クラス構築----------------
			String strGenerateCls = getHinagata();
			String strプロジェクト名;
			{
				//プロジェクト名生成
				strプロジェクト名 = "project" + daoプロジェクト管理.getId();
				strGenerateCls = strGenerateCls.replace(
						"co.jp.souya.prototype",
						"co.jp.souya." + strプロジェクト名
						);
			}
			String strクラス名;
			{
				//クラス名生成
				long nケース管理番号 = 10000*daoプロジェクト管理.getId()
						+1*daoテストケース管理.getId();
				strクラス名 = "Case" + nケース管理番号;
				strGenerateCls = strGenerateCls.replace(
						"SeleniumHinagata",
						strクラス名
						);
			}
			{
				//初期化 -> 置換
				//TODO:実装
			}
			{
				//DB初期化 -> 置換
				//TODO:実装
			}
			{
				//画面遷移 -> 置換
				StringBuffer strReplace = new StringBuffer();
				strReplace.append("//画面遷移");
				strReplace.append(sep);
				boolean doInitialURL = false;
				for (MovePatternDetail movePatternDetail : dao遷移パターン明細リスト) {
					String url = movePatternDetail.getUrl();
					strReplace.append("		//" + movePatternDetail.get画面タイトル());
					strReplace.append(sep);
					if(url!=null && !"".equals(url) && !doInitialURL){
						strReplace.append("		webdriver.get(\"" + url + "\");");
						strReplace.append(sep);
						doInitialURL = true;
					}
					//TODO:実行順に取得していることを確認
					List<ParametaValue> daoパラメタ値リスト=
					daoSvc.getParametaValueList(movePatternDetail.get入力パターンid());
					for (ParametaValue parametaValue : daoパラメタ値リスト) {
						strReplace.append("		{");
						strReplace.append(sep);
						strReplace.append("			//" + parametaValue.get項目名());
						strReplace.append(sep);
						strReplace
								.append("			WebElement element = webdriver.findElement("
										+ parametaValue.getエレメント型()
										+ "(\""
										+ parametaValue.getエレメント名() + "\"));");
						strReplace.append(sep);
						if(TTConst.ACTION_SENDKEYS.equals(parametaValue.getアクション())){
							strReplace.append("			element.sendKeys(\"" + parametaValue.get値() + "\");");
							strReplace.append(sep);
						}
						if(TTConst.ACTION_CLICK.equals(parametaValue.getアクション())){
							strReplace.append("			element.click();");
							strReplace.append(sep);
						}
						strReplace.append("		}");
						strReplace.append(sep);
					}
				}
				strGenerateCls = strGenerateCls.replace(
						"//画面遷移",
						strReplace.toString()
						);
			}
			{
				//テストケース -> 置換
				StringBuffer strReplace = new StringBuffer();
				strReplace.append("	//テストケース開始");
				strReplace.append(sep);
				for (InputPattern inputPattern : dao入力パターンリスト) {
					strReplace.append("	@Test");
					strReplace.append(sep);
					strReplace.append("	public void Test" + inputPattern.getNo() + "() throws UnsupportedEncodingException{");
					strReplace.append(sep);
					//---------実行---------
					{
						strReplace.append(sep);
						strReplace.append("		//実行");
						strReplace.append(sep);
						strReplace.append("		boolean bTestResult = true;");
						strReplace.append(sep);
						//TODO:実行順に取得していることを確認
						List<ParametaValue> daoパラメタ値リスト=
						daoSvc.getParametaValueList(inputPattern.getId());
						for (ParametaValue parametaValue : daoパラメタ値リスト) {
							strReplace.append("		{");
							strReplace.append(sep);
							strReplace.append("			//" + parametaValue.get項目名());
							strReplace.append(sep);
							strReplace
									.append("			WebElement element = webdriver.findElement("
											+ parametaValue.getエレメント型()
											+ "(\""
											+ parametaValue.getエレメント名() + "\"));");
							strReplace.append(sep);
							if(TTConst.ACTION_SENDKEYS.equals(parametaValue.getアクション())){
								strReplace.append("			element.sendKeys(\"" + parametaValue.get値() + "\");");
								strReplace.append(sep);
							}
							if(TTConst.ACTION_CLICK.equals(parametaValue.getアクション())){
								strReplace.append("			element.click();");
								strReplace.append(sep);
							}
							strReplace.append("		}");
							strReplace.append(sep);
						}
					}
					//---------実行後スナップショット取得---------
					{
						strReplace.append(sep);
						strReplace.append("		// 実行後スナップショット取得");
						strReplace.append(sep);
						strReplace.append("		File file = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);");
						strReplace.append(sep);
						strReplace.append("		String strSnapshot = TTUtility.getBase64String(file);");
						strReplace.append(sep);
						strReplace.append("");
						strReplace.append(sep);
					}
					//---------WEB状態取得・比較---------
					{
						strReplace.append(sep);
						strReplace.append("		//web状態取得・比較");
						strReplace.append(sep);
						strReplace.append("		String strResultWeb = webdriver.getPageSource();");
						strReplace.append(sep);
						if(inputPattern.get実行回数()<=0){
							//初回
							strReplace.append("");
							strReplace.append(sep);
							strReplace.append("");
							strReplace.append(sep);
						}else{
							//2回目以降
							strReplace.append("		String strExpectWebEncoded = \"" + inputPattern.getHtml正解() + "\";");
							strReplace.append(sep);
							strReplace.append("		String strExpectWeb = URLDecoder.decode(strExpectWebEncoded, \"UTF-8\");");
							strReplace.append(sep);
							strReplace.append("		String strWebDif = TTUtility.validateWeb(strResultWeb, strExpectWeb);");
							strReplace.append(sep);
							strReplace.append("		if(!strWebDif.isEmpty()) bTestResult=false;");
							strReplace.append(sep);
							strReplace.append("");
							strReplace.append(sep);
						}

					}
					//---------DB状態取得・比較---------
					{
						strReplace.append(sep);
						strReplace.append("		//DB状態取得・比較");
						strReplace.append(sep);
						strReplace.append("		String strResultDB = \"\";");
						strReplace.append(sep);
						if(inputPattern.get実行回数()<=0){
							//初回
							strReplace.append("");
							strReplace.append(sep);
							strReplace.append("");
							strReplace.append(sep);
						}else{
							//2回目以降
							strReplace.append("");
							strReplace.append(sep);
							strReplace.append("");
							strReplace.append(sep);
						}
					}
					//---------正解値更新---------
					if(inputPattern.get実行回数()<=0)
					{
						//初回
						strReplace.append(sep);
						strReplace.append("		// 正解値更新");
						strReplace.append(sep);
						strReplace.append("		try {");
						strReplace.append(sep);
						strReplace.append("			URI url = new URI(\"" + TTConst.URL_API_BASE + TTConst.URL_UPDATE_RESULT + "\");");
						strReplace.append(sep);
						strReplace.append("			JSONObject request = new JSONObject();");
						strReplace.append(sep);
						strReplace.append("			request.put(\"id\", " + inputPattern.getId() + ");");
						strReplace.append(sep);
						strReplace.append("			request.put(\"html\", URLEncoder.encode(strResultWeb, \"UTF-8\"));");
						strReplace.append(sep);
						strReplace.append("			request.put(\"db\", URLEncoder.encode(strResultDB, \"UTF-8\"));");
						strReplace.append(sep);
						strReplace.append("");
						strReplace.append(sep);
						strReplace.append("			HttpEntity<String> entity = new HttpEntity<String>(");
						strReplace.append(sep);
						strReplace.append("					request.toString(), headers);");
						strReplace.append(sep);
						strReplace.append("");
						strReplace.append(sep);
						strReplace.append("			System.out.println(\"URL: \" + url);");
						strReplace.append(sep);
						strReplace.append("			String response = restTemplate.postForObject(url, entity,");
						strReplace.append(sep);
						strReplace.append("					String.class);");
						strReplace.append(sep);
						strReplace.append("			System.out.println(\"Response: \" + response);");
						strReplace.append(sep);
						strReplace.append("");
						strReplace.append(sep);
						strReplace.append("		} catch (Exception e) {");
						strReplace.append(sep);
						strReplace.append("			e.printStackTrace();");
						strReplace.append(sep);
						strReplace.append("			assertTrue(false);");
						strReplace.append(sep);
						strReplace.append("		}");
						strReplace.append(sep);
						strReplace.append("");
						strReplace.append(sep);
						strReplace.append("");
						strReplace.append(sep);
						strReplace.append("");
						strReplace.append(sep);
					}
					//---------JOB状況更新---------
					{
						strReplace.append(sep);
						strReplace.append("		// 結果更新");
						strReplace.append(sep);
						strReplace.append("		try {");
						strReplace.append(sep);
						strReplace.append("			URI url = new URI(\"" + TTConst.URL_API_BASE + TTConst.URL_UPDATE_TEST_RESULT + "\");");
						strReplace.append(sep);
						strReplace.append("			JSONObject request = new JSONObject();");
						strReplace.append(sep);
						strReplace.append("			request.put(\"id\", " + inputPattern.getId() + ");");
						strReplace.append(sep);
						strReplace.append("			request.put(\"execTimes\", " + (inputPattern.get実行回数()+1) + ");");
						strReplace.append(sep);
						strReplace.append("			request.put(\"html\", URLEncoder.encode(strResultWeb, \"UTF-8\"));");
						strReplace.append(sep);
						strReplace.append("			request.put(\"db\", URLEncoder.encode(strResultDB, \"UTF-8\"));");
						strReplace.append(sep);
						strReplace.append("			request.put(\"jobStatus\", URLEncoder.encode(TTConst.JOB_STATUS_FINISH, \"UTF-8\"));");
						strReplace.append(sep);
						strReplace.append("			request.put(\"testResult\", bTestResult ? TTConst.TEST_RESULT_OK : TTConst.TEST_RESULT_NG);");
						strReplace.append(sep);
						strReplace.append("			request.put(\"snapshot\", strSnapshot);");
						strReplace.append(sep);
						strReplace.append("");
						strReplace.append(sep);
						strReplace.append("			HttpEntity<String> entity = new HttpEntity<String>(");
						strReplace.append(sep);
						strReplace.append("					request.toString(), headers);");
						strReplace.append(sep);
						strReplace.append("");
						strReplace.append(sep);
						strReplace.append("			System.out.println(\"URL: \" + url);");
						strReplace.append(sep);
						strReplace.append("			String response = restTemplate.postForObject(url, entity,");
						strReplace.append(sep);
						strReplace.append("					String.class);");
						strReplace.append(sep);
						strReplace.append("			System.out.println(\"Response: \" + response);");
						strReplace.append(sep);
						strReplace.append("");
						strReplace.append(sep);
						strReplace.append("		} catch (Exception e) {");
						strReplace.append(sep);
						strReplace.append("			e.printStackTrace();");
						strReplace.append(sep);
						strReplace.append("			assertTrue(false);");
						strReplace.append(sep);
						strReplace.append("		}");
						strReplace.append(sep);
						strReplace.append("");
						strReplace.append(sep);
						strReplace.append("");
						strReplace.append(sep);
						strReplace.append("");
						strReplace.append(sep);
					}
					strReplace.append("	}");
					strReplace.append(sep);
				}
				strReplace.append("	//テストケース終了");
				strReplace.append(sep);
				strGenerateCls = strGenerateCls.replace(
						"//テストケース",
						strReplace.toString()
						);
			}

			logger.info(strGenerateCls);

			try {
				//クラスファイル出力
				FileWriter fw = new FileWriter(TTConst.PATH_GENERATESRC_OUTPUT + strプロジェクト名 + "\\" + strクラス名 +  ".java");
				fw.write(strGenerateCls);
				fw.close();
			} catch (IOException ioe) {
				logger.error(ioe.getMessage(),ioe);
				return false;
			}

		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return false;

		}

		return result;
	}


	private String getHinagata(){
		StringBuffer strbuf = new StringBuffer();
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("package co.jp.souya.prototype;");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("import static org.junit.Assert.*;");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("import java.io.File;");
		strbuf.append(sep);
		strbuf.append("import java.io.UnsupportedEncodingException;");
		strbuf.append(sep);
		strbuf.append("import java.net.URI;");
		strbuf.append(sep);
		strbuf.append("import java.net.URLDecoder;");
		strbuf.append(sep);
		strbuf.append("import java.net.URLEncoder;");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("import org.json.JSONObject;");
		strbuf.append(sep);
		strbuf.append("import org.junit.After;");
		strbuf.append(sep);
		strbuf.append("import org.junit.AfterClass;");
		strbuf.append(sep);
		strbuf.append("import org.junit.Before;");
		strbuf.append(sep);
		strbuf.append("import org.junit.BeforeClass;");
		strbuf.append(sep);
		strbuf.append("import org.junit.Test;");
		strbuf.append(sep);
		strbuf.append("import org.openqa.selenium.By;");
		strbuf.append(sep);
		strbuf.append("import org.openqa.selenium.OutputType;");
		strbuf.append(sep);
		strbuf.append("import org.openqa.selenium.TakesScreenshot;");
		strbuf.append(sep);
		strbuf.append("import org.openqa.selenium.WebDriver;");
		strbuf.append(sep);
		strbuf.append("import org.openqa.selenium.WebElement;");
		strbuf.append(sep);
		strbuf.append("import org.openqa.selenium.firefox.FirefoxDriver;");
		strbuf.append(sep);
		strbuf.append("import org.springframework.http.HttpEntity;");
		strbuf.append(sep);
		strbuf.append("import org.springframework.http.HttpHeaders;");
		strbuf.append(sep);
		strbuf.append("import org.springframework.http.MediaType;");
		strbuf.append(sep);
		strbuf.append("import org.springframework.web.client.RestTemplate;");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("import co.jp.souya.tool.TTConst;");
		strbuf.append(sep);
		strbuf.append("import co.jp.souya.tool.TTUtility;");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("public class SeleniumHinagata {");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("	public SeleniumHinagata() {");
		strbuf.append(sep);
		strbuf.append("	}");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("	private static WebDriver webdriver;");
		strbuf.append(sep);
		strbuf.append("	private static HttpHeaders headers;");
		strbuf.append(sep);
		strbuf.append("	private static RestTemplate restTemplate;");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("	@BeforeClass");
		strbuf.append(sep);
		strbuf.append("	public static  void doBeforeCls(){");
		strbuf.append(sep);
		strbuf.append("		//初期化");
		strbuf.append(sep);
		strbuf.append("		headers = new HttpHeaders();");
		strbuf.append(sep);
		strbuf.append("		headers.setContentType(MediaType.APPLICATION_JSON);");
		strbuf.append(sep);
		strbuf.append("		restTemplate = new RestTemplate();");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("		//永続化マネージャの生成等");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("	}");
		strbuf.append(sep);
		strbuf.append("	@Before");
		strbuf.append(sep);
		strbuf.append("	public void doBefore(){");
		strbuf.append(sep);
		strbuf.append("		//DB初期化");
		strbuf.append(sep);
		strbuf.append("		//import用スクリプトをキックする、など要検討");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("    	webdriver = new FirefoxDriver();");
		strbuf.append(sep);
		strbuf.append("		//画面遷移");
		strbuf.append(sep);
		strbuf.append("	}");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("	//テストケース");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("	@After");
		strbuf.append(sep);
		strbuf.append("	public void doAfter(){");
		strbuf.append(sep);
		strbuf.append("		//WebDriver終了");
		strbuf.append(sep);
		strbuf.append("		webdriver.quit();");
		strbuf.append(sep);
		strbuf.append("	}");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("	@AfterClass");
		strbuf.append(sep);
		strbuf.append("	public static void aftCls(){");
		strbuf.append(sep);
		strbuf.append("		//インスタンス破棄等");
		strbuf.append(sep);
		strbuf.append("	}");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("}");

		return strbuf.toString();
	}



}
