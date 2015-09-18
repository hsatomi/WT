package co.jp.souya.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.jp.souya.jpa.DisplayAdmin;
import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.MovePatternAdmin;
import co.jp.souya.jpa.MovePatternDetail;
import co.jp.souya.jpa.ParametaValue;
import co.jp.souya.jpa.ProjectAdmin;
import co.jp.souya.jpa.TestCaseAdmin;

/**
 * 各エンティティへの単純なアクセッサを提供します
 * @author hsatomi
 *
 */
@Service
public class DaoSvc extends BaseSvc {
	private static final Logger logger = LoggerFactory.getLogger(DaoSvc.class);

	public DaoSvc() {
		logger.info(this.getClass().getName());
	}

	public ProjectAdmin getProjectAdmin(int id) {

		ProjectAdmin dao = null;

		try {
			init();

			dao = em.find(ProjectAdmin.class, id);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dao;
	}

	public DisplayAdmin getDisplayAdmin(int id) {

		DisplayAdmin dao = null;

		try {
			init();

			dao = em.find(DisplayAdmin.class, id);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dao;
	}

	public MovePatternAdmin getMovePatternAdmin(int id) {

		MovePatternAdmin dao = null;

		try {
			init();

			dao = em.find(MovePatternAdmin.class, id);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dao;
	}

	@SuppressWarnings("unchecked")
	public List<MovePatternDetail> getMovePatternDetailList(int id) {

		List<MovePatternDetail> dao = new ArrayList<MovePatternDetail>();

		try {
			init();
			dao = em.createNamedQuery("MovePatternDetail.findListById")
					.setParameter("遷移パターン管理id", id).getResultList();

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dao;
	}

	/**
	 *
	 * @param id
	 *            テストケース管理id
	 * @return
	 */
	public TestCaseAdmin getTestCaseAdmin(int id) {

		TestCaseAdmin dao = null;

		try {
			init();

			dao = em.find(TestCaseAdmin.class, id);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dao;
	}

	/**
	 *
	 * @param id
	 *            入力パターンid
	 * @return
	 */
	public InputPattern getInputPattern(int id) {

		InputPattern dao = null;

		try {
			init();

			dao = em.find(InputPattern.class, id);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dao;
	}

	/**
	 *
	 * @param id
	 *            入力パターンid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ParametaValue> getParametaValueList(int id) {

		List<ParametaValue> dao = new ArrayList<ParametaValue>();

		try {
			init();
			dao = em.createNamedQuery("ParametaValue.findListById")
					.setParameter("入力パターンid", id).getResultList();

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dao;
	}

}
