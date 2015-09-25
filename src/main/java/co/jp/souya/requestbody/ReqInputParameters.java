package co.jp.souya.requestbody;

import java.util.ArrayList;
import java.util.List;

import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.ParametaValue;




public class ReqInputParameters {

	public ReqInputParameters(){
		list = new ArrayList<ParametaValue>();
	}

	public InputPattern inputPattern ;

	public List<ParametaValue> list;


}