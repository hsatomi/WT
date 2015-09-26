package co.jp.souya.project1;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Aaa {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://prove-admin.rivieramypage.jp/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testAaa() throws Exception {
		driver.get(baseUrl + "/admin/login");
		driver.findElement(By.name("login_id")).clear();
		driver.findElement(By.name("login_id")).sendKeys("souya5");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("souya5");
		driver.findElement(By.cssSelector("input.middle_btn.margin_top_btn"))
				.click();

		try {
			driver.findElement(By.linkText("マスタ管理")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			driver.findElement(By.linkText("書類テンプレート")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			driver.findElement(By.cssSelector("a[href='/document_template?mode=index_from_menu']"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			driver.findElement(By.partialLinkText("document_template?mode=index_from_menu"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
