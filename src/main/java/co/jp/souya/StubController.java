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

import co.jp.souya.dto.TestCaseAdminDTO;
import co.jp.souya.jpa.TestCaseAdmin;
import co.jp.souya.service.TestCaseAdminSvc;
import co.jp.souya.tool.TTConst;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/")
public class StubController {

	private static final Logger logger = LoggerFactory
			.getLogger(StubController.class);

	@Autowired
	private TestCaseAdminSvc testCaseAdminSvc;

	@RequestMapping(method = RequestMethod.GET)
	public String get(Locale locale, Model model,
			@RequestParam(value = "id", required = false) Integer id

	) throws UnsupportedEncodingException {
		logger.info("get");

		String baseUrl = TTConst.URL_API_BASE + "/TestCaseAdmin?id=" ;
		StringBuffer buf = new StringBuffer();

		List<TestCaseAdmin> dtoList = testCaseAdminSvc.getAll();
		for (TestCaseAdmin testCaseAdmin : dtoList) {
			TestCaseAdminDTO dto = testCaseAdminSvc.getDTO(testCaseAdmin.getId());

			String name=dto.get遷移パターン管理().get遷移パターン名();
			String link = "<a href=\"" + baseUrl + testCaseAdmin.getId() + "\">id=" + testCaseAdmin.getId() + " " + name  +  "</a><br>";
			buf.append(link);
		}


		model.addAttribute("dto", buf.toString());
		return "home";
	}

}
