package co.jp.souya;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.jp.souya.service.TestCaseAdminSvc;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	TestCaseAdminSvc svc;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String def(Locale locale, Model model) {
		return index(locale,model);
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		logger.info("index page");
		return "index";
	}

	@RequestMapping(value = "/function", method = RequestMethod.GET)
	public String function(Locale locale, Model model) {
		logger.info("function page");
		return "function";
	}

	@RequestMapping(value = "/function_list", method = RequestMethod.GET)
	public String function_list(Locale locale, Model model) {
		logger.info("function_list page");
		return "function_list";
	}

	@RequestMapping(value = "/InputParameters", method = RequestMethod.GET)
	public String InputParameters(Locale locale, Model model) {
		logger.info("入力パラメータ画面 page");
		return "InputParameters";
	}

	@RequestMapping(value = "/TestCaseAdmin", method = RequestMethod.GET)
	public String TestCaseAdmin(Locale locale, Model model) {
		logger.info("テストケース管理画面");


		svc.test2();


		return "TestCaseAdmin";
	}






}
