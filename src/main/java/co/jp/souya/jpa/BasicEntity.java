package co.jp.souya.jpa;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

@MappedSuperclass
public class BasicEntity {

	@Id
	@GeneratedValue
	private Long id;
	@Version
	private Long version;
	private Date createdAt;
	private Date updatedAt;

	public Long getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	@PrePersist
	public void onCreate() {
		createdAt = new Date();
		onUpdate();
	}

	@PreUpdate
	public void onUpdate() {
		updatedAt = new Date();
	}
}