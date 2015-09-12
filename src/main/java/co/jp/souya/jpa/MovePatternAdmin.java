package co.jp.souya.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "MovePatternAdmin" database table.
 * 
 */
@Entity
@Table(name="\"MovePatternAdmin\"")
@NamedQuery(name="MovePatternAdmin.findAll", query="SELECT m FROM MovePatternAdmin m")
public class MovePatternAdmin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"id\"")
	private int id;

	@Column(name="\"画面管理id\"")
	private int 画面管理id;

	private String 遷移パターン名;

	private String 備考;

	public MovePatternAdmin() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int get画面管理id() {
		return this.画面管理id;
	}

	public void set画面管理id(int 画面管理id) {
		this.画面管理id = 画面管理id;
	}

	public String get遷移パターン名() {
		return this.遷移パターン名;
	}

	public void set遷移パターン名(String 遷移パターン名) {
		this.遷移パターン名 = 遷移パターン名;
	}

	public String get備考() {
		return this.備考;
	}

	public void set備考(String 備考) {
		this.備考 = 備考;
	}

}