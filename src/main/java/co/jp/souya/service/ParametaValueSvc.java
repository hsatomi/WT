package co.jp.souya.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.jp.souya.jpa.ParametaValue;

@Service
public class ParametaValueSvc extends BaseSvc {
	private static final Logger logger = LoggerFactory
			.getLogger(ParametaValueSvc.class);

	public ParametaValueSvc() {
		logger.info(this.getClass().getName());
	}

	/**
	 * 単純取得
	 *
	 * @param id
	 * @return
	 */
	public ParametaValue get(Integer id) {
		ParametaValue dao = null;
		try {
			init();

			dao = em.find(ParametaValue.class, id);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dao;
	}

	/**
	 * 単純更新
	 *
	 * @param dao
	 * @return
	 */
	public ParametaValue update(ParametaValue dao) {
		ParametaValue newdao = null;
		try {
			init();
			tx.begin();

			newdao = (dao.getId() == null) ? new ParametaValue() : em.find(
					ParametaValue.class, dao.getId());

			newdao.set入力パターンid(dao.get入力パターンid());
			newdao.set実行順(dao.get実行順());
			newdao.set項目名(dao.get項目名());
			newdao.setエレメント型(dao.getエレメント型());
			newdao.setエレメント名(dao.getエレメント名());
			newdao.setアクション(dao.getアクション());
			newdao.set型(dao.get型());
			newdao.set値(dao.get値());
			newdao.set備考(dao.get備考());
			em.persist(newdao);

			tx.commit();

		} catch (Throwable e) {
			tx.rollback();
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return newdao;
	}

}
