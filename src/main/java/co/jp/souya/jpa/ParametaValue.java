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
 * The persistent class for the "ParametaValue" database table.
 *
 */
@Entity
@Table(name="\"ParametaValue\"")
@NamedQueries({
	@NamedQuery(name="ParametaValue.findAll", query="SELECT p FROM ParametaValue p")
,	@NamedQuery(name="ParametaValue.findListById", query="SELECT i FROM ParametaValue i where 入力パターンid=:入力パターンid")
})
public class ParametaValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private Integer id;

	private String アクション;

	private String エレメント型;

	private String エレメント名;

	private String 型;

	private String 項目名;

	private Integer 実行順;

	private String 値;

	@Column(name="\"入力パターンid\"")
	private Integer 入力パターンid;

	private String 備考;

	public ParametaValue() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getアクション() {
		return this.アクション;
	}

	public void setアクション(String アクション) {
		this.アクション = アクション;
	}

	public String getエレメント型() {
		return this.エレメント型;
	}

	public void setエレメント型(String エレメント型) {
		this.エレメント型 = エレメント型;
	}

	public String getエレメント名() {
		return this.エレメント名;
	}

	public void setエレメント名(String エレメント名) {
		this.エレメント名 = エレメント名;
	}

	public String get型() {
		return this.型;
	}

	public void set型(String 型) {
		this.型 = 型;
	}

	public String get項目名() {
		return this.項目名;
	}

	public void set項目名(String 項目名) {
		this.項目名 = 項目名;
	}

	public Integer get実行順() {
		return this.実行順;
	}

	public void set実行順(Integer 実行順) {
		this.実行順 = 実行順;
	}

	public String get値() {
		return this.値;
	}

	public void set値(String 値) {
		this.値 = 値;
	}

	public Integer get入力パターンid() {
		return this.入力パターンid;
	}

	public void set入力パターンid(Integer 入力パターンid) {
		this.入力パターンid = 入力パターンid;
	}

	public String get備考() {
		return this.備考;
	}

	public void set備考(String 備考) {
		this.備考 = 備考;
	}

}