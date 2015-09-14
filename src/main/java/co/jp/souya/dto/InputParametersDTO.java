package co.jp.souya.dto;

import java.util.List;

import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.ParametaValue;

public class InputParametersDTO {

	private InputPattern 入力パターン;
	private List<ParametaValue> パラメタ値リスト;

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

}
