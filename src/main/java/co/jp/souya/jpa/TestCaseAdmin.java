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
 * The persistent class for the "TestCaseAdmin" database table.
 *
 */
@Entity
@Table(name="\"TestCaseAdmin\"")
@NamedQuery(name="TestCaseAdmin.findAll", query="SELECT t FROM TestCaseAdmin t")
public class TestCaseAdmin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private int id;

	@Column(name="\"gitリポジトリパス\"")
	private String gitリポジトリパス;

	@Column(name="\"JenkinsジョブURL\"")
	private String jenkinsジョブURL;

	private String インプットdbダンプ;

	private String テストコード;

	@Column(name="\"画面管理id\"")
	private int 画面管理id;

	private String 更新テーブル１;

	private String 更新テーブル２;

	private String 更新テーブル３;

	@Column(name="\"遷移パターン管理id\"")
	private int 遷移パターン管理id;

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

	public String getJenkinsジョブURL() {
		return this.jenkinsジョブURL;
	}

	public void setJenkinsジョブURL(String jenkinsジョブURL) {
		this.jenkinsジョブURL = jenkinsジョブURL;
	}

	public String getインプットdbダンプ() {
		return this.インプットdbダンプ;
	}

	public void setインプットdbダンプ(String インプットdbダンプ) {
		this.インプットdbダンプ = インプットdbダンプ;
	}

	public String getテストコード() {
		return this.テストコード;
	}

	public void setテストコード(String テストコード) {
		this.テストコード = テストコード;
	}

	public int get画面管理id() {
		return this.画面管理id;
	}

	public void set画面管理id(int 画面管理id) {
		this.画面管理id = 画面管理id;
	}

	public String get更新テーブル１() {
		return this.更新テーブル１;
	}

	public void set更新テーブル１(String 更新テーブル１) {
		this.更新テーブル１ = 更新テーブル１;
	}

	public String get更新テーブル２() {
		return this.更新テーブル２;
	}

	public void set更新テーブル２(String 更新テーブル２) {
		this.更新テーブル２ = 更新テーブル２;
	}

	public String get更新テーブル３() {
		return this.更新テーブル３;
	}

	public void set更新テーブル３(String 更新テーブル３) {
		this.更新テーブル３ = 更新テーブル３;
	}

	public int get遷移パターン管理id() {
		return this.遷移パターン管理id;
	}

	public void set遷移パターン管理id(int 遷移パターン管理id) {
		this.遷移パターン管理id = 遷移パターン管理id;
	}

}