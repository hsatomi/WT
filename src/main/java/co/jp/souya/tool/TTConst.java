package co.jp.souya.tool;



public class TTConst {

	//デフォルト値と考えること
	//運用時値はDIで上書き設定する -> 設定：servlet-context.xml
	public static String PATH_GENERATESRC_OUTPUT="C:\\Temp\\";
	public static String PATH_GITPUSHSCRIPT="C:\\Users\\hsatomi\\git\\wt\\auto_git_for_local.cmd";
	public static String URL_API_BASE="http://localhost:8080/souya";
	public static String URL_JENKINS_JOB_BASE="http://192.168.0.142:8081/jenkins/job/";
	public static String URL_UPDATE_TEST_RESULT="/api/updateTestResult";
	public static String URL_UPDATE_RESULT="/api/updateResult";
	public static String URL_RESET_TESTCASE="/api/resetTestCase";
	public static String URL_GENERATE_TESTCASE="/api/generateTestCase";
	public static String URL_DELETE_TESTCASE="/api/deleteTestCase";
	public static String URL_EXECJENKINS="/api/execJenkins";
	public static String URL_POLLINGJENKINS="/api/pollingJenkins";
	public static String URL_ANALYZE="/api/analyze";
	public static String TEST_RESULT_OK="OK";
	public static String TEST_RESULT_NG="NG";
	public static String TEST_RESULT_ERROR="ERROR";
	public static String ACTION_CLICK="click";
	public static String ACTION_CLEAR="clear";
	public static String ACTION_SENDKEYS="sendKeys";
	public static String ACTION_SELECTBYINDEX="selectByIndex";
	public static String ACTION_SELECTBYVALUE="selectByValue";
	public static String ACTION_CLICK_RADIO="clickRadio";
	public static String ACTION_CLICK_BYATTRVALUE="clickByAttrValue";
	public static String JOB_STATUS_START="登録済み";
	public static String JOB_STATUS_DELETED="削除済み";
	public static String JOB_STATUS_EXEC="実行中";
	public static String JOB_STATUS_FINISH="完了";


	//↓↓↓↓↓↓↓自動生成したセッター INJECTに必要↓↓↓↓↓↓↓↓

	public static void setPATH_GENERATESRC_OUTPUT(String pATH_GENERATESRC_OUTPUT) {
		PATH_GENERATESRC_OUTPUT = pATH_GENERATESRC_OUTPUT;
	}
	public static void setPATH_GITPUSHSCRIPT(String pATH_GITPUSHSCRIPT) {
		PATH_GITPUSHSCRIPT = pATH_GITPUSHSCRIPT;
	}
	public static void setACTION_CLICK(String aCTION_CLICK) {
		ACTION_CLICK = aCTION_CLICK;
	}
	public static void setACTION_SENDKEYS(String aCTION_SENDKEYS) {
		ACTION_SENDKEYS = aCTION_SENDKEYS;
	}
	public static void setURL_API_BASE(String uRL_API_BASE) {
		URL_API_BASE = uRL_API_BASE;
	}
	public static void setURL_UPDATE_TEST_RESULT(String uRL_UPDATE_TEST_RESULT) {
		URL_UPDATE_TEST_RESULT = uRL_UPDATE_TEST_RESULT;
	}
	public static void setURL_UPDATE_RESULT(String uRL_UPDATE_RESULT) {
		URL_UPDATE_RESULT = uRL_UPDATE_RESULT;
	}
	public static void setURL_RESET_TESTCASE(String uRL_RESET_TESTCASE) {
		URL_RESET_TESTCASE = uRL_RESET_TESTCASE;
	}
	public static void setURL_GENERATE_TESTCASE(String uRL_GENERATE_TESTCASE) {
		URL_GENERATE_TESTCASE = uRL_GENERATE_TESTCASE;
	}
	public static void setURL_EXECJENKINS(String uRL_EXECJENKINS) {
		URL_EXECJENKINS = uRL_EXECJENKINS;
	}
	public static void setURL_JENKINS_JOB_BASE(String uRL_JENKINS_JOB_BASE) {
		URL_JENKINS_JOB_BASE = uRL_JENKINS_JOB_BASE;
	}
	public static void setTEST_RESULT_OK(String tEST_RESULT_OK) {
		TEST_RESULT_OK = tEST_RESULT_OK;
	}
	public static void setTEST_RESULT_NG(String tEST_RESULT_NG) {
		TEST_RESULT_NG = tEST_RESULT_NG;
	}
	public static void setJOB_STATUS_START(String jOB_STATUS_START) {
		JOB_STATUS_START = jOB_STATUS_START;
	}
	public static void setJOB_STATUS_EXEC(String jOB_STATUS_EXEC) {
		JOB_STATUS_EXEC = jOB_STATUS_EXEC;
	}
	public static void setJOB_STATUS_FINISH(String jOB_STATUS_FINISH) {
		JOB_STATUS_FINISH = jOB_STATUS_FINISH;
	}



}
