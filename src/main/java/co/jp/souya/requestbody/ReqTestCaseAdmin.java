package co.jp.souya.requestbody;

import java.util.ArrayList;
import java.util.List;




public class ReqTestCaseAdmin {

	public ReqTestCaseAdmin(){
		input_ids = new ArrayList<Integer>();
	}

	/**
	 * テストケース管理id
	 */
	public Integer id;

	/**
	 * 入力パターンids
	 */
	public List<Integer> input_ids;


}