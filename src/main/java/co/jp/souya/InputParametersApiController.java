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

import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.ParametaValue;
import co.jp.souya.requestbody.ReqInputParameters;
import co.jp.souya.service.InputPatternSvc;

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
	private ParametaValue parametaValue; 

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean update(@RequestBody ReqInputParameters req)
			throws UnsupportedEncodingException {
		logger.info("update");
		boolean result = false;

		InputPattern dao = inputPatternSvc.get(req.inputPattern.getId());
		if(dao==null){
			dao = new InputPattern();
		}
		dao.setテストケース管理id(req.inputPattern.getテストケース管理id());
		dao.setNo(req.inputPattern.getNo());
		dao.set入力パターン名(req.inputPattern.get入力パターン名());
		dao.set備考(req.inputPattern.get備考());
		result = inputPatternSvc.update(dao);
		if(!result) return result;

		
		parametaValue.
		
		
		
		return result;
	}


}
