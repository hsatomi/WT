package co.jp.souya;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.jp.souya.dto.MovePatternAdminDTO;
import co.jp.souya.service.MovePatternAdminSvc;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/MovePatternDetail")
public class MovePatternDetailController {

	private static final Logger logger = LoggerFactory
			.getLogger(MovePatternDetailController.class);

	@Autowired
	private MovePatternAdminSvc movePatternAdminSvc;

	/**
	 * 遷移パターン明細画面　標準ページ
	 *
	 * @param locale
	 * @param model
	 * @param id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String get(Locale locale, Model model,
			@RequestParam(value = "id", required = true) Integer id

	) throws UnsupportedEncodingException {
		logger.info("get");

		MovePatternAdminDTO dto = movePatternAdminSvc.getDTO(id);
		model.addAttribute("dto", dto);
		return "MovePatternDetail";
	}

}
