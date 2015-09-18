
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



		//DB状態取得・比較
		String strResultDB = "";



		// 正解値更新
		try {
			URI url = new URI("http://192.168.0.142:8080/wt/api/updateResult");
			JSONObject request = new JSONObject();
			request.put("id", 1);
			request.put("html", URLEncoder.encode(strResultWeb, "UTF-8"));
			request.put("db", URLEncoder.encode(strResultDB, "UTF-8"));

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




		// 結果更新
		try {
			URI url = new URI("http://192.168.0.142:8080/wt/api/updateTestResult");
			JSONObject request = new JSONObject();
			request.put("id", 1);
			request.put("execTimes", 1);
			request.put("html", URLEncoder.encode(strResultWeb, "UTF-8"));
			request.put("db", URLEncoder.encode(strResultDB, "UTF-8"));
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
	@Test
	public void Test2() throws UnsupportedEncodingException{

		//実行
		boolean bTestResult = true;
		{
			//ユーザ名
			WebElement element = webdriver.findElement(By.name("textfree"));
			element.sendKeys("TESTパターン2のテストです");
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



		//DB状態取得・比較
		String strResultDB = "";



		// 正解値更新
		try {
			URI url = new URI("http://192.168.0.142:8080/wt/api/updateResult");
			JSONObject request = new JSONObject();
			request.put("id", 2);
			request.put("html", URLEncoder.encode(strResultWeb, "UTF-8"));
			request.put("db", URLEncoder.encode(strResultDB, "UTF-8"));

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




		// 結果更新
		try {
			URI url = new URI("http://192.168.0.142:8080/wt/api/updateTestResult");
			JSONObject request = new JSONObject();
			request.put("id", 2);
			request.put("execTimes", 1);
			request.put("html", URLEncoder.encode(strResultWeb, "UTF-8"));
			request.put("db", URLEncoder.encode(strResultDB, "UTF-8"));
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