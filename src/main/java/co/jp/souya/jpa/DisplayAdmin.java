package co.jp.souya.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "DisplayAdmin" database table.
 * 
 */
@Entity
@Table(name="\"DisplayAdmin\"")
@NamedQuery(name="DisplayAdmin.findAll", query="SELECT d FROM DisplayAdmin d")
public class DisplayAdmin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"id\"")
	private int id;

	@Column(name="\"プロジェクトid\"")
	private String プロジェクトid;

	public DisplayAdmin() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getプロジェクトid() {
		return this.プロジェクトid;
	}

	public void setプロジェクトid(String プロジェクトid) {
		this.プロジェクトid = プロジェクトid;
	}

}