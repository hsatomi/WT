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

import co.jp.souya.jpa.MovePatternAdmin;
import co.jp.souya.requestbody.ReqMovePatternAdmin;
import co.jp.souya.service.MovePatternAdminSvc;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "/MovePatternAdmin/api")
public class MovePatternAdminApiController {

	private static final Logger logger = LoggerFactory
			.getLogger(MovePatternAdminApiController.class);

	@Autowired
	private MovePatternAdminSvc movePatternAdminSvc;

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean update(@RequestBody ReqMovePatternAdmin req)
			throws UnsupportedEncodingException {
		logger.info("update");

		// 登録 or 更新
		for (MovePatternAdmin movePatternAdmin : req.list) {
			// 入力チェック・自動除外
			if (movePatternAdmin.get画面管理id() == null ||
					movePatternAdmin.get遷移パターン名() == null)
			{
					continue;
			}
			// TODO:削除ロジック

			MovePatternAdmin newdao = movePatternAdminSvc.update(movePatternAdmin);
			if(newdao == null) return false;
		}

		return true;
	}

}
