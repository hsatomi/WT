package co.jp.souya.requestbody;


public class ReqUpdateTestResult {

	/**
	 * id
	 */
	public Integer id;
	/**
	 * 実行回数
	 */
	public Integer execTimes;
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