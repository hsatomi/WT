
package co.jp.souya.project1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import co.jp.souya.tool.TTConst;
import co.jp.souya.tool.TTUtility;

public class Case10001 {

	public Case10001() {
	}

	private static WebDriver webdriver;
	private static HttpHeaders headers;
	private static RestTemplate restTemplate;

	@BeforeClass
	public static  void doBeforeCls(){
		//初期化
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate = new RestTemplate();

		//永続化マネージャの生成等

	}
	@Before
	public void doBefore(){
		//DB初期化
		//import用スクリプトをキックする、など要検討


    	webdriver = new FirefoxDriver();
		//画面遷移
		//ログイン画面
		webdriver.get("http://192.168.0.142:8080/ts/");
		{
			//ユーザ名
			WebElement element = webdriver.findElement(By.name("user"));
			element.sendKeys("test");
		}
		{
			//パスワード
			WebElement element = webdriver.findElement(By.name("pass"));
			element.sendKeys("password");
		}
		{
			//パスワード
			WebElement element = webdriver.findElement(By.name("btnSnd"));
			element.click();
		}
		//入力画面

	}

		//テストケース開始
	@Test
	public void Test1() throws UnsupportedEncodingException{

		//実行
		boolean bTestResult = true;
		{
			//ユーザ名
			WebElement element = webdriver.findElement(By.name("textfree"));
			element.sendKeys("TESTパターン1のテストです");
		}
		{
			//入力エリア適当
			WebElement element = webdriver.findElement(By.name("textinput"));
			element.sendKeys("test8394fj;xpw");
		}
		{
			//入力エリア適当
			WebElement element = webdriver.findElement(By.name("pass"));
			element.sendKeys("password");
		}
		{
			//パスワード
			WebElement element = webdriver.findElement(By.name("pass"));
			element.click();
		}

		// 実行後スナップショット取得
		File file = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		String strSnapshot = TTUtility.getBase64String(file);


		//web状態取得・比較
		String strResultWeb = webdriver.getPageSource();
		String strExpectWebEncoded = "%3C%21DOCTYPE+html+PUBLIC+%22-%2F%2FW3C%2F%2FDTD+XHTML+1.0+Transitional%2F%2FEN%22+%22http%3A%2F%2Fwww.w3.org%2FTR%2Fxhtml1%2FDTD%2Fxhtml1-transitional.dtd%22%3E%0D%0A%3Chtml+xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F1999%2Fxhtml%22%3E%3Chead%3E%0A%3Cmeta+content%3D%22text%2Fhtml%3B+charset%3Dutf-8%22+http-equiv%3D%22Content-Type%22+%2F%3E%0A%3Ctitle%3E%E3%83%86%E3%82%B9%E3%83%88%E7%94%A8web%E3%83%9A%E3%83%BC%E3%82%B8index%3C%2Ftitle%3E%0A%3C%2Fhead%3E%0A%0A%3Cbody%3E%0A%3Ctable+border%3D%222%22%3E%0A%3Ctbody%3E%3Ctr%3E%0A%3Ctd%3E%3Cp%3E%E3%83%86%E3%82%AD%E3%82%B9%E3%83%88%E3%82%A8%E3%83%AA%E3%82%A2+%3C%2Fp%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cp%3E%E8%A1%A8%E7%A4%BA%E3%82%A8%E3%83%AA%E3%82%A2%28%E6%9C%AA%E4%BD%9C%E6%88%90%29%3C%2Fp%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cp%3E%E3%83%86%E3%82%AD%E3%82%B9%E3%83%88%E5%85%A5%E5%8A%9B%E3%83%95%E3%82%A3%E3%83%BC%E3%83%AB%E3%83%89%3C%2Fp%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cp%3E%E3%83%91%E3%82%B9%E3%83%AF%E3%83%BC%E3%83%89%E5%85%A5%E5%8A%9B%E3%83%95%E3%82%A3%E3%83%BC%E3%83%AB%E3%83%89%3C%2Fp%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cp%3E%E3%83%AA%E3%83%B3%E3%82%AF%3C%2Fp%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cp%3E%E3%83%A9%E3%82%B8%E3%82%AA%E3%83%9C%E3%82%BF%E3%83%B3%3C%2Fp%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cp%3E%E3%83%AA%E3%82%B9%E3%83%88%3C%2Fp%3E%3C%2Ftd%3E%0A%3C%2Ftr%3E%0A%0A%3Ctr%3E%0A%3Ctd%3E%3Ctextarea+cols%3D%2210%22+rows%3D%222%22+name%3D%22textfree%22%3E%3C%2Ftextarea%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cp%3E%3C%2Fp%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cinput+type%3D%22text%22+value%3D%22%E5%85%A5%E5%8A%9B%E3%81%97%E3%81%A6%E3%81%8F%E3%81%A0%E3%81%95%E3%81%84%22+name%3D%22textinput%22+%2F%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cinput+type%3D%22password%22+name%3D%22pass%22+%2F%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Ca+href%3D%22index2.html%22%3E%E4%BC%9A%E5%93%A1%E7%99%BB%E9%8C%B2%E3%83%9A%E3%83%BC%E3%82%B8%E3%81%B8%3C%2Fa%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cinput+type%3D%22button%22+onclick%3D%22window_close%28%29%22+value%3D%22%E9%96%89%E3%81%98%E3%82%8B%22+%2F%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cul%3E%3Cli%3E%E3%83%86%E3%82%B9%E3%83%881%3C%2Fli%3E%3Cli%3E%E3%83%86%E3%82%B9%E3%83%882%3C%2Fli%3E%3C%2Ful%3E%3C%2Ftd%3E%0A%3C%2Ftr%3E%0A%0A%3Ctr%3E%3C%2Ftr%3E%0A%0A%3Ctr%3E%0A%3Ctd%3E%3Cp%3E%E3%83%97%E3%83%AB%E3%83%80%E3%82%A6%E3%83%B3%E3%83%A1%E3%83%8B%E3%83%A5%E3%83%BC%3C%2Fp%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cp%3E%E3%83%81%E3%82%A7%E3%83%83%E3%82%AF%E3%83%9C%E3%83%83%E3%82%AF%E3%82%B9%EF%BC%86%E3%83%A9%E3%83%99%E3%83%AB%3C%2Fp%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cp%3E%E5%AE%9F%E8%A1%8C%E3%83%9C%E3%82%BF%E3%83%B3%3C%2Fp%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cp%3E%E5%8F%96%E3%82%8A%E6%B6%88%E3%81%97%E3%83%9C%E3%82%BF%E3%83%B3%3C%2Fp%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cp%3ESat+Sep+19+03%3A06%3A35+JST+2015%3C%2Fp%3E%3C%2Ftd%3E%0A%3C%2Ftr%3E%0A%0A%3Ctr%3E%0A%3Ctd%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cinput+type%3D%22radio%22+checked%3D%22checked%22+value%3D%22yes%22+%2F%3E%0A++%3Clabel+for%3D%22r1%22+accesskey%3D%22Y%22%3E%E3%81%AF%E3%81%84%28%3Cu%3EY%3C%2Fu%3E%29%3C%2Flabel%3E%0A++%3Cinput+type%3D%22radio%22+value%3D%22no%22+id%3D%22r2%22+name%3D%22YES_NO%22+%2F%3E%0A++%3Clabel+for%3D%22r2%22+accesskey%3D%22N%22%3E%E3%81%84%E3%81%84%E3%81%88%28%3Cu%3EN%3C%2Fu%3E%29%3C%2Flabel%3E%0A+%3C%2Ftd%3E%0A%3Ctd%3E%3Cinput+type%3D%22submit%22+name%3D%22submit%22+%2F%3E%3C%2Ftd%3E%0A%3Ctd%3E%3Cinput+type%3D%22reset%22+name%3D%22reset%22+%2F%3E%3C%2Ftd%3E%0A%3C%2Ftr%3E%0A%0A%3C%2Ftbody%3E%3C%2Ftable%3E%0A%0A%0A%0A%3C%2Fbody%3E%3Ccanvas+id%3D%22fxdriver-screenshot-canvas%22+style%3D%22display%3A+none%3B%22+width%3D%221192%22+height%3D%22554%22%3E%3C%2Fcanvas%3E%3C%2Fhtml%3E";
		String strExpectWeb = URLDecoder.decode(strExpectWebEncoded, "UTF-8");
		String strWebDif = TTUtility.validateWeb(strResultWeb, strExpectWeb);
		if(!strWebDif.isEmpty()) bTestResult=false;


		//DB状態取得・比較
		String strResultDB = "";
		String strDBDif="";


		// 結果更新
		try {
			URI url = new URI("http://localhost:8080/souya/api/updateTestResult");
			JSONObject request = new JSONObject();
			request.put("id", 1);
			request.put("html", URLEncoder.encode(strResultWeb, "UTF-8"));
			request.put("db", URLEncoder.encode(strResultDB, "UTF-8"));
			request.put("html", URLEncoder.encode(strResultWeb, "UTF-8"));
			request.put("db_dif", URLEncoder.encode(strDBDif, "UTF-8"));
			request.put("html_dif", URLEncoder.encode(strWebDif, "UTF-8"));
			request.put("jobStatus", URLEncoder.encode(TTConst.JOB_STATUS_FINISH, "UTF-8"));
			request.put("testResult", bTestResult ? TTConst.TEST_RESULT_OK : TTConst.TEST_RESULT_NG);
			request.put("snapshot", strSnapshot);

			HttpEntity<String> entity = new HttpEntity<String>(
					request.toString(), headers);

			System.out.println("URL: " + url);
			String response = restTemplate.postForObject(url, entity,
					String.class);
			System.out.println("Response: " + response);

		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}



	}
	//テストケース終了


	@After
	public void doAfter(){
		//WebDriver終了
		webdriver.quit();
	}

	@AfterClass
	public static void aftCls(){
		//インスタンス破棄等
	}

}