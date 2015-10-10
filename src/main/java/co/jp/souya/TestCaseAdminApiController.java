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

import co.jp.souya.dto.InputPatternDTO;
import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.ParametaValue;
import co.jp.souya.requestbody.ReqTestCaseAdmin;
import co.jp.souya.service.InputPatternSvc;
import co.jp.souya.service.ParametaValueSvc;
import co.jp.souya.service.TestCaseAdminSvc;

/**
 * テストケース管理画面用API
 *
 * @author hsatomi
 *
 */
@RestController
@RequestMapping(value = "/TestCaseAdmin/api")
public class TestCaseAdminApiController {

	private static final Logger logger = LoggerFactory
			.getLogger(TestCaseAdminApiController.class);

	@Autowired
	private TestCaseAdminSvc testCaseAdminSvc;

	@Autowired
	private InputPatternSvc inputPatternSvc;

	@Autowired
	private ParametaValueSvc parametaValueSvc;

	/**
	 * テストケースにひもづく入力パターン削除
	 *
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean delete(@RequestBody ReqTestCaseAdmin req)
			throws UnsupportedEncodingException {
		logger.info("delete");

		if (req.id == null)
			return false;
		if (req.input_ids == null)
			return false;

		boolean result = true;
		for (Integer id : req.input_ids) {
			InputPatternDTO dto = inputPatternSvc.getDTO(id);
			for (ParametaValue dao : dto.getパラメタ値リスト()) {
				result = parametaValueSvc.delete(dao.getId());
				if(!result) logger.warn("parametaValueSvc.delete失敗");
			}

			// id:入力パターンid
			result = inputPatternSvc.delete(id);
			if (!result)
				break;

		}
		return result;
	}

	@RequestMapping(value = "/copy", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean copy(@RequestBody ReqTestCaseAdmin req)
			throws UnsupportedEncodingException {
		logger.info("copy");


		if (req.id == null)
			return false;
		if (req.input_ids == null)
			return false;

		// //No採番(最も大きい番号＋１)
		// Integer maxNo = 0;
		// TestCaseAdminDTO dto = testCaseAdminSvc.getDTO(req.id);
		// for (InputPattern inputPattern : dto.get入力パターンリスト()) {
		// if(inputPattern.getNo()==null) continue;
		// if(inputPattern.getNo() > maxNo){
		// maxNo = inputPattern.getNo();
		// }
		// }
		// maxNo++;

		boolean result = false;
		for (Integer id : req.input_ids) {
			// id:入力パターンid

			// コピー元
			InputPatternDTO dto = inputPatternSvc.getDTO(id);
			InputPattern dao = dto.get入力パターン();

			// 親作成・登録
			InputPattern newdao = new InputPattern();
			newdao.setテストケース管理id(req.id);
			newdao.setNo(dao.getNo());
			newdao.set入力パターン名(dao.get入力パターン名());
			newdao.set備考(dao.get備考());
			newdao = inputPatternSvc.update(newdao);

			for (ParametaValue dao_child : dto.getパラメタ値リスト()) {
				ParametaValue newdao_child = new ParametaValue();
				newdao_child.set入力パターンid(newdao.getId());
				newdao_child.setアクション(dao_child.getアクション());
				newdao_child.setエレメント名(dao_child.getエレメント名());
				newdao_child.setエレメント型(dao_child.getエレメント型());
				newdao_child.set値(dao_child.get値());
				newdao_child.set備考(dao_child.get備考());
				newdao_child.set型(dao_child.get型());
				newdao_child.set実行順(dao_child.get実行順());
				newdao_child.set項目名(dao_child.get項目名());
				parametaValueSvc.update(newdao_child);
			}
		}
		result = true;
		return result;
	}

}
