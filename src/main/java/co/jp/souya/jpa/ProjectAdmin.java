package co.jp.souya.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "ProjectAdmin" database table.
 * 
 */
@Entity
@Table(name="\"ProjectAdmin\"")
@NamedQuery(name="ProjectAdmin.findAll", query="SELECT p FROM ProjectAdmin p")
public class ProjectAdmin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"id\"")
	private int id;

	private String プロジェクト名;

	public ProjectAdmin() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getプロジェクト名() {
		return this.プロジェクト名;
	}

	public void setプロジェクト名(String プロジェクト名) {
		this.プロジェクト名 = プロジェクト名;
	}

}