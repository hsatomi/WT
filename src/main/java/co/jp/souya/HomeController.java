package co.jp.souya;

import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.jp.souya.jpa.Customer;
import co.jp.souya.jpa.Person;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

//	/**
//	 * sample code
//	 * Simply selects the home view to render by returning its name.
//	 */
//	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
//	public String welcome(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//
//		String formattedDate = dateFormat.format(date);
//
//		model.addAttribute("serverTime", formattedDate );
//
//		return "home";
//	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String def(Locale locale, Model model) {
		return index(locale,model);
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		logger.info("index page");
		return "index";
	}

	@RequestMapping(value = "/function", method = RequestMethod.GET)
	public String function(Locale locale, Model model) {
		logger.info("function page");
		return "function";
	}

	@RequestMapping(value = "/function_list", method = RequestMethod.GET)
	public String function_list(Locale locale, Model model) {
		logger.info("function_list page");
		return "function_list";
	}

	@RequestMapping(value = "/parameter_pattern", method = RequestMethod.GET)
	public String parameter_pattern(Locale locale, Model model) {
		logger.info("parameter_pattern page");
		return "parameter_pattern";
	}

	@RequestMapping(value = "/transition_pattern", method = RequestMethod.GET)
	public String transition_pattern(Locale locale, Model model) {
		logger.info("transition_pattern page");



		try{
			test2();
		}catch(Exception e){
		}




		return "transition_pattern";
	}


	public void test(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		Customer customer;

		tx.begin();

		customer = new Customer();
//		if (args.length >= 2) {
//			customer = em.find(Customer.class, Long.valueOf(args[1]));
//		} else {
//			customer = new Customer();
//		}

		customer.setName("test客");
		em.persist(customer);
		tx.commit();

//		Customer found = em.find(Customer.class, customer.getId());
//		System.out.println("PERSISTED OBJECT: " + found);

		TypedQuery<Customer> query = em.createNamedQuery(Customer.FIND_ALL, Customer.class);
		for (Customer c : query.getResultList()) {
			System.out.println("OBJECTS IN TABLE: " + c);
		}

		em.close();
		emf.close();
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

			Person personDB = em.find(Person.class, 1);

			int i = 43;

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
