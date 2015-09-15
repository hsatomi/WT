package co.jp.souya.core;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.jp.souya.jpa.DisplayAdmin;
import co.jp.souya.jpa.MovePatternAdmin;
import co.jp.souya.jpa.ProjectAdmin;
import co.jp.souya.jpa.TestCaseAdmin;
import co.jp.souya.service.DaoSvc;

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

	public boolean generate(Integer id, List<Integer> input_ids) {
		boolean result = false;

		// 必要情報取得
		// TODO: EntityRelationから自動取得を実装できればよい
		TestCaseAdmin daoテストケース管理
		= daoSvc.getTestCaseAdmin(id);
		MovePatternAdmin dao遷移パターン管理
		= daoSvc.getMovePatternAdmin(daoテストケース管理.get遷移パターン管理id());
		DisplayAdmin dao画面管理
		= daoSvc.getDisplayAdmin(daoテストケース管理.get画面管理id());
		ProjectAdmin daoプロジェクト管理
		= daoSvc.getProjectAdmin(dao画面管理.getプロジェクトid());



		// クラス構築
		String strGenerateCls = getHinagata();
		{
			strGenerateCls = strGenerateCls.replace(
					"co.jp.souya.prototype",
					"co.jp.souya.project" + daoプロジェクト管理.getId()
					);
		}
		String strクラス名;
		{
			long nケース管理番号 = 10000*daoプロジェクト管理.getId()
					+1*daoテストケース管理.getId();
			strクラス名 = "Case" + nケース管理番号;
			strGenerateCls = strGenerateCls.replace(
					"SeleniumHinagata",
					strクラス名
					);
		}
		{
			String str = "webdriver.get(\"http://192.168.0.142:8080/ts/\");";
			strGenerateCls = strGenerateCls.replace(
					"//初期画面位置",
					""
					);
		}










		logger.info(strGenerateCls);


		try {
			//TODO:read from property
			String filePath = "C:\\Temp\\";
			FileWriter fw = new FileWriter(filePath + strクラス名);
			fw.write(strGenerateCls);
			fw.close();




		} catch (IOException e) {
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
		strbuf.append("import org.openqa.selenium.WebDriver;");
		strbuf.append(sep);
		strbuf.append("import org.openqa.selenium.WebElement;");
		strbuf.append(sep);
		strbuf.append("import org.openqa.selenium.firefox.FirefoxDriver;");
		strbuf.append(sep);
		strbuf.append("");
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
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("	@BeforeClass");
		strbuf.append(sep);
		strbuf.append("	public static  void doBeforeCls(){");
		strbuf.append(sep);
		strbuf.append("		//初期化");
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
		strbuf.append("    	//初期画面位置");
		strbuf.append(sep);
		strbuf.append("        {");
		strbuf.append(sep);
		strbuf.append("            WebElement element = webdriver.findElement(By.name(\"user\"));");
		strbuf.append(sep);
		strbuf.append("            element.sendKeys(\"test\");");
		strbuf.append(sep);
		strbuf.append("        }");
		strbuf.append(sep);
		strbuf.append("        {");
		strbuf.append(sep);
		strbuf.append("            WebElement element = webdriver.findElement(By.name(\"btnSnd\"));");
		strbuf.append(sep);
		strbuf.append("            element.click();");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("        }");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("	@Test");
		strbuf.append(sep);
		strbuf.append("	public void testパターン1(){");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("		assertTrue(true);");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("	}");
		strbuf.append(sep);
		strbuf.append("");
		strbuf.append(sep);
		strbuf.append("	@After");
		strbuf.append(sep);
		strbuf.append("	public void doAfter(){");
		strbuf.append(sep);
		strbuf.append("		//結果評価等");
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
