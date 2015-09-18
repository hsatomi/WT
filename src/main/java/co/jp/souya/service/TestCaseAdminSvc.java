package co.jp.souya.service;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.jp.souya.dto.TestCaseAdminDTO;
import co.jp.souya.jpa.DisplayAdmin;
import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.MovePatternAdmin;
import co.jp.souya.jpa.MovePatternDetail;
import co.jp.souya.jpa.ProjectAdmin;
import co.jp.souya.jpa.TestCaseAdmin;
import co.jp.souya.tool.TTConst;

/**
 * テストケース管理画面に絡んだサービス
 * @author hsatomi
 *
 */
@Service
public class TestCaseAdminSvc extends BaseSvc {
	private static final Logger logger = LoggerFactory
			.getLogger(TestCaseAdminSvc.class);

	private static HttpHeaders headers;
	private static RestTemplate restTemplate;

	@Autowired
	private DaoSvc daoSvc;

	public TestCaseAdminSvc() {
		logger.info(this.getClass().getName());
		//初期化
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate = new RestTemplate();
	}

	/**
	 *
	 * @param id テストケース管理id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TestCaseAdminDTO getDTO(int id) {

		TestCaseAdminDTO dto = new TestCaseAdminDTO();

		try {
			init();

			TestCaseAdmin wテストケース管理 = em.find(TestCaseAdmin.class, id);
			MovePatternAdmin w遷移パターン管理 = em.find(MovePatternAdmin.class,
					wテストケース管理.get遷移パターン管理id());
			List<MovePatternDetail> w遷移パターン明細リスト = em
					.createNamedQuery("MovePatternDetail.findListById")
					.setParameter("遷移パターン管理id", w遷移パターン管理.getId())
					.getResultList();
			List<InputPattern> w入力パターンリスト = em
					.createNamedQuery("InputPattern.findListById")
					.setParameter("テストケース管理id", wテストケース管理.getId())
					.getResultList();

			dto.setテストケース管理(wテストケース管理);
			dto.set遷移パターン管理(w遷移パターン管理);
			dto.set遷移パターン明細リスト(w遷移パターン明細リスト);
			dto.set入力パターンリスト(w入力パターンリスト);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dto;
	}


	/**
	 * テストケース管理idでjenkinsJOBを実行する
	 * @param id
	 * @return
	 */
	public boolean execJenkins(int id){
		// 結果更新
		try {
			// ----------------必要情報取得----------------
			TestCaseAdmin daoテストケース管理
			= daoSvc.getTestCaseAdmin(id);
			DisplayAdmin dao画面管理
			= daoSvc.getDisplayAdmin(daoテストケース管理.get画面管理id());
			ProjectAdmin daoプロジェクト管理
			= daoSvc.getProjectAdmin(dao画面管理.getプロジェクトid());
			//クラス名生成
			long nケース管理番号 = 10000*daoプロジェクト管理.getId()
					+1*daoテストケース管理.getId();
			String strクラス名 = "Case" + nケース管理番号;

			URI url = new URI(TTConst.URL_JENKINS_JOB_BASE + strクラス名 + "/build");
			System.out.println("URL: " + url);
			String response = restTemplate.getForObject(url, String.class);
			System.out.println("Response: " + response);

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
		return true;
	}



}
