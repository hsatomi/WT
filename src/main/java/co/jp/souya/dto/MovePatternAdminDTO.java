package co.jp.souya.dto;

import java.util.ArrayList;
import java.util.List;

import co.jp.souya.jpa.MovePatternAdmin;
import co.jp.souya.jpa.MovePatternDetail;

public class MovePatternAdminDTO {

	public MovePatternAdminDTO() {
		遷移パターン管理 = new MovePatternAdmin();
		遷移パターン明細リスト = new ArrayList<MovePatternDetail>();
	}

	private MovePatternAdmin 遷移パターン管理;
	private List<MovePatternDetail> 遷移パターン明細リスト;

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




}
