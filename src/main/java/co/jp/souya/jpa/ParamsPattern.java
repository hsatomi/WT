package co.jp.souya.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "ParamsPattern" database table.
 * 
 */
@Entity
@Table(name="\"ParamsPattern\"")
@NamedQuery(name="ParamsPattern.findAll", query="SELECT p FROM ParamsPattern p")
public class ParamsPattern implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"id\"")
	private int id;

	@Column(name="\"テストケースid\"")
	private int テストケースid;

	public ParamsPattern() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getテストケースid() {
		return this.テストケースid;
	}

	public void setテストケースid(int テストケースid) {
		this.テストケースid = テストケースid;
	}

}