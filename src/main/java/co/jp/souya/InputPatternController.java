package co.jp.souya;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.jp.souya.dto.InputPatternDTO;
import co.jp.souya.jpa.ParametaValue;
import co.jp.souya.jpa.TestCaseAdmin;
import co.jp.souya.service.InputPatternSvc;
import co.jp.souya.service.TestCaseAdminSvc;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/InputPattern")
public class InputPatternController {

	private static final Logger logger = LoggerFactory
			.getLogger(InputPatternController.class);

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
		InputPatternDTO dto = null;
		if(id==null){
			logger.info("新規");
			TestCaseAdmin testCaseAdmin = testCaseAdminSvc.get(test_case_id);

			dto = new InputPatternDTO();
			dto.get入力パターン().setテストケース管理id(testCaseAdmin.getId());

		}else{
			logger.info("変更");
			 dto = inputPatternSvc.getDTO(id);
		}

		{
			int n実行順 = 1;
			List<ParametaValue> list = dto.getパラメタ値リスト();
			for (ParametaValue parametaValue : list) {
				Integer _n実行順 = parametaValue.get実行順();
				if(_n実行順==null) continue;
				if(parametaValue.get実行順() > n実行順){
					n実行順 = parametaValue.get実行順();
				}
			}
			n実行順++;

			// 空白編集行を追加
			ParametaValue newDetail = new ParametaValue();
			newDetail.set実行順(n実行順);
			dto.getパラメタ値リスト().add(newDetail);
		}
		model.addAttribute("dto", dto);
		return "InputPattern";
	}


}
