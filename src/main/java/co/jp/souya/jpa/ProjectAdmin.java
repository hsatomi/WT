package co.jp.souya.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Josue R G Junior josueribeiro.jr@gmail.com
 */
@Entity
public class ProjectAdmin implements Serializable {

	private static final long serialVersionUID = -7250234396452258822L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String プロジェクト名;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getプロジェクト名() {
		return プロジェクト名;
	}

	public void setプロジェクト名(String プロジェクト名) {
		this.プロジェクト名 = プロジェクト名;
	}


}
