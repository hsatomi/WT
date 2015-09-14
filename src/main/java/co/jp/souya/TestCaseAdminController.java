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

import co.jp.souya.dto.TestCaseAdminDTO;
import co.jp.souya.service.TestCaseAdminSvc;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/testcaseadmin")
public class TestCaseAdminController {

	private static final Logger logger = LoggerFactory
			.getLogger(TestCaseAdminController.class);

	@Autowired
	private TestCaseAdminSvc testCaseAdminSvc;

	@RequestMapping(method = RequestMethod.GET)
	public String get(Locale locale, Model model,
			@RequestParam(value = "id", required = true) Integer id) {
		logger.info("get");

		TestCaseAdminDTO dto = testCaseAdminSvc.getDTO(id);
		model.addAttribute("dto", dto);

		return "TestCaseAdmin";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String post(Locale locale, Model model) {
		logger.info("post");
		return "TestCaseAdmin";
	}

}
