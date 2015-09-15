package co.jp.souya.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.jp.souya.jpa.DisplayAdmin;
import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.MovePatternAdmin;
import co.jp.souya.jpa.ProjectAdmin;
import co.jp.souya.jpa.TestCaseAdmin;

@Service
public class DaoSvc extends BaseSvc {
	private static final Logger logger = LoggerFactory.getLogger(DaoSvc.class);

	public DaoSvc() {
		logger.info(this.getClass().getName());
	}

	public ProjectAdmin getProjectAdmin(int id) {

		ProjectAdmin dto = null;

		try {
			init();

			dto = em.find(ProjectAdmin.class, id);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dto;
	}

	public DisplayAdmin getDisplayAdmin(int id) {

		DisplayAdmin dto = null;

		try {
			init();

			dto = em.find(DisplayAdmin.class, id);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dto;
	}

	public MovePatternAdmin getMovePatternAdmin(int id) {

		MovePatternAdmin dto = null;

		try {
			init();

			dto = em.find(MovePatternAdmin.class, id);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dto;
	}

	/**
	 *
	 * @param id
	 *            テストケース管理id
	 * @return
	 */
	public TestCaseAdmin getTestCaseAdmin(int id) {

		TestCaseAdmin dto = null;

		try {
			init();

			dto = em.find(TestCaseAdmin.class, id);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dto;
	}

	/**
	 *
	 * @param id
	 *            入力パターンid
	 * @return
	 */
	public InputPattern getInputPattern(int id) {

		InputPattern dto = null;

		try {
			init();

			dto = em.find(InputPattern.class, id);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			destroy();
		}

		return dto;
	}

}
