package co.jp.souya.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.jp.souya.dto.InputParametersDTO;
import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.ParametaValue;

@Service
public class InputPatternSvc extends BaseSvc {
	private static final Logger logger = LoggerFactory
			.getLogger(InputPatternSvc.class);

	public InputPatternSvc() {
		logger.info(this.getClass().getName());
	}

	@SuppressWarnings("unchecked")
	public InputParametersDTO getDTO(int id) {

		InputParametersDTO dto = new InputParametersDTO();

		try {
			init();

			InputPattern w入力パターン = em.find(InputPattern.class, id);
			List<ParametaValue> wパラメタ値リスト = em
					.createNamedQuery("ParametaValue.findListById")
					.setParameter("入力パターンid", w入力パターン.getId()).getResultList();
			dto.set入力パターン(w入力パターン);
			dto.setパラメタ値リスト(wパラメタ値リスト);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dto;
	}

	public boolean updateTestResult(int id, String w判定結果, String wJob状況) {

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
