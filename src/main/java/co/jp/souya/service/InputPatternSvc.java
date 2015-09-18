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

	/**
	 * テスト結果をアップデートする
	 * @param id
	 * @param w実行回数
	 * @param w判定結果
	 * @param wJob状況
	 * @param wHtml
	 * @param wDb
	 * @param wスナップショットBase64
	 * @return
	 */
	public boolean updateTestResult(int id,
			int w実行回数,
			String w判定結果,
			String wJob状況,
			String wスナップショットBase64,
			String wHtml,
			String wDb
			) {

		try {
			init();
			tx.begin();

			InputPattern dao = em.find(InputPattern.class, id);
			dao.set判定結果(w判定結果);
			dao.setJob状況(wJob状況);
			dao.set遷移結果(wスナップショットBase64);
			dao.set実行回数(w実行回数);
			dao.setDb(wDb);
			dao.setHtml(wHtml);
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

	/**
	 * テスト結果の正解値（HTMLとDB）をアップデートする
	 * @param id
	 * @return
	 */
	public boolean updateResult(int id, String wHTML, String wDB) {

		try {
			init();
			tx.begin();

			InputPattern dao = em.find(InputPattern.class, id);
			dao.setDb正解(wDB);
			dao.setHtml正解(wHTML);
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

	/**
	 * テストケースをリセットする
	 * @param id
	 * @return
	 */
	public boolean reset(int id) {

		try {
			init();
			tx.begin();

			InputPattern dao = em.find(InputPattern.class, id);
			dao.set実行回数(0);
			dao.setJob状況("");
			dao.set判定結果("");
			//TODO:全部リセットする？
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
