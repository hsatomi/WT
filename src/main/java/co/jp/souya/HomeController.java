package co.jp.souya;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
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
	@RequestMapping(value = "/parameter_pattern", method = RequestMethod.GET)
	public String parameter_pattern(Locale locale, Model model) {
		logger.info("parameter_pattern page");
		return "parameter_pattern";
	}
	@RequestMapping(value = "/transition_pattern", method = RequestMethod.GET)
	public String transition_pattern(Locale locale, Model model) {
		logger.info("transition_pattern page");
		return "transition_pattern";
	}


}
