package co.jp.souya.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.jp.souya.HomeController;
import co.jp.souya.jpa.ProjectAdmin;

@Service
public class TestCaseAdminSvc {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public TestCaseAdminSvc() {
		// TODO 自動生成されたコンストラクター・スタブ

		logger.info("TestCaseAdminSvcコンストラクタ");

	}


	public void test2(){
		EntityManagerFactory emf;
		EntityManager em;

		emf = Persistence.createEntityManagerFactory("pu-sqlite-jpa");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		try {

//			//Persist in database
//			Person person = new Person();
//			person.setName("person2");
//			em.persist(person);
//			em.getTransaction().commit();
//
//			//Find by id
//			Person personDB = em.find(Person.class, person.getId());

			//Person personDB = em.find(Person.class, 1);


			ProjectAdmin admin = em.find(ProjectAdmin.class, 1);




			int i = 43;
			int j = i;

//			Assert.assertEquals(person.getName(), personDB.getName());

		} catch (Throwable e) {
			e.printStackTrace();
			Assert.fail();
		}finally{

			em.close();
			emf.close();
		}

	}
}
