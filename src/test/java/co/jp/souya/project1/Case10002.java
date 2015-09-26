
package co.jp.souya.project1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

public class Case10002 {

	public Case10002() {
	}

	private static WebDriver webdriver;
	private static HttpHeaders headers;
	private static RestTemplate restTemplate;
	private static Set<String> hndlsNow;

	@BeforeClass
	public static  void doBeforeCls(){
		//初期化
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate = new RestTemplate();

		//永続化マネージャの生成等

	}
	@Before
	public void doBefore() throws Exception{
		//DB初期化
		//import用スクリプトをキックする、など要検討


    	webdriver = new FirefoxDriver();
		//画面遷移
		//ログイン
		webdriver.get("http://prove-admin.rivieramypage.jp/admin/login");
		{
			//ユーザ名
			List<WebElement> elements = webdriver.findElements(By.name("login_id"));
			WebElement element = webdriver.findElement(By.name("login_id"));
			element.clear();
			element.sendKeys("souya5");
		}
		{
			//パスワード
			List<WebElement> elements = webdriver.findElements(By.name("password"));
			WebElement element = webdriver.findElement(By.name("password"));
			element.clear();
			element.sendKeys("souya5");
		}
		{
			//ボタン
			List<WebElement> elements = webdriver.findElements(By.className("middle_btn"));
			WebElement element = webdriver.findElement(By.className("middle_btn"));
			element.click();
		}
		//書類テンプレート
		{
			//書類テンプレート
			List<WebElement> elements = webdriver.findElements(By.linkText("マスタ管理"));
			WebElement element = webdriver.findElement(By.linkText("マスタ管理"));
			element.click();
		}
		{
			//書類テンプレート
			List<WebElement> elements = webdriver.findElements(By.linkText("書類テンプレート"));
			WebElement element = webdriver.findElement(By.linkText("書類テンプレート"));
			element.click();
		}
		//一覧
		{
			//新規登録
			List<WebElement> elements = webdriver.findElements(By.cssSelector("form[name=\"input_form\"] > input.middle_btn"));
			WebElement element = webdriver.findElement(By.cssSelector("form[name=\"input_form\"] > input.middle_btn"));
			element.click();
		}
		{
			move_anotherWindow();
		}

	}

		//テストケース開始
	@Test
	public void Test7() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		{
			//表示順
			List<WebElement> elements = webdriver.findElements(By.id("display_num"));
			WebElement element = webdriver.findElement(By.id("display_num"));
			element.clear();
			element.sendKeys("166");
		}
		{
			//業種
			List<WebElement> elements = webdriver.findElements(By.id("company_industry_id"));
			WebElement element = webdriver.findElement(By.id("company_industry_id"));
			Select select=new Select(element);
			select.selectByIndex(1);
		}
		{
			//テンプレート名
			List<WebElement> elements = webdriver.findElements(By.id("template_name"));
			WebElement element = webdriver.findElement(By.id("template_name"));
			element.clear();
			element.sendKeys("testByToolForUpdate");
		}
		{
			//データ形式
			List<WebElement> elements = webdriver.findElements(By.id("data_type"));
			WebElement element = webdriver.findElement(By.id("data_type"));
			Select select=new Select(element);
			select.selectByIndex(1);
		}
		{
			//個別清算者
			List<WebElement> elements = webdriver.findElements(By.className("main_unit"));
			WebElement element = webdriver.findElement(By.className("main_unit"));
			elements.get(1).click();
		}
		{
			//対象テンプレートファイル
			List<WebElement> elements = webdriver.findElements(By.id("file_path"));
			WebElement element = webdriver.findElement(By.id("file_path"));
			element.clear();
			element.sendKeys("C:\\Temp\\dummy.pxd");
		}
		{
			//登録ボタン
			List<WebElement> elements = webdriver.findElements(By.className("middle_btn"));
			WebElement element = webdriver.findElement(By.className("middle_btn"));
			element.click();
		}
		{
			click_alertOK();
			move_activeWindow();
		}
		{
			//テンプレート名
			List<WebElement> elements = webdriver.findElements(By.id("template_name"));
			WebElement element = webdriver.findElement(By.id("template_name"));
			element.clear();
			element.sendKeys("");
		}
		{
			//検索ボタン
			List<WebElement> elements = webdriver.findElements(By.className("middle_btn"));
			WebElement element = webdriver.findElement(By.className("middle_btn"));
			element.click();
		}
		{
			//更新ボタン
			List<WebElement> elements = webdriver.findElements(By.className("middle_btn"));
			WebElement element = webdriver.findElement(By.className("middle_btn"));
			for (WebElement webElement : elements) {
				if("更新".equals(webElement.getAttribute("value"))){
					webElement.click();
					break;
				}
			}
		}
		{
			move_anotherWindow();
		}
		{
			//表示順
			List<WebElement> elements = webdriver.findElements(By.id("display_num"));
			WebElement element = webdriver.findElement(By.id("display_num"));
			element.clear();
			element.sendKeys("167");
		}
		{
			//業種
			List<WebElement> elements = webdriver.findElements(By.id("company_industry_id"));
			WebElement element = webdriver.findElement(By.id("company_industry_id"));
			Select select=new Select(element);
			select.selectByIndex(2);
		}
		{
			//テンプレート名
			List<WebElement> elements = webdriver.findElements(By.id("template_name"));
			WebElement element = webdriver.findElement(By.id("template_name"));
			element.clear();
			element.sendKeys("testByToolForUpdateAfter");
		}
		{
			//個別清算者
			List<WebElement> elements = webdriver.findElements(By.className("main_unit"));
			WebElement element = webdriver.findElement(By.className("main_unit"));
			elements.get(2).click();
		}
		{
			//登録ボタン
			List<WebElement> elements = webdriver.findElements(By.className("middle_btn"));
			WebElement element = webdriver.findElement(By.className("middle_btn"));
			element.click();
		}
		{
			click_alertOK();
			move_activeWindow();
		}

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
			request.put("id", 109);
			request.put("html", URLEncoder.encode(strResultWeb, "UTF-8"));
			request.put("db", URLEncoder.encode(strResultDB, "UTF-8"));
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