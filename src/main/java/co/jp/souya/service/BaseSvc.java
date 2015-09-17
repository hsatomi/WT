package co.jp.souya.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseSvc {
	private static final Logger logger = LoggerFactory
			.getLogger(InputPatternSvc.class);

	protected EntityManagerFactory emf;
	protected EntityManager em;
	protected EntityTransaction tx;

	public BaseSvc(){
		logger.info("BaseSvcコンストラクタ");
	}

	protected void init() {
		emf = Persistence.createEntityManagerFactory("pu-sqlite-jpa");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	protected void destroy() {
		em.close();
		emf.close();
	}

}
