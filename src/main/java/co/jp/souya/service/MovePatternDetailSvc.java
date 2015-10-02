package co.jp.souya.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.MovePatternDetail;

/**
 * 遷移パターン管理画面に絡んだサービス
 *
 * @author hsatomi
 *
 */
@Service
public class MovePatternDetailSvc extends BaseSvc {
	private static final Logger logger = LoggerFactory
			.getLogger(MovePatternDetailSvc.class);

	private static HttpHeaders headers;
	private static RestTemplate restTemplate;

	@Autowired
	private DaoSvc daoSvc;

	public MovePatternDetailSvc() {
		logger.info(this.getClass().getName());
		// 初期化
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate = new RestTemplate();
	}

	/**
	 * idで取得
	 *
	 * @return
	 */
	public MovePatternDetail get(int id) {

		MovePatternDetail newdao = null;
		try {
			init();

			newdao = em.find(MovePatternDetail.class, id);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return newdao;
	}

	/**
	 * 更新
	 *
	 * @return
	 */
	public MovePatternDetail update(MovePatternDetail dao) {

		MovePatternDetail newdao = null;
		try {
			init();
			tx.begin();

			newdao = (dao.getId() == null) ? new MovePatternDetail() : em.find(
					MovePatternDetail.class, dao.getId());
			if (newdao == null)
				newdao = new MovePatternDetail();

			newdao.setUrl(dao.getUrl());
			newdao.set入力パターンid(dao.get入力パターンid());
			newdao.set画面タイトル(dao.get画面タイトル());
			newdao.set遷移パターン管理id(dao.get遷移パターン管理id());
			newdao.set遷移順(dao.get遷移順());
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

	/**
	 * idでひもづく入力パターンごと削除
	 *
	 * @return
	 */
	public boolean delete(int id) {

		try {
			init();
			tx.begin();

			MovePatternDetail dao = em.find(MovePatternDetail.class, id);

			if(dao.get入力パターンid() != null){
				InputPattern entity = em.find(InputPattern.class, dao.get入力パターンid());
				em.remove(entity);
			}

			{
				MovePatternDetail entity = em.find(MovePatternDetail.class, dao.getId());
				em.remove(entity);
			}

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
