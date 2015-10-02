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

import co.jp.souya.requestbody.ReqTestCaseAdmin;
import co.jp.souya.service.InputPatternSvc;
import co.jp.souya.service.ParametaValueSvc;

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
			// id:入力パターンid

			result = inputPatternSvc.delete(id);
			if (!result)
				break;

		}
		return result;
	}

}
