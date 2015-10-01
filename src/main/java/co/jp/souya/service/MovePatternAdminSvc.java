package co.jp.souya.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.jp.souya.dto.MovePatternAdminDTO;
import co.jp.souya.jpa.MovePatternAdmin;
import co.jp.souya.jpa.MovePatternDetail;

/**
 * 遷移パターン管理画面に絡んだサービス
 *
 * @author hsatomi
 *
 */
@Service
public class MovePatternAdminSvc extends BaseSvc {
	private static final Logger logger = LoggerFactory
			.getLogger(MovePatternAdminSvc.class);

	private static HttpHeaders headers;
	private static RestTemplate restTemplate;

	@Autowired
	private DaoSvc daoSvc;

	public MovePatternAdminSvc() {
		logger.info(this.getClass().getName());
		// 初期化
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate = new RestTemplate();
	}

	/**
	 * 無条件にリストで取得
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MovePatternAdmin> getAll() {

		List<MovePatternAdmin> dto = new ArrayList<MovePatternAdmin>();

		try {
			init();

			dto = em.createNamedQuery("MovePatternAdmin.findAll")
					.getResultList();

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dto;
	}

	/**
	 * idでDTO取得
	 *
	 * @return
	 */
	public MovePatternAdminDTO getDTO(Integer id) {

		MovePatternAdminDTO dto = new MovePatternAdminDTO();

		try {
			init();

			MovePatternAdmin dao = em.find(MovePatternAdmin.class, id);
			@SuppressWarnings("unchecked")
			List<MovePatternDetail> list = em
					.createNamedQuery("MovePatternDetail.findListById")
					.setParameter("遷移パターン管理id", id).getResultList();

			dto.set遷移パターン管理(dao);
			dto.set遷移パターン明細リスト(list);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dto;
	}

	/**
	 * 更新
	 *
	 * @return
	 */
	public MovePatternAdmin update(MovePatternAdmin dao) {

		MovePatternAdmin newdao = null;
		try {
			init();
			tx.begin();

			newdao = (dao.getId() == null) ? new MovePatternAdmin() : em.find(
					MovePatternAdmin.class, dao.getId());
			if (newdao == null)
				newdao = new MovePatternAdmin();

			newdao.set遷移パターン名(dao.get遷移パターン名());
			newdao.set画面管理id(dao.get画面管理id());
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
