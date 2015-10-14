
package co.jp.souya.project1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import co.jp.souya.tool.TTConst;
import co.jp.souya.tool.TTUtility;

public class Case10014 {

	public Case10014() {
	}

	private static WebDriver webdriver;
	private static HttpHeaders headers;
	private static RestTemplate restTemplate;
	private static Set<String> hndlsNow;
	private static List<String> errList;
	private static String strIdentifyName;

	@BeforeClass
	public static  void doBeforeCls(){
		//初期化
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate = new RestTemplate();
		errList = new ArrayList<String>();

		//永続化マネージャの生成等

	}
	@Before
	public void doBefore() throws Exception{
		//DB初期化
		//import用スクリプトをキックする、など要検討


    	webdriver = new FirefoxDriver();
		//画面遷移
		//Rosyログイン
		webdriver.get("http://prove-admin.rivieramypage.jp/admin/login");
		try{
			//ユーザ名
			strIdentifyName = "項目名:ユーザ名,実行順:1,エレメント名:login_id,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("login_id"));
			WebElement element = webdriver.findElement(By.name("login_id"));
			element.clear();
			element.sendKeys("souya5");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//パスワード
			strIdentifyName = "項目名:パスワード,実行順:2,エレメント名:password,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("password"));
			WebElement element = webdriver.findElement(By.name("password"));
			element.clear();
			element.sendKeys("souya5");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//ボタン
			strIdentifyName = "項目名:ボタン,実行順:3,エレメント名:middle_btn,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.className("middle_btn"));
			WebElement element = webdriver.findElement(By.className("middle_btn"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		//見込み客一覧
		webdriver.get("https://prove-admin.rivieramypage.jp/admin/visitor");

	}

		//テストケース開始
	@Test
	public void Test9000035() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		try{
			//日付
			strIdentifyName = "項目名:日付,実行順:1,エレメント名:book_datetime_date,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.id("book_datetime_date"));
			WebElement element = webdriver.findElement(By.id("book_datetime_date"));
			element.clear();
			element.sendKeys("2015/10/12");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//ロケーション
			strIdentifyName = "項目名:ロケーション,実行順:2,エレメント名:#location_id,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.cssSelector("#location_id"));
			WebElement element = webdriver.findElement(By.cssSelector("#location_id"));
			Select select=new Select(element);
			select.selectByIndex(0);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//検索ボタン
			strIdentifyName = "項目名:検索ボタン,実行順:3,エレメント名:input.middle_btn,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector("input.middle_btn"));
			WebElement element = webdriver.findElement(By.cssSelector("input.middle_btn"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}

		// 実行後アラートダイアログチェック
		Alert alert = null;
		try{
			alert = webdriver.switchTo().alert();
		}catch(Exception e){
			System.out.println("no alert");
		}


		// 実行後スナップショット取得
		String strSnapshot = "";
		if (alert == null) {
			strSnapshot = tryGetPicture();
		}


		//web状態取得・比較
		String strResultWeb = "";
		if(alert==null){
			strResultWeb = webdriver.getPageSource();
		}else{
			strResultWeb = alert.getText();
		}


		//DB状態取得・比較
		String strResultDB = "";

		// 結果更新
		try {
			URI url = new URI("http://localhost:8080/souya/api/updateTestResult");
			JSONObject request = new JSONObject();
			request.put("id", 9000035);
			request.put("html", URLEncoder.encode(strResultWeb, "UTF-8"));
			request.put("db", URLEncoder.encode(strResultDB, "UTF-8"));
			request.put("snapshot", strSnapshot);
			request.put("errList", URLEncoder.encode(errList.toString(), "UTF-8"));

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
	public void Test9000036() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		try{
			//日付
			strIdentifyName = "項目名:日付,実行順:1,エレメント名:book_datetime_date,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.id("book_datetime_date"));
			WebElement element = webdriver.findElement(By.id("book_datetime_date"));
			element.clear();
			element.sendKeys("2015/10/12");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//ロケーション
			strIdentifyName = "項目名:ロケーション,実行順:2,エレメント名:#location_id,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.cssSelector("#location_id"));
			WebElement element = webdriver.findElement(By.cssSelector("#location_id"));
			Select select=new Select(element);
			select.selectByIndex(0);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//検索ボタン
			strIdentifyName = "項目名:検索ボタン,実行順:3,エレメント名:input.middle_btn,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector("input.middle_btn"));
			WebElement element = webdriver.findElement(By.cssSelector("input.middle_btn"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:4,エレメント名://input[@value='削除'],アクション:click";
			List<WebElement> elements = webdriver.findElements(By.xpath("//input[@value='削除']"));
			WebElement element = webdriver.findElement(By.xpath("//input[@value='削除']"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}

		// 実行後アラートダイアログチェック
		Alert alert = null;
		try{
			alert = webdriver.switchTo().alert();
		}catch(Exception e){
			System.out.println("no alert");
		}


		// 実行後スナップショット取得
		String strSnapshot = "";
		if (alert == null) {
			strSnapshot = tryGetPicture();
		}


		//web状態取得・比較
		String strResultWeb = "";
		if(alert==null){
			strResultWeb = webdriver.getPageSource();
		}else{
			strResultWeb = alert.getText();
		}


		//DB状態取得・比較
		String strResultDB = "";

		// 結果更新
		try {
			URI url = new URI("http://localhost:8080/souya/api/updateTestResult");
			JSONObject request = new JSONObject();
			request.put("id", 9000036);
			request.put("html", URLEncoder.encode(strResultWeb, "UTF-8"));
			request.put("db", URLEncoder.encode(strResultDB, "UTF-8"));
			request.put("snapshot", strSnapshot);
			request.put("errList", URLEncoder.encode(errList.toString(), "UTF-8"));

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
		//エラーリストをクリア
		errList.clear();
		//WebDriver終了
		webdriver.quit();
	}

	@AfterClass
	public static void aftCls(){
		//インスタンス破棄等
	}

	//他画面へ遷移する
	private void move_anotherWindow() throws Exception{
		Thread.sleep(500);
		String hndlMain = webdriver.getWindowHandle();
		Set<String> windowList = webdriver.getWindowHandles();
		for (String hndlWnd : windowList) {

			if(!hndlMain.equals(hndlWnd)){
				webdriver.switchTo().window(hndlWnd);
				break;
			}
		}
		Thread.sleep(500);
	}

	//アラートをOKクリックする
	private void click_alertOK() throws Exception{
		Thread.sleep(500);
		Alert alert = null;
		try{
			alert = webdriver.switchTo().alert();
			alert.accept();
		}catch(Exception e){
			System.out.println("no alert");
		}
		Thread.sleep(500);
	}

	//アラートをキャンセルクリックする
	private void click_alertNG() throws Exception{
		Thread.sleep(500);
		Alert alert = null;
		try{
			alert = webdriver.switchTo().alert();
			alert.dismiss();
		}catch(Exception e){
			System.out.println("no alert");
		}
		Thread.sleep(500);
	}

	//アラート確認
	private boolean isAlertPresent() {
		try {
			webdriver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//画像を取得
	private String tryGetPicture() {
		String strSnapshot = "";
		try {
			File file = ((TakesScreenshot) webdriver)
					.getScreenshotAs(OutputType.FILE);
			strSnapshot = TTUtility.getBase64String(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strSnapshot;
	}

	// 活性Windowを検索してフォーカスを移動する
	private boolean move_activeWindow() throws Exception {
		Thread.sleep(500);
		try{
			webdriver.getWindowHandle();
			return true;
		}catch(Exception e){
		}
		Thread.sleep(500);

		boolean bStatus = false;
		for (String hndlWin : hndlsNow) {
			try {
				webdriver.switchTo().window(hndlWin);
				bStatus = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if (bStatus)
				break;
		}
		return bStatus;
	}
}