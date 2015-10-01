package co.jp.souya;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.jp.souya.dto.InputParametersDTO;
import co.jp.souya.jpa.ParametaValue;
import co.jp.souya.jpa.TestCaseAdmin;
import co.jp.souya.service.InputPatternSvc;
import co.jp.souya.service.TestCaseAdminSvc;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/InputParameters")
public class InputParametersController {

	private static final Logger logger = LoggerFactory
			.getLogger(InputParametersController.class);

	@Autowired
	private InputPatternSvc inputPatternSvc;

	@Autowired
	private TestCaseAdminSvc testCaseAdminSvc;

	@RequestMapping(method = RequestMethod.GET)
	public String get(Locale locale, Model model,
			@RequestParam(value = "id", required = false) Integer id
			,@RequestParam(value = "test_case_id", required = false) Integer test_case_id
			,@RequestParam(value = "move_pattern_detail_id", required = false) Integer move_pattern_detail_id
			) {
		logger.info("get");
		InputParametersDTO dto = null;
		if(id==null){
			logger.info("新規");
			TestCaseAdmin testCaseAdmin = testCaseAdminSvc.get(test_case_id);

			dto = new InputParametersDTO();
			dto.get入力パターン().setテストケース管理id(testCaseAdmin.getId());

		}else{
			logger.info("変更");
			 dto = inputPatternSvc.getDTO(id);
		}

		{
			// 空白編集行を追加
			dto.getパラメタ値リスト().add(new ParametaValue());
		}
		model.addAttribute("dto", dto);
		return "InputParameters";
	}

}
