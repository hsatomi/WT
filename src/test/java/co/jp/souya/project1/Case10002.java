
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
			WebElement element = webdriver.findElement(By.name("login_id"));
			element.sendKeys("souya5");
		}
		{
			//パスワード
			WebElement element = webdriver.findElement(By.name("password"));
			element.sendKeys("souya5");
		}
		{
			//ボタン
			WebElement element = webdriver.findElement(By.className("middle_btn"));
			element.click();
		}
		//書類テンプレート
		{
			//書類テンプレート
			WebElement element = webdriver.findElement(By.linkText("マスタ管理"));
			element.click();
		}
		{
			//書類テンプレート
			WebElement element = webdriver.findElement(By.linkText("書類テンプレート"));
			element.click();
		}
		//一覧
		{
			//新規登録
			WebElement element = webdriver.findElement(By.cssSelector("form[name=\"input_form\"] > input.middle_btn"));
			element.click();
		}
		{
			move_anotherWindow();
		}

	}

		//テストケース開始
	@Test
	public void Test4() throws Exception{

		//実行
		hndlsNow = webdriver.getWindowHandles();
		boolean bTestResult = true;
		{
			//登録ボタン
			WebElement element = webdriver.findElement(By.className("middle_btn"));
			element.click();
		}
		{
			click_alertNG();
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
		String strSnapshot = tryGetPicture();


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
			request.put("id", 106);
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
		Thread.sleep(300);
		String hndlMain = webdriver.getWindowHandle();
		Set<String> windowList = webdriver.getWindowHandles();
		for (String hndlWnd : windowList) {

			if(!hndlMain.equals(hndlWnd)){
				webdriver.switchTo().window(hndlWnd);
				break;
			}
		}
	}

	//アラートをOKクリックする
	private void click_alertOK() throws Exception{
		Thread.sleep(300);
		Alert alert = null;
		try{
			alert = webdriver.switchTo().alert();
			alert.accept();
		}catch(Exception e){
			System.out.println("no alert");
		}
	}

	//アラートをキャンセルクリックする
	private void click_alertNG() throws Exception{
		Thread.sleep(300);
		Alert alert = null;
		try{
			alert = webdriver.switchTo().alert();
			alert.dismiss();
		}catch(Exception e){
			System.out.println("no alert");
		}
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
	private boolean move_activeWindow() {
		try{
			webdriver.getWindowHandle();
			return true;
		}catch(Exception e){
		}

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