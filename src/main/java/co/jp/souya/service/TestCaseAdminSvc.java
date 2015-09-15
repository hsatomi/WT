package co.jp.souya.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.jp.souya.dto.TestCaseAdminDTO;
import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.MovePatternAdmin;
import co.jp.souya.jpa.MovePatternDetail;
import co.jp.souya.jpa.TestCaseAdmin;

@Service
public class TestCaseAdminSvc extends BaseSvc {
	private static final Logger logger = LoggerFactory
			.getLogger(TestCaseAdminSvc.class);

	public TestCaseAdminSvc() {
		logger.info(this.getClass().getName());
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

}
