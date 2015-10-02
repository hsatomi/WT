package co.jp.souya;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.jp.souya.dto.MovePatternAdminDTO;
import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.MovePatternDetail;
import co.jp.souya.requestbody.ReqMovePatternDetail;
import co.jp.souya.service.InputPatternSvc;
import co.jp.souya.service.MovePatternAdminSvc;
import co.jp.souya.service.MovePatternDetailSvc;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "/MovePatternDetail/api")
public class MovePatternDetailApiController {

	private static final Logger logger = LoggerFactory
			.getLogger(MovePatternDetailApiController.class);

	@Autowired
	private MovePatternAdminSvc movePatternAdminSvc;

	@Autowired
	private MovePatternDetailSvc movePatternDetailSvc;

	@Autowired
	private InputPatternSvc inputPatternSvc;

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean update(@RequestBody ReqMovePatternDetail req)
			throws UnsupportedEncodingException {
		logger.info("update");

		// 登録 or 更新
		for (MovePatternDetail movePatternDetail : req.list) {
			// 入力チェック・自動除外
			// TODO:削除ロジック

			MovePatternDetail newdao = movePatternDetailSvc
					.update(movePatternDetail);
			if (newdao == null)
				return false;
		}

		return true;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean delete(@RequestBody ReqMovePatternDetail req)
			throws UnsupportedEncodingException {
		logger.info("delete");

		if(req.id == null) return false;
		//ひもづく入力パターンごと削除
		boolean result = movePatternDetailSvc.delete(req.id);

		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean add(@RequestBody ReqMovePatternDetail req)
			throws UnsupportedEncodingException {
		logger.info("add");

		if (req.id == null) {
			return false;
		}

		MovePatternAdminDTO dto = movePatternAdminSvc.getDTO(req.id);
		InputPattern inputPatternDao = new InputPattern();
		{
			// TODO:テストケース管理に紐づかないものをid=0で登録する 定数化するなり何か標準化する
			inputPatternDao.setテストケース管理id(0);
			inputPatternDao = inputPatternSvc.update(inputPatternDao);
		}

		Integer max遷移順 = 0;
		{
			for (MovePatternDetail dao : dto.get遷移パターン明細リスト()) {
				if (max遷移順 < dao.get遷移順()) {
					max遷移順 = dao.get遷移順();
				}
			}
		}
		{
			MovePatternDetail newdao = new MovePatternDetail();
			newdao.set入力パターンid(inputPatternDao.getId());
			newdao.set遷移パターン管理id(dto.get遷移パターン管理().getId());
			newdao.set遷移順(max遷移順 + 1);
			movePatternDetailSvc.update(newdao);
		}

		return true;
	}
}
