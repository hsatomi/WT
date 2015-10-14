
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

public class Case10013 {

	public Case10013() {
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
		//公開トップ画面
		webdriver.get("https://prove-www.riviera-i.jp/");

	}

		//テストケース開始
	@Test
	public void Test9000022() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		try{
			//
			strIdentifyName = "項目名:,実行順:1,エレメント名:.gnav_fair > a:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".gnav_fair > a:nth-child(1)"));
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
			request.put("id", 9000022);
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
	public void Test9000023() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		try{
			//
			strIdentifyName = "項目名:,実行順:1,エレメント名:.gnav_fair > a:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//カレンダーから選ぶ
			strIdentifyName = "項目名:カレンダーから選ぶ,実行順:2,エレメント名:.pnav_cal > a:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".pnav_cal > a:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".pnav_cal > a:nth-child(1)"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//フェア検索フィルタ
			strIdentifyName = "項目名:フェア検索フィルタ,実行順:3,エレメント名:.con_search > form:nth-child(1) > select:nth-child(1),アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".con_search > form:nth-child(1) > select:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".con_search > form:nth-child(1) > select:nth-child(1)"));
			Select select=new Select(element);
			select.selectByVisibleText("模擬挙式");
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
			request.put("id", 9000023);
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
	public void Test9000024() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		try{
			//
			strIdentifyName = "項目名:,実行順:1,エレメント名:.gnav_fair > a:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//フェア予約入力画面
			strIdentifyName = "項目名:フェア予約入力画面,実行順:2,エレメント名:.txt_title,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".txt_title"));
			WebElement element = webdriver.findElement(By.cssSelector(".txt_title"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:3,エレメント名:customselector,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector"));
			WebElement element = webdriver.findElement(By.id("customselector"));
			Select select=new Select(element);
			select.selectByIndex(1);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:4,エレメント名:customselector2,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector2"));
			WebElement element = webdriver.findElement(By.id("customselector2"));
			Select select=new Select(element);
			select.selectByIndex(1);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:5,エレメント名:l_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("l_name"));
			WebElement element = webdriver.findElement(By.name("l_name"));
			element.clear();
			element.sendKeys("souya");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:6,エレメント名:f_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("f_name"));
			WebElement element = webdriver.findElement(By.name("f_name"));
			element.clear();
			element.sendKeys("test");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:7,エレメント名:l_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("l_name_kana"));
			WebElement element = webdriver.findElement(By.name("l_name_kana"));
			element.clear();
			element.sendKeys("ソウヤ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:8,エレメント名:f_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("f_name_kana"));
			WebElement element = webdriver.findElement(By.name("f_name_kana"));
			element.clear();
			element.sendKeys("テスト");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:9,エレメント名:sex,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.name("sex"));
			WebElement element = webdriver.findElement(By.name("sex"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:10,エレメント名:zip1,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.id("zip1"));
			WebElement element = webdriver.findElement(By.id("zip1"));
			element.clear();
			element.sendKeys("270");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:11,エレメント名:zip2,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("zip2"));
			WebElement element = webdriver.findElement(By.name("zip2"));
			element.clear();
			element.sendKeys("0741");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:12,エレメント名:pref_search,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.id("pref_search"));
			WebElement element = webdriver.findElement(By.id("pref_search"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:13,エレメント名:address1,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("address1"));
			WebElement element = webdriver.findElement(By.name("address1"));
			element.clear();
			element.sendKeys("あ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:14,エレメント名:address2,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("address2"));
			WebElement element = webdriver.findElement(By.name("address2"));
			element.clear();
			element.sendKeys("い");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:15,エレメント名:contact,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("contact"));
			WebElement element = webdriver.findElement(By.name("contact"));
			element.clear();
			element.sendKeys("03-1234-5678");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:16,エレメント名:email,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("email"));
			WebElement element = webdriver.findElement(By.name("email"));
			element.clear();
			element.sendKeys("test@test.jp");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:17,エレメント名:email_check,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("email_check"));
			WebElement element = webdriver.findElement(By.name("email_check"));
			element.clear();
			element.sendKeys("test@test.jp");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:18,エレメント名:partner_l_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_l_name"));
			WebElement element = webdriver.findElement(By.name("partner_l_name"));
			element.clear();
			element.sendKeys("souya");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:19,エレメント名:partner_f_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_f_name"));
			WebElement element = webdriver.findElement(By.name("partner_f_name"));
			element.clear();
			element.sendKeys("hanako");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:20,エレメント名:partner_l_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_l_name_kana"));
			WebElement element = webdriver.findElement(By.name("partner_l_name_kana"));
			element.clear();
			element.sendKeys("ソウヤ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:21,エレメント名:partner_f_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_f_name_kana"));
			WebElement element = webdriver.findElement(By.name("partner_f_name_kana"));
			element.clear();
			element.sendKeys("ハナコ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:22,エレメント名:rn_form_bk8,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.id("rn_form_bk8"));
			WebElement element = webdriver.findElement(By.id("rn_form_bk8"));
			Select select=new Select(element);
			select.selectByVisibleText("2016");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:23,エレメント名:wedding_date_month,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_date_month"));
			WebElement element = webdriver.findElement(By.name("wedding_date_month"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:24,エレメント名:wedding_date_day,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_date_day"));
			WebElement element = webdriver.findElement(By.name("wedding_date_day"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:25,エレメント名:rn_form_bk12,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.id("rn_form_bk12"));
			WebElement element = webdriver.findElement(By.id("rn_form_bk12"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:26,エレメント名:attendant_num_to,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("attendant_num_to"));
			WebElement element = webdriver.findElement(By.name("attendant_num_to"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:27,エレメント名:wedding_style,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_style"));
			WebElement element = webdriver.findElement(By.name("wedding_style"));
			Select select=new Select(element);
			select.selectByVisibleText("キリスト式");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:28,エレメント名:customer_message,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("customer_message"));
			WebElement element = webdriver.findElement(By.name("customer_message"));
			element.clear();
			element.sendKeys("あ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:29,エレメント名:#rn_agree,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector("#rn_agree"));
			WebElement element = webdriver.findElement(By.cssSelector("#rn_agree"));
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
			request.put("id", 9000024);
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
	public void Test9000026() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		try{
			//
			strIdentifyName = "項目名:,実行順:1,エレメント名:.gnav_fair > a:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//フェア予約入力画面
			strIdentifyName = "項目名:フェア予約入力画面,実行順:2,エレメント名:.txt_title,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".txt_title"));
			WebElement element = webdriver.findElement(By.cssSelector(".txt_title"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:3,エレメント名:customselector,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector"));
			WebElement element = webdriver.findElement(By.id("customselector"));
			Select select=new Select(element);
			select.selectByIndex(1);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:4,エレメント名:customselector2,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector2"));
			WebElement element = webdriver.findElement(By.id("customselector2"));
			Select select=new Select(element);
			select.selectByIndex(1);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:5,エレメント名:l_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("l_name"));
			WebElement element = webdriver.findElement(By.name("l_name"));
			element.clear();
			element.sendKeys("souya");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:6,エレメント名:f_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("f_name"));
			WebElement element = webdriver.findElement(By.name("f_name"));
			element.clear();
			element.sendKeys("test");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:7,エレメント名:l_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("l_name_kana"));
			WebElement element = webdriver.findElement(By.name("l_name_kana"));
			element.clear();
			element.sendKeys("ソウヤ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:8,エレメント名:f_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("f_name_kana"));
			WebElement element = webdriver.findElement(By.name("f_name_kana"));
			element.clear();
			element.sendKeys("テスト");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:9,エレメント名:sex,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.name("sex"));
			WebElement element = webdriver.findElement(By.name("sex"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:10,エレメント名:zip1,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.id("zip1"));
			WebElement element = webdriver.findElement(By.id("zip1"));
			element.clear();
			element.sendKeys("２７０");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:11,エレメント名:zip2,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("zip2"));
			WebElement element = webdriver.findElement(By.name("zip2"));
			element.clear();
			element.sendKeys("０７４１");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:12,エレメント名:pref_search,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.id("pref_search"));
			WebElement element = webdriver.findElement(By.id("pref_search"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:13,エレメント名:address1,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("address1"));
			WebElement element = webdriver.findElement(By.name("address1"));
			element.clear();
			element.sendKeys("あ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:14,エレメント名:address2,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("address2"));
			WebElement element = webdriver.findElement(By.name("address2"));
			element.clear();
			element.sendKeys("い");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:15,エレメント名:contact,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("contact"));
			WebElement element = webdriver.findElement(By.name("contact"));
			element.clear();
			element.sendKeys("03-1234-5678");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:16,エレメント名:email,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("email"));
			WebElement element = webdriver.findElement(By.name("email"));
			element.clear();
			element.sendKeys("test@test.jp");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:17,エレメント名:email_check,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("email_check"));
			WebElement element = webdriver.findElement(By.name("email_check"));
			element.clear();
			element.sendKeys("test@test.jp");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:18,エレメント名:partner_l_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_l_name"));
			WebElement element = webdriver.findElement(By.name("partner_l_name"));
			element.clear();
			element.sendKeys("souya");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:19,エレメント名:partner_f_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_f_name"));
			WebElement element = webdriver.findElement(By.name("partner_f_name"));
			element.clear();
			element.sendKeys("hanako");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:20,エレメント名:partner_l_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_l_name_kana"));
			WebElement element = webdriver.findElement(By.name("partner_l_name_kana"));
			element.clear();
			element.sendKeys("ソウヤ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:21,エレメント名:partner_f_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_f_name_kana"));
			WebElement element = webdriver.findElement(By.name("partner_f_name_kana"));
			element.clear();
			element.sendKeys("ハナコ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:22,エレメント名:rn_form_bk8,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.id("rn_form_bk8"));
			WebElement element = webdriver.findElement(By.id("rn_form_bk8"));
			Select select=new Select(element);
			select.selectByVisibleText("2016");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:23,エレメント名:wedding_date_month,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_date_month"));
			WebElement element = webdriver.findElement(By.name("wedding_date_month"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:24,エレメント名:wedding_date_day,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_date_day"));
			WebElement element = webdriver.findElement(By.name("wedding_date_day"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:25,エレメント名:rn_form_bk12,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.id("rn_form_bk12"));
			WebElement element = webdriver.findElement(By.id("rn_form_bk12"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:26,エレメント名:attendant_num_to,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("attendant_num_to"));
			WebElement element = webdriver.findElement(By.name("attendant_num_to"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:27,エレメント名:wedding_style,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_style"));
			WebElement element = webdriver.findElement(By.name("wedding_style"));
			Select select=new Select(element);
			select.selectByVisibleText("キリスト式");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:28,エレメント名:customer_message,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("customer_message"));
			WebElement element = webdriver.findElement(By.name("customer_message"));
			element.clear();
			element.sendKeys("あ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:29,エレメント名:#rn_agree,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector("#rn_agree"));
			WebElement element = webdriver.findElement(By.cssSelector("#rn_agree"));
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
			request.put("id", 9000026);
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
	public void Test9000027() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		try{
			//
			strIdentifyName = "項目名:,実行順:1,エレメント名:.gnav_fair > a:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//フェア予約入力画面
			strIdentifyName = "項目名:フェア予約入力画面,実行順:2,エレメント名:.txt_title,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".txt_title"));
			WebElement element = webdriver.findElement(By.cssSelector(".txt_title"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:31,エレメント名:customselector,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector"));
			WebElement element = webdriver.findElement(By.id("customselector"));
			Select select=new Select(element);
			select.selectByIndex(1);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:32,エレメント名:customselector2,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector2"));
			WebElement element = webdriver.findElement(By.id("customselector2"));
			Select select=new Select(element);
			select.selectByIndex(1);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//確認ボタン
			strIdentifyName = "項目名:確認ボタン,実行順:33,エレメント名:.rn_if_submitBox > a:nth-child(2) > img:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
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
			request.put("id", 9000027);
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
	public void Test9000028() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		try{
			//
			strIdentifyName = "項目名:,実行順:1,エレメント名:.gnav_fair > a:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//フェア予約入力画面
			strIdentifyName = "項目名:フェア予約入力画面,実行順:2,エレメント名:.txt_title,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".txt_title"));
			WebElement element = webdriver.findElement(By.cssSelector(".txt_title"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:3,エレメント名:customselector,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector"));
			WebElement element = webdriver.findElement(By.id("customselector"));
			Select select=new Select(element);
			select.selectByIndex(1);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:4,エレメント名:customselector2,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector2"));
			WebElement element = webdriver.findElement(By.id("customselector2"));
			Select select=new Select(element);
			select.selectByIndex(1);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:5,エレメント名:#rn_agree,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector("#rn_agree"));
			WebElement element = webdriver.findElement(By.cssSelector("#rn_agree"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//確認ボタン
			strIdentifyName = "項目名:確認ボタン,実行順:6,エレメント名:.rn_if_submitBox > a:nth-child(2) > img:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
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
			request.put("id", 9000028);
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
	public void Test9000029() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		try{
			//
			strIdentifyName = "項目名:,実行順:1,エレメント名:.gnav_fair > a:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//フェア予約入力画面
			strIdentifyName = "項目名:フェア予約入力画面,実行順:2,エレメント名:.txt_title,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".txt_title"));
			WebElement element = webdriver.findElement(By.cssSelector(".txt_title"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//確認ボタン
			strIdentifyName = "項目名:確認ボタン,実行順:30,エレメント名:.rn_if_submitBox > a:nth-child(2) > img:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
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
			request.put("id", 9000029);
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
	public void Test9000030() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		try{
			//
			strIdentifyName = "項目名:,実行順:1,エレメント名:.gnav_fair > a:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//フェア予約入力画面
			strIdentifyName = "項目名:フェア予約入力画面,実行順:2,エレメント名:.txt_title,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".txt_title"));
			WebElement element = webdriver.findElement(By.cssSelector(".txt_title"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:3,エレメント名:customselector,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector"));
			WebElement element = webdriver.findElement(By.id("customselector"));
			Select select=new Select(element);
			select.selectByIndex(1);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:4,エレメント名:customselector2,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector2"));
			WebElement element = webdriver.findElement(By.id("customselector2"));
			Select select=new Select(element);
			select.selectByIndex(1);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:5,エレメント名:l_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("l_name"));
			WebElement element = webdriver.findElement(By.name("l_name"));
			element.clear();
			element.sendKeys("souya");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:6,エレメント名:f_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("f_name"));
			WebElement element = webdriver.findElement(By.name("f_name"));
			element.clear();
			element.sendKeys("test");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:7,エレメント名:l_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("l_name_kana"));
			WebElement element = webdriver.findElement(By.name("l_name_kana"));
			element.clear();
			element.sendKeys("ソウヤ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:8,エレメント名:f_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("f_name_kana"));
			WebElement element = webdriver.findElement(By.name("f_name_kana"));
			element.clear();
			element.sendKeys("テスト");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:9,エレメント名:sex,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.name("sex"));
			WebElement element = webdriver.findElement(By.name("sex"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:10,エレメント名:zip1,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.id("zip1"));
			WebElement element = webdriver.findElement(By.id("zip1"));
			element.clear();
			element.sendKeys("270");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:11,エレメント名:zip2,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("zip2"));
			WebElement element = webdriver.findElement(By.name("zip2"));
			element.clear();
			element.sendKeys("0741");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:12,エレメント名:pref_search,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.id("pref_search"));
			WebElement element = webdriver.findElement(By.id("pref_search"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:13,エレメント名:address1,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("address1"));
			WebElement element = webdriver.findElement(By.name("address1"));
			element.clear();
			element.sendKeys("あ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:14,エレメント名:address2,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("address2"));
			WebElement element = webdriver.findElement(By.name("address2"));
			element.clear();
			element.sendKeys("い");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:15,エレメント名:contact,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("contact"));
			WebElement element = webdriver.findElement(By.name("contact"));
			element.clear();
			element.sendKeys("03-1234-5678");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:16,エレメント名:email,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("email"));
			WebElement element = webdriver.findElement(By.name("email"));
			element.clear();
			element.sendKeys("test@test.jp");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:17,エレメント名:email_check,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("email_check"));
			WebElement element = webdriver.findElement(By.name("email_check"));
			element.clear();
			element.sendKeys("test@test.jp");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:18,エレメント名:partner_l_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_l_name"));
			WebElement element = webdriver.findElement(By.name("partner_l_name"));
			element.clear();
			element.sendKeys("souya");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:19,エレメント名:partner_f_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_f_name"));
			WebElement element = webdriver.findElement(By.name("partner_f_name"));
			element.clear();
			element.sendKeys("hanako");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:20,エレメント名:partner_l_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_l_name_kana"));
			WebElement element = webdriver.findElement(By.name("partner_l_name_kana"));
			element.clear();
			element.sendKeys("ソウヤ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:21,エレメント名:partner_f_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_f_name_kana"));
			WebElement element = webdriver.findElement(By.name("partner_f_name_kana"));
			element.clear();
			element.sendKeys("ハナコ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:22,エレメント名:rn_form_bk8,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.id("rn_form_bk8"));
			WebElement element = webdriver.findElement(By.id("rn_form_bk8"));
			Select select=new Select(element);
			select.selectByVisibleText("2016");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:23,エレメント名:wedding_date_month,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_date_month"));
			WebElement element = webdriver.findElement(By.name("wedding_date_month"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:24,エレメント名:wedding_date_day,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_date_day"));
			WebElement element = webdriver.findElement(By.name("wedding_date_day"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:25,エレメント名:rn_form_bk12,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.id("rn_form_bk12"));
			WebElement element = webdriver.findElement(By.id("rn_form_bk12"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:26,エレメント名:attendant_num_to,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("attendant_num_to"));
			WebElement element = webdriver.findElement(By.name("attendant_num_to"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:27,エレメント名:wedding_style,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_style"));
			WebElement element = webdriver.findElement(By.name("wedding_style"));
			Select select=new Select(element);
			select.selectByVisibleText("キリスト式");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:28,エレメント名:customer_message,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("customer_message"));
			WebElement element = webdriver.findElement(By.name("customer_message"));
			element.clear();
			element.sendKeys("あ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:29,エレメント名:#rn_agree,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector("#rn_agree"));
			WebElement element = webdriver.findElement(By.cssSelector("#rn_agree"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//確認ボタン
			strIdentifyName = "項目名:確認ボタン,実行順:30,エレメント名:.rn_if_submitBox > a:nth-child(2) > img:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
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
			request.put("id", 9000030);
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
	public void Test9000031() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		try{
			//
			strIdentifyName = "項目名:,実行順:1,エレメント名:.gnav_fair > a:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//フェア予約入力画面
			strIdentifyName = "項目名:フェア予約入力画面,実行順:2,エレメント名:.txt_title,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".txt_title"));
			WebElement element = webdriver.findElement(By.cssSelector(".txt_title"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:3,エレメント名:customselector,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector"));
			WebElement element = webdriver.findElement(By.id("customselector"));
			Select select=new Select(element);
			select.selectByIndex(1);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:4,エレメント名:customselector2,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector2"));
			WebElement element = webdriver.findElement(By.id("customselector2"));
			Select select=new Select(element);
			select.selectByIndex(1);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:5,エレメント名:l_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("l_name"));
			WebElement element = webdriver.findElement(By.name("l_name"));
			element.clear();
			element.sendKeys("souya");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:6,エレメント名:f_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("f_name"));
			WebElement element = webdriver.findElement(By.name("f_name"));
			element.clear();
			element.sendKeys("test");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:7,エレメント名:l_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("l_name_kana"));
			WebElement element = webdriver.findElement(By.name("l_name_kana"));
			element.clear();
			element.sendKeys("ソウヤ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:8,エレメント名:f_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("f_name_kana"));
			WebElement element = webdriver.findElement(By.name("f_name_kana"));
			element.clear();
			element.sendKeys("テスト");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:9,エレメント名:sex,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.name("sex"));
			WebElement element = webdriver.findElement(By.name("sex"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:10,エレメント名:zip1,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.id("zip1"));
			WebElement element = webdriver.findElement(By.id("zip1"));
			element.clear();
			element.sendKeys("270");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:11,エレメント名:zip2,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("zip2"));
			WebElement element = webdriver.findElement(By.name("zip2"));
			element.clear();
			element.sendKeys("0741");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:12,エレメント名:pref_search,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.id("pref_search"));
			WebElement element = webdriver.findElement(By.id("pref_search"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:13,エレメント名:address1,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("address1"));
			WebElement element = webdriver.findElement(By.name("address1"));
			element.clear();
			element.sendKeys("あ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:14,エレメント名:address2,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("address2"));
			WebElement element = webdriver.findElement(By.name("address2"));
			element.clear();
			element.sendKeys("い");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:15,エレメント名:contact,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("contact"));
			WebElement element = webdriver.findElement(By.name("contact"));
			element.clear();
			element.sendKeys("03-1234-5678");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:16,エレメント名:email,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("email"));
			WebElement element = webdriver.findElement(By.name("email"));
			element.clear();
			element.sendKeys("test@test.jp");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:17,エレメント名:email_check,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("email_check"));
			WebElement element = webdriver.findElement(By.name("email_check"));
			element.clear();
			element.sendKeys("test@test.jp");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:18,エレメント名:partner_l_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_l_name"));
			WebElement element = webdriver.findElement(By.name("partner_l_name"));
			element.clear();
			element.sendKeys("souya");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:19,エレメント名:partner_f_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_f_name"));
			WebElement element = webdriver.findElement(By.name("partner_f_name"));
			element.clear();
			element.sendKeys("hanako");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:20,エレメント名:partner_l_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_l_name_kana"));
			WebElement element = webdriver.findElement(By.name("partner_l_name_kana"));
			element.clear();
			element.sendKeys("ソウヤ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:21,エレメント名:partner_f_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_f_name_kana"));
			WebElement element = webdriver.findElement(By.name("partner_f_name_kana"));
			element.clear();
			element.sendKeys("ハナコ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:22,エレメント名:rn_form_bk8,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.id("rn_form_bk8"));
			WebElement element = webdriver.findElement(By.id("rn_form_bk8"));
			Select select=new Select(element);
			select.selectByVisibleText("2016");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:23,エレメント名:wedding_date_month,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_date_month"));
			WebElement element = webdriver.findElement(By.name("wedding_date_month"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:24,エレメント名:wedding_date_day,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_date_day"));
			WebElement element = webdriver.findElement(By.name("wedding_date_day"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:25,エレメント名:rn_form_bk12,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.id("rn_form_bk12"));
			WebElement element = webdriver.findElement(By.id("rn_form_bk12"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:26,エレメント名:attendant_num_to,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("attendant_num_to"));
			WebElement element = webdriver.findElement(By.name("attendant_num_to"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:27,エレメント名:wedding_style,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_style"));
			WebElement element = webdriver.findElement(By.name("wedding_style"));
			Select select=new Select(element);
			select.selectByVisibleText("キリスト式");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:28,エレメント名:customer_message,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("customer_message"));
			WebElement element = webdriver.findElement(By.name("customer_message"));
			element.clear();
			element.sendKeys("あ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:29,エレメント名:#rn_agree,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector("#rn_agree"));
			WebElement element = webdriver.findElement(By.cssSelector("#rn_agree"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//確認ボタン
			strIdentifyName = "項目名:確認ボタン,実行順:30,エレメント名:.rn_if_submitBox > a:nth-child(2) > img:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//戻るボタン
			strIdentifyName = "項目名:戻るボタン,実行順:31,エレメント名:.rn_if_submitBox > a:nth-child(1) > img:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".rn_if_submitBox > a:nth-child(1) > img:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".rn_if_submitBox > a:nth-child(1) > img:nth-child(1)"));
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
			request.put("id", 9000031);
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
	public void Test9000032() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		try{
			//
			strIdentifyName = "項目名:,実行順:1,エレメント名:.gnav_fair > a:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".gnav_fair > a:nth-child(1)"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//フェア予約入力画面
			strIdentifyName = "項目名:フェア予約入力画面,実行順:2,エレメント名:.txt_title,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".txt_title"));
			WebElement element = webdriver.findElement(By.cssSelector(".txt_title"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:3,エレメント名:customselector,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector"));
			WebElement element = webdriver.findElement(By.id("customselector"));
			Select select=new Select(element);
			select.selectByIndex(3);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:4,エレメント名:customselector2,アクション:selectByIndex";
			List<WebElement> elements = webdriver.findElements(By.id("customselector2"));
			WebElement element = webdriver.findElement(By.id("customselector2"));
			Select select=new Select(element);
			select.selectByIndex(1);
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:5,エレメント名:l_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("l_name"));
			WebElement element = webdriver.findElement(By.name("l_name"));
			element.clear();
			element.sendKeys("souya");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:6,エレメント名:f_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("f_name"));
			WebElement element = webdriver.findElement(By.name("f_name"));
			element.clear();
			element.sendKeys("test");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:7,エレメント名:l_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("l_name_kana"));
			WebElement element = webdriver.findElement(By.name("l_name_kana"));
			element.clear();
			element.sendKeys("ソウヤ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:8,エレメント名:f_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("f_name_kana"));
			WebElement element = webdriver.findElement(By.name("f_name_kana"));
			element.clear();
			element.sendKeys("テスト");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:9,エレメント名:sex,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.name("sex"));
			WebElement element = webdriver.findElement(By.name("sex"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:10,エレメント名:zip1,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.id("zip1"));
			WebElement element = webdriver.findElement(By.id("zip1"));
			element.clear();
			element.sendKeys("270");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:11,エレメント名:zip2,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("zip2"));
			WebElement element = webdriver.findElement(By.name("zip2"));
			element.clear();
			element.sendKeys("0741");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:12,エレメント名:pref_search,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.id("pref_search"));
			WebElement element = webdriver.findElement(By.id("pref_search"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:13,エレメント名:address1,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("address1"));
			WebElement element = webdriver.findElement(By.name("address1"));
			element.clear();
			element.sendKeys("あ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:14,エレメント名:address2,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("address2"));
			WebElement element = webdriver.findElement(By.name("address2"));
			element.clear();
			element.sendKeys("い");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:15,エレメント名:contact,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("contact"));
			WebElement element = webdriver.findElement(By.name("contact"));
			element.clear();
			element.sendKeys("03-1234-5678");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:16,エレメント名:email,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("email"));
			WebElement element = webdriver.findElement(By.name("email"));
			element.clear();
			element.sendKeys("test@test.jp");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:17,エレメント名:email_check,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("email_check"));
			WebElement element = webdriver.findElement(By.name("email_check"));
			element.clear();
			element.sendKeys("test@test.jp");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:18,エレメント名:partner_l_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_l_name"));
			WebElement element = webdriver.findElement(By.name("partner_l_name"));
			element.clear();
			element.sendKeys("souya");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:19,エレメント名:partner_f_name,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_f_name"));
			WebElement element = webdriver.findElement(By.name("partner_f_name"));
			element.clear();
			element.sendKeys("hanako");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:20,エレメント名:partner_l_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_l_name_kana"));
			WebElement element = webdriver.findElement(By.name("partner_l_name_kana"));
			element.clear();
			element.sendKeys("ソウヤ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:21,エレメント名:partner_f_name_kana,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("partner_f_name_kana"));
			WebElement element = webdriver.findElement(By.name("partner_f_name_kana"));
			element.clear();
			element.sendKeys("ハナコ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:22,エレメント名:rn_form_bk8,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.id("rn_form_bk8"));
			WebElement element = webdriver.findElement(By.id("rn_form_bk8"));
			Select select=new Select(element);
			select.selectByVisibleText("2016");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:23,エレメント名:wedding_date_month,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_date_month"));
			WebElement element = webdriver.findElement(By.name("wedding_date_month"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:24,エレメント名:wedding_date_day,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_date_day"));
			WebElement element = webdriver.findElement(By.name("wedding_date_day"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:25,エレメント名:rn_form_bk12,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.id("rn_form_bk12"));
			WebElement element = webdriver.findElement(By.id("rn_form_bk12"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:26,エレメント名:attendant_num_to,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("attendant_num_to"));
			WebElement element = webdriver.findElement(By.name("attendant_num_to"));
			Select select=new Select(element);
			select.selectByVisibleText("未定");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:27,エレメント名:wedding_style,アクション:selectByVisibleText";
			List<WebElement> elements = webdriver.findElements(By.name("wedding_style"));
			WebElement element = webdriver.findElement(By.name("wedding_style"));
			Select select=new Select(element);
			select.selectByVisibleText("キリスト式");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:28,エレメント名:customer_message,アクション:sendKeys";
			List<WebElement> elements = webdriver.findElements(By.name("customer_message"));
			WebElement element = webdriver.findElement(By.name("customer_message"));
			element.clear();
			element.sendKeys("あ");
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//
			strIdentifyName = "項目名:,実行順:29,エレメント名:#rn_agree,アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector("#rn_agree"));
			WebElement element = webdriver.findElement(By.cssSelector("#rn_agree"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//確認ボタン
			strIdentifyName = "項目名:確認ボタン,実行順:30,エレメント名:.rn_if_submitBox > a:nth-child(2) > img:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
			element.click();
			Thread.sleep(300);
		}catch(Exception e){errList.add(strIdentifyName+e.getMessage());}
		try{
			//送信ボタン
			strIdentifyName = "項目名:送信ボタン,実行順:31,エレメント名:.rn_if_submitBox > a:nth-child(2) > img:nth-child(1),アクション:click";
			List<WebElement> elements = webdriver.findElements(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
			WebElement element = webdriver.findElement(By.cssSelector(".rn_if_submitBox > a:nth-child(2) > img:nth-child(1)"));
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
			request.put("id", 9000032);
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