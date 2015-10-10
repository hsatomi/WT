package co.jp.souya.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public abstract class BaseSvc {
	private static final Logger logger = LoggerFactory
			.getLogger(InputPatternSvc.class);

	protected EntityManagerFactory emf;
	protected EntityManager em;
	protected EntityTransaction tx;

	protected HttpHeaders headers;
	protected RestTemplate restTemplate;

	public BaseSvc(){
		logger.info("BaseSvcコンストラクタ");
		// 初期化
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate = new RestTemplate();
	}

	protected void init() {
		emf = Persistence.createEntityManagerFactory("pu-sqlite-jpa");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	protected void destroy() {
		if(em!=null){
			try{
				em.close();
			}catch(Exception e){
				logger.error("!TODO:em.closeエラー原因不明  " + e.getMessage());
			}
		}
		if(emf!=null){
			try{
				emf.close();
			}catch(Exception e){
				logger.error("!TODO:emf.closeエラー原因不明  " + e.getMessage());
			}
		}
	}

}
