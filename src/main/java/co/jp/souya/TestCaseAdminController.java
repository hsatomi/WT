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

		TestCaseAdminDTO dto = testCaseAdminSvc.getDTO(id);
		model.addAttribute("dto", dto);
		return "TestCaseAdmin";
	}




	/**
	 * 結果参照ページ
	 *
	 * @param locale
	 * @param model
	 * @param id
	 * @param input_id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/htmlResult", method = RequestMethod.GET)
	public String htmlResult(Locale locale, Model model,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "input_id", required = true) Integer input_id

	) throws UnsupportedEncodingException {
		logger.info("htmlResult");
		return getResult(locale,model,"htmlResult",id,input_id);
	}
	/**
	 * 結果参照ページ
	 *
	 * @param locale
	 * @param model
	 * @param id
	 * @param input_id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/htmlDiff", method = RequestMethod.GET)
	public String htmlDif(Locale locale, Model model,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "input_id", required = true) Integer input_id

	) throws UnsupportedEncodingException {
		logger.info("htmlDiff");
		return getResult(locale,model,"htmlDiff",id,input_id);
	}
	/**
	 * 結果参照ページ
	 *
	 * @param locale
	 * @param model
	 * @param id
	 * @param input_id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/htmlCorrect", method = RequestMethod.GET)
	public String htmlCorrect(Locale locale, Model model,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "input_id", required = true) Integer input_id

	) throws UnsupportedEncodingException {
		logger.info("htmlCorrect");
		return getResult(locale,model,"htmlCorrect",id,input_id);
	}

	/**
	 * 結果参照ページ
	 *
	 * @param locale
	 * @param model
	 * @param id
	 * @param input_id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/dbResult", method = RequestMethod.GET)
	public String dbResult(Locale locale, Model model,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "input_id", required = true) Integer input_id

	) throws UnsupportedEncodingException {
		logger.info("dbResult");
		return getResult(locale,model,"dbResult",id,input_id);
	}
	/**
	 * 結果参照ページ
	 *
	 * @param locale
	 * @param model
	 * @param id
	 * @param input_id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/dbDiff", method = RequestMethod.GET)
	public String dbDiff(Locale locale, Model model,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "input_id", required = true) Integer input_id

	) throws UnsupportedEncodingException {
		logger.info("dbDiff");
		return getResult(locale,model,"dbDiff",id,input_id);

	}
	/**
	 * 結果参照ページ
	 *
	 * @param locale
	 * @param model
	 * @param id
	 * @param input_id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/dbCorrect", method = RequestMethod.GET)
	public String dbCorrect(Locale locale, Model model,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "input_id", required = true) Integer input_id

	) throws UnsupportedEncodingException {
		logger.info("dbCorrect");
		return getResult(locale,model,"dbCorrect",id,input_id);
	}

	/**
	 * 結果参照ページ
	 *
	 * @param locale
	 * @param model
	 * @param id
	 * @param input_id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/moveResult", method = RequestMethod.GET)
	public String moveResult(Locale locale, Model model,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "input_id", required = true) Integer input_id

	) throws UnsupportedEncodingException {
		logger.info("moveResult");
		return getResult(locale,model,"moveResult",id,input_id);
	}

	/**
	 * URLEncodeされた文字列を確認画面表示用にDecodeして確認する画面リソースを返す
	 * @param locale
	 * @param model
	 * @param kind
	 * @param id
	 * @param input_id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String getResult( Locale locale, Model model,
			String kind,
			Integer id, Integer input_id) throws UnsupportedEncodingException{

		TestCaseAdminDTO dto = testCaseAdminSvc.getDTO(id);
		String strDto = "";
		// 欲しいのは入力パターンid1つ
		for (InputPattern inputPattern : dto.get入力パターンリスト()) {
			if (input_id.equals(inputPattern.getId())) {

				if("htmlResult".equals(kind)){
					strDto = inputPattern.getHtml();
				}
				if("htmlDiff".equals(kind)){
					strDto = inputPattern.getHtml差異();
				}
				if("htmlCorrect".equals(kind)){
					strDto = inputPattern.getHtml正解();
				}
				if("dbResult".equals(kind)){
					strDto = inputPattern.getDb();
				}
				if("dbDiff".equals(kind)){
					strDto = inputPattern.getDb差異();
				}
				if("dbCorrect".equals(kind)){
					strDto = inputPattern.getDb正解();
				}
				if("moveResult".equals(kind)){
					strDto = inputPattern.get遷移結果();
				}

				if (strDto == null || strDto.isEmpty())
					break;
				strDto = URLDecoder.decode(strDto, "UTF-8");
				strDto = strDto.replace("<", "&lt");
				break;
			}
		}
		model.addAttribute("dto", strDto);
		return "TestCaseAdminConfirm";
	}


}
