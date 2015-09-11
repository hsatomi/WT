package co.jp.souya.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "VariableInitialTemplate" database table.
 * 
 */
@Entity
@Table(name="\"VariableInitialTemplate\"")
@NamedQuery(name="VariableInitialTemplate.findAll", query="SELECT v FROM VariableInitialTemplate v")
public class VariableInitialTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"id\"")
	private int id;

	private String パターン名;

	private String 型;

	private String 値1;

	private String 値10;

	private String 値2;

	private String 値3;

	private String 値4;

	private String 値5;

	private String 値6;

	private String 値7;

	private String 値8;

	private String 値9;

	private String 備考;

	public VariableInitialTemplate() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getパターン名() {
		return this.パターン名;
	}

	public void setパターン名(String パターン名) {
		this.パターン名 = パターン名;
	}

	public String get型() {
		return this.型;
	}

	public void set型(String 型) {
		this.型 = 型;
	}

	public String get値1() {
		return this.値1;
	}

	public void set値1(String 値1) {
		this.値1 = 値1;
	}

	public String get値10() {
		return this.値10;
	}

	public void set値10(String 値10) {
		this.値10 = 値10;
	}

	public String get値2() {
		return this.値2;
	}

	public void set値2(String 値2) {
		this.値2 = 値2;
	}

	public String get値3() {
		return this.値3;
	}

	public void set値3(String 値3) {
		this.値3 = 値3;
	}

	public String get値4() {
		return this.値4;
	}

	public void set値4(String 値4) {
		this.値4 = 値4;
	}

	public String get値5() {
		return this.値5;
	}

	public void set値5(String 値5) {
		this.値5 = 値5;
	}

	public String get値6() {
		return this.値6;
	}

	public void set値6(String 値6) {
		this.値6 = 値6;
	}

	public String get値7() {
		return this.値7;
	}

	public void set値7(String 値7) {
		this.値7 = 値7;
	}

	public String get値8() {
		return this.値8;
	}

	public void set値8(String 値8) {
		this.値8 = 値8;
	}

	public String get値9() {
		return this.値9;
	}

	public void set値9(String 値9) {
		this.値9 = 値9;
	}

	public String get備考() {
		return this.備考;
	}

	public void set備考(String 備考) {
		this.備考 = 備考;
	}

}