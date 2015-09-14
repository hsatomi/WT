package co.jp.souya.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.jp.souya.dto.TestCaseAdminDTO;
import co.jp.souya.jpa.InputPattern;
import co.jp.souya.jpa.MovePatternAdmin;
import co.jp.souya.jpa.MovePatternDetail;
import co.jp.souya.jpa.TestCaseAdmin;

@Service
public class TestCaseAdminSvc {
	private static final Logger logger = LoggerFactory
			.getLogger(TestCaseAdminSvc.class);

	private EntityManagerFactory emf;
	private EntityManager em;

	public TestCaseAdminSvc() {
		logger.info("TestCaseAdminSvcコンストラクタ");
	}

	private void init() {
		emf = Persistence.createEntityManagerFactory("pu-sqlite-jpa");
		em = emf.createEntityManager();
	}

	private void destroy() {
		em.close();
		emf.close();
	}

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

	public void test() {

		try {
			init();

			// //Persist in database
			// Person person = new Person();
			// person.setName("person2");
			// em.persist(person);
			// em.getTransaction().commit();
			//
			// //Find by id
			// Person personDB = em.find(Person.class, person.getId());

			// Person personDB = em.find(Person.class, 1);

			// TestCaseAdmin admin = em.find(TestCaseAdmin.class, 1);

			// MovePatternDetail admin = em.find(MovePatternDetail.class, 2);

			@SuppressWarnings("unchecked")
			List<MovePatternDetail> admin = em.createNamedQuery(
					"MovePatternDetail.findAll").getResultList();

			System.out.println(admin.size());

			System.out.println("");

		} catch (Throwable e) {
			e.printStackTrace();
			Assert.fail();
		} finally {
			destroy();
		}

	}

}
