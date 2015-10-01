package co.jp.souya.requestbody;

import java.util.ArrayList;
import java.util.List;

import co.jp.souya.jpa.MovePatternDetail;

public class ReqMovePatternDetail {

	public ReqMovePatternDetail() {
		list = new ArrayList<MovePatternDetail>();
	}

	public Integer id;

	public List<MovePatternDetail> list;

}