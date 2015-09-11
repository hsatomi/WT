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

}