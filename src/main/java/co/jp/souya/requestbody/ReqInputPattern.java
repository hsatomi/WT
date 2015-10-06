package co.jp.souya.requestbody;

import java.util.ArrayList;
import java.util.List;

import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.ParametaValue;




public class ReqInputPattern {

	public ReqInputPattern(){
		inputPattern = new InputPattern();
		list = new ArrayList<ParametaValue>();
	}

	public String seleniumCode;

	public InputPattern inputPattern ;

	public List<ParametaValue> list;

	/**
	 * パラメタ値のid
	 */
	public Integer parametaValueId;

}