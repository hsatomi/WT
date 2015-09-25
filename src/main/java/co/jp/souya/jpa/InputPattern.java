package co.jp.souya.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the "InputPattern" database table.
 *
 */
@Entity
@Table(name="\"InputPattern\"")
@NamedQueries({
	@NamedQuery(name="InputPattern.findAll", query="SELECT i FROM InputPattern i")
,	@NamedQuery(name="InputPattern.findListById", query="SELECT i FROM InputPattern i where テストケース管理id=:テストケース管理id")
})
public class InputPattern implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private Integer id;

	private String db;

	private String db差異;

	private String db正解;

	private String html;

	private String html差異;

	private String html正解;

	private String job状況;

	@Column(name="\"No\"")
	private Integer no;

	@Column(name="\"テストケース管理id\"")
	private Integer テストケース管理id;

	private Integer 実行回数;

	private String 遷移結果;

	private String 入力パターン名;

	private String 判定結果;

	private String 備考;

	public InputPattern() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDb() {
		return this.db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getDb差異() {
		return this.db差異;
	}

	public void setDb差異(String db差異) {
		this.db差異 = db差異;
	}

	public String getDb正解() {
		return this.db正解;
	}

	public void setDb正解(String db正解) {
		this.db正解 = db正解;
	}

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getHtml差異() {
		return this.html差異;
	}

	public void setHtml差異(String html差異) {
		this.html差異 = html差異;
	}

	public String getHtml正解() {
		return this.html正解;
	}

	public void setHtml正解(String html正解) {
		this.html正解 = html正解;
	}

	public String getJob状況() {
		return this.job状況;
	}

	public void setJob状況(String job状況) {
		this.job状況 = job状況;
	}

	public Integer getNo() {
		return this.no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Integer getテストケース管理id() {
		return this.テストケース管理id;
	}

	public void setテストケース管理id(Integer テストケース管理id) {
		this.テストケース管理id = テストケース管理id;
	}

	public Integer get実行回数() {
		return this.実行回数;
	}

	public void set実行回数(Integer 実行回数) {
		this.実行回数 = 実行回数;
	}

	public String get遷移結果() {
		return this.遷移結果;
	}

	public void set遷移結果(String 遷移結果) {
		this.遷移結果 = 遷移結果;
	}

	public String get入力パターン名() {
		return this.入力パターン名;
	}

	public void set入力パターン名(String 入力パターン名) {
		this.入力パターン名 = 入力パターン名;
	}

	public String get判定結果() {
		return this.判定結果;
	}

	public void set判定結果(String 判定結果) {
		this.判定結果 = 判定結果;
	}

	public String get備考() {
		return this.備考;
	}

	public void set備考(String 備考) {
		this.備考 = 備考;
	}

}