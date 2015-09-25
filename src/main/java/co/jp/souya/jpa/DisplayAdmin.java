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
 * The persistent class for the "DisplayAdmin" database table.
 *
 */
@Entity
@Table(name="\"DisplayAdmin\"")
@NamedQuery(name="DisplayAdmin.findAll", query="SELECT d FROM DisplayAdmin d")
public class DisplayAdmin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private Integer id;

	@Column(name="\"プロジェクトid\"")
	private Integer プロジェクトid;

	private String 画面名;

	public DisplayAdmin() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getプロジェクトid() {
		return this.プロジェクトid;
	}

	public void setプロジェクトid(Integer プロジェクトid) {
		this.プロジェクトid = プロジェクトid;
	}

	public String get画面名() {
		return this.画面名;
	}

	public void set画面名(String 画面名) {
		this.画面名 = 画面名;
	}

}