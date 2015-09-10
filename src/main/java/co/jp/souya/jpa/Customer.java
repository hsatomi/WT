package co.jp.souya.jpa;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = Customer.FIND_ALL, query = "SELECT c FROM Customer c")
})
public class Customer extends BasicEntity {

	public static final String FIND_ALL = "findAllCustomers";

	private String name;

	public Customer() {
		super();
	}

	public Customer(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return String.format(
				"id=%d, name=%s, version=%d, created_at=%s, updated_at=%s",
				getId(), getName(), getVersion(), getCreatedAt(), getUpdatedAt());
	}
}