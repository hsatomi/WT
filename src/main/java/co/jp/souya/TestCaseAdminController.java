package co.jp.souya;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.jp.souya.dto.TestCaseAdminDTO;
import co.jp.souya.jpa.InputPattern;
import co.jp.souya.service.TestCaseAdminSvc;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/TestCaseAdmin")
public class TestCaseAdminController {

	private static final Logger logger = LoggerFactory
			.getLogger(TestCaseAdminController.class);

	@Autowired
	private TestCaseAdminSvc testCaseAdminSvc;

	/**
	 * テストケース管理画面　標準ページ
	 * @param locale
	 * @param model
	 * @param id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String get(
			Locale locale,
			Model model,
			@RequestParam(value = "id", required = true) Integer id

	) throws UnsupportedEncodingException {
		logger.info("get");

		TestCaseAdminDTO dto = testCaseAdminSvc.getDTO(id);
		model.addAttribute("dto", dto);
		return "TestCaseAdmin";
	}



	/**
	 * html結果参照ページ
	 * @param locale
	 * @param model
	 * @param id
	 * @param input_id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/htmlResult",method = RequestMethod.GET)
	public String htmlResult(
			Locale locale,
			Model model,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "input_id", required = true) Integer input_id

	) throws UnsupportedEncodingException {
		logger.info("htmlResult");

		TestCaseAdminDTO dto = testCaseAdminSvc.getDTO(id);
		String strDto = "";
		// 欲しいのは入力パターンid1つ
		for (InputPattern inputPattern : dto.get入力パターンリスト()) {
			if (input_id.equals(inputPattern.getId())) {
				strDto = inputPattern.getHtml();
				strDto = URLDecoder.decode(strDto,"UTF-8");
				strDto = strDto.replace("<", "&lt");
				break;
			}
		}
		model.addAttribute("dto", strDto);
		return "TestCaseAdminConfirm";
	}


	@RequestMapping(value="/htmlDif",method = RequestMethod.GET)
	public String htmlDif(
			Locale locale,
			Model model,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "input_id", required = true) Integer input_id

	) throws UnsupportedEncodingException {
		logger.info("htmlDif");

		TestCaseAdminDTO dto = testCaseAdminSvc.getDTO(id);
		String strDto = "";
		// 欲しいのは入力パターンid1つ
		for (InputPattern inputPattern : dto.get入力パターンリスト()) {
			if (input_id.equals(inputPattern.getId())) {
				strDto = inputPattern.getHtml差異();
				strDto = URLDecoder.decode(strDto,"UTF-8");
				strDto = strDto.replace("<", "&lt");
				break;
			}
		}
		model.addAttribute("dto", strDto);
		return "TestCaseAdminConfirm";
	}

}
