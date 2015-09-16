package co.jp.souya.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name="\"id\"")
	private int id;

	private String db;

	private String html;

	private String job状況;

	@Column(name="\"No\"")
	private int no;

	@Column(name="\"テストケース管理id\"")
	private int テストケース管理id;

	private String 遷移結果;

	private String 前回との結果一致状況;

	private String 入力パターン名;

	private String 判定結果;

	private int 非初回;

	private String 備考;

	public InputPattern() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDb() {
		return this.db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getJob状況() {
		return this.job状況;
	}

	public void setJob状況(String job状況) {
		this.job状況 = job状況;
	}

	public int getNo() {
		return this.no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getテストケース管理id() {
		return this.テストケース管理id;
	}

	public void setテストケース管理id(int テストケース管理id) {
		this.テストケース管理id = テストケース管理id;
	}

	public String get遷移結果() {
		return this.遷移結果;
	}

	public void set遷移結果(String 遷移結果) {
		this.遷移結果 = 遷移結果;
	}

	public String get前回との結果一致状況() {
		return this.前回との結果一致状況;
	}

	public void set前回との結果一致状況(String 前回との結果一致状況) {
		this.前回との結果一致状況 = 前回との結果一致状況;
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

	public int get非初回() {
		return this.非初回;
	}

	public void set非初回(int 非初回) {
		this.非初回 = 非初回;
	}

	public String get備考() {
		return this.備考;
	}

	public void set備考(String 備考) {
		this.備考 = 備考;
	}

}