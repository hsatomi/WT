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
import co.jp.souya.service.InputPatternSvc;

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

	@RequestMapping(method = RequestMethod.GET)
	public String get(Locale locale, Model model,
			@RequestParam(value = "id", required = true) Integer id
			) {
		logger.info("get");
		InputParametersDTO dto = inputPatternSvc.getDTO(id);
		model.addAttribute("dto", dto);
		return "InputParameters";
	}

}
