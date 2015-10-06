package co.jp.souya.dto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.ParametaValue;
import co.jp.souya.tool.TTConst;
import co.jp.souya.tool.TTUtility;


public class InputPatternDTO {

	private List<String> アクションリスト;
	private InputPattern 入力パターン;
	private List<ParametaValue> パラメタ値リスト;

	public InputPatternDTO() {
		入力パターン = new InputPattern();
		パラメタ値リスト = new ArrayList<ParametaValue>();
		アクションリスト = new ArrayList<String>();

		{
			Class<TTConst> c = TTConst.class;
			for (Field f1 : c.getFields()) {
				f1.setAccessible(true);
				try {
					String strFetch = TTUtility.fetchPatternChar("ACTION*", f1.getName());
					if(strFetch.isEmpty()) continue;
					アクションリスト.add((String)f1.get(null));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public InputPattern get入力パターン() {
		return 入力パターン;
	}

	public void set入力パターン(InputPattern 入力パターン) {
		this.入力パターン = 入力パターン;
	}

	public List<ParametaValue> getパラメタ値リスト() {
		return パラメタ値リスト;
	}

	public void setパラメタ値リスト(List<ParametaValue> パラメタ値リスト) {
		this.パラメタ値リスト = パラメタ値リスト;
	}

	public List<String> getアクションリスト() {
		return アクションリスト;
	}

	public void setアクションリスト(List<String> アクションリスト) {
		this.アクションリスト = アクションリスト;
	}

}
