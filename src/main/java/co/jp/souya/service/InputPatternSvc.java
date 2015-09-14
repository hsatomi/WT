package co.jp.souya.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.jp.souya.jpa.InputPattern;

@Service
public class InputPatternSvc extends BaseSvc {
	private static final Logger logger = LoggerFactory
			.getLogger(InputPatternSvc.class);


	public InputPatternSvc() {
		logger.info("TestCaseAdminSvcコンストラクタ");
	}

	public boolean updateTestResult(int id, String w判定結果,String wJob状況) {

		try {
			init();
			tx.begin();

			InputPattern dao = em.find(InputPattern.class, id);
			dao.set判定結果(w判定結果);
			dao.setJob状況(wJob状況);
			em.persist(dao);

			tx.commit();

		} catch (Throwable e) {
			tx.rollback();
			logger.error(e.getMessage(), e);
			return false;
		} finally {
			destroy();
		}

		return true;
	}

}
