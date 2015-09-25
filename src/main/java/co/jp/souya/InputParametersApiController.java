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

import co.jp.souya.jpa.ParametaValue;
import co.jp.souya.requestbody.ReqInputParameters;
import co.jp.souya.service.InputPatternSvc;
import co.jp.souya.service.ParametaValueSvc;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "/InputParameters/api")
public class InputParametersApiController {

	private static final Logger logger = LoggerFactory
			.getLogger(InputParametersApiController.class);

	@Autowired
	private InputPatternSvc inputPatternSvc;

	@Autowired
	private ParametaValueSvc parametaValueSvc;

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean update(@RequestBody ReqInputParameters req)
			throws UnsupportedEncodingException {
		logger.info("update");

		// 登録 or 更新
		req.inputPattern = inputPatternSvc.update(req.inputPattern);
		if (req.inputPattern == null)
			return false;

		for (ParametaValue parametaValue : req.list) {
			// 入力チェック・自動除外
			if (parametaValue.getId() == null) {
				if (parametaValue.getエレメント型() == null
						|| parametaValue.getエレメント型().isEmpty()) {
					continue;
				}
				if (parametaValue.getエレメント名() == null
						|| parametaValue.getエレメント名().isEmpty()) {
					continue;
				}
				if (parametaValue.getアクション() == null
						|| parametaValue.getアクション().isEmpty()) {
					continue;
				}
			}
			// TODO:削除ロジック

			parametaValue.set入力パターンid(req.inputPattern.getId());
			parametaValue = parametaValueSvc.update(parametaValue);
			if (parametaValue == null)
				return false;
		}

		return true;
	}

}
