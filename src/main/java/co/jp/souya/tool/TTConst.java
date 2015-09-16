package co.jp.souya.tool;

public class TTConst {

	//TODO:環境によってチューニングが必要なため
	//TODO:autoconfigで自動読み込み、プロパティ化するなど、外部パラメタと同義とする

	public static String PATH_GENERATESRC_OUTPUT="C:\\Temp\\";

	public static String ACTION_CLICK="click";
	public static String ACTION_SENDKEYS="sendKeys";

	public static String URL_API_BASE="http://localhost:8080/souya";
	public static String URL_UPDATE_TEST_RESULT="/api/updateTestResult";
	public static String URL_UPDATE_RESULT="/api/updateResult";
	public static String TEST_RESULT_OK="OK";
	public static String TEST_RESULT_NG="NG";
	public static String JOB_STATUS_START="登録済み";
	public static String JOB_STATUS_EXEC="実行中";
	public static String JOB_STATUS_FINISH="完了";

}
