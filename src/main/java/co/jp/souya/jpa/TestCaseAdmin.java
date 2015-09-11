package co.jp.souya.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "TestCaseAdmin" database table.
 * 
 */
@Entity
@Table(name="\"TestCaseAdmin\"")
@NamedQuery(name="TestCaseAdmin.findAll", query="SELECT t FROM TestCaseAdmin t")
public class TestCaseAdmin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"id\"")
	private int id;

	@Column(name="\"gitリポジトリパス\"")
	private String gitリポジトリパス;

	@Column(name="\"画面管理id\"")
	private int 画面管理id;

	@Column(name="\"遷移パターンid\"")
	private int 遷移パターンid;

	public TestCaseAdmin() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGitリポジトリパス() {
		return this.gitリポジトリパス;
	}

	public void setGitリポジトリパス(String gitリポジトリパス) {
		this.gitリポジトリパス = gitリポジトリパス;
	}

	public int get画面管理id() {
		return this.画面管理id;
	}

	public void set画面管理id(int 画面管理id) {
		this.画面管理id = 画面管理id;
	}

	public int get遷移パターンid() {
		return this.遷移パターンid;
	}

	public void set遷移パターンid(int 遷移パターンid) {
		this.遷移パターンid = 遷移パターンid;
	}

}