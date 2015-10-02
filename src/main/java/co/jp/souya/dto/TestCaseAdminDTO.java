package co.jp.souya.dto;

import java.util.List;

import co.jp.souya.jpa.DisplayAdmin;
import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.MovePatternAdmin;
import co.jp.souya.jpa.MovePatternDetail;
import co.jp.souya.jpa.TestCaseAdmin;

public class TestCaseAdminDTO {

	public TestCaseAdminDTO() {
	}

	private TestCaseAdmin テストケース管理;
	private DisplayAdmin 画面管理;
	private MovePatternAdmin 遷移パターン管理;
	private List<MovePatternDetail> 遷移パターン明細リスト;
	private List<InputPattern> 入力パターンリスト;
	private String ジェンキンスURL;

	public TestCaseAdmin getテストケース管理() {
		return テストケース管理;
	}

	public void setテストケース管理(TestCaseAdmin テストケース管理) {
		this.テストケース管理 = テストケース管理;
	}

	public MovePatternAdmin get遷移パターン管理() {
		return 遷移パターン管理;
	}

	public void set遷移パターン管理(MovePatternAdmin 遷移パターン管理) {
		this.遷移パターン管理 = 遷移パターン管理;
	}

	public List<MovePatternDetail> get遷移パターン明細リスト() {
		return 遷移パターン明細リスト;
	}

	public void set遷移パターン明細リスト(List<MovePatternDetail> 遷移パターン明細リスト) {
		this.遷移パターン明細リスト = 遷移パターン明細リスト;
	}

	public List<InputPattern> get入力パターンリスト() {
		return 入力パターンリスト;
	}

	public void set入力パターンリスト(List<InputPattern> 入力パターンリスト) {
		this.入力パターンリスト = 入力パターンリスト;
	}

	public String getジェンキンスURL() {
		return ジェンキンスURL;
	}

	public void setジェンキンスURL(String ジェンキンスurl) {
		ジェンキンスURL = ジェンキンスurl;
	}

	public DisplayAdmin get画面管理() {
		return 画面管理;
	}

	public void set画面管理(DisplayAdmin 画面管理) {
		this.画面管理 = 画面管理;
	}


}
