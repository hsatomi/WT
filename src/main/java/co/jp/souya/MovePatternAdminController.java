package co.jp.souya;

import java.io.UnsupportedEncodingException;
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

import co.jp.souya.jpa.MovePatternAdmin;
import co.jp.souya.service.MovePatternAdminSvc;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/MovePatternAdmin")
public class MovePatternAdminController {

	private static final Logger logger = LoggerFactory
			.getLogger(MovePatternAdminController.class);

	@Autowired
	private MovePatternAdminSvc movePatternAdminSvc;

	/**
	 * 遷移パターン管理画面　標準ページ
	 *
	 * @param locale
	 * @param model
	 * @param id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String get(Locale locale, Model model,
			@RequestParam(value = "id", required = false) Integer id

	) throws UnsupportedEncodingException {
		logger.info("get");

		List<MovePatternAdmin> dto = movePatternAdminSvc.getAll();
		dto.add(new MovePatternAdmin());
		model.addAttribute("dto", dto);
		return "MovePatternAdmin";
	}

}
