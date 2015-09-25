package co.jp.souya.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private Integer id;

	private String プロジェクト名;

	public ProjectAdmin() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getプロジェクト名() {
		return this.プロジェクト名;
	}

	public void setプロジェクト名(String プロジェクト名) {
		this.プロジェクト名 = プロジェクト名;
	}

}