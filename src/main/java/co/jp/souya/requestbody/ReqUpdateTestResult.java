package co.jp.souya.requestbody;


public class ReqUpdateTestResult {

	public Integer id;
	/**
	 * JOB状況
	 */
	public String jobStatus;
	/**
	 * テスト結果
	 */
	public String testResult;
	/**
	 * Image to Base64String
	 */
	public String snapshot;

	/**
	 * html状態を表す何か
	 */
	public String html;
	/**
	 * DB状態を表す何か
	 */
	public String db;


}