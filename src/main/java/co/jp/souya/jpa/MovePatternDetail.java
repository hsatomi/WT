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
 * The persistent class for the "MovePatternDetail" database table.
 *
 */
@Entity
@Table(name="\"MovePatternDetail\"")
@NamedQueries({
	@NamedQuery(name="MovePatternDetail.findAll", query="SELECT m FROM MovePatternDetail m")
,	@NamedQuery(name="MovePatternDetail.findListById", query="SELECT m FROM MovePatternDetail m where 遷移パターン管理id=:遷移パターン管理id")
})
public class MovePatternDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private int id;

	private String url;

	private String 画面タイトル;

	@Column(name="\"遷移パターン管理id\"")
	private int 遷移パターン管理id;

	private int 遷移順;

	@Column(name="\"入力パターンid\"")
	private int 入力パターンid;

	public MovePatternDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String get画面タイトル() {
		return this.画面タイトル;
	}

	public void set画面タイトル(String 画面タイトル) {
		this.画面タイトル = 画面タイトル;
	}

	public int get遷移パターン管理id() {
		return this.遷移パターン管理id;
	}

	public void set遷移パターン管理id(int 遷移パターン管理id) {
		this.遷移パターン管理id = 遷移パターン管理id;
	}

	public int get遷移順() {
		return this.遷移順;
	}

	public void set遷移順(int 遷移順) {
		this.遷移順 = 遷移順;
	}

	public int get入力パターンid() {
		return this.入力パターンid;
	}

	public void set入力パターンid(int 入力パターンid) {
		this.入力パターンid = 入力パターンid;
	}

}