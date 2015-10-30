package bcel.cardcenter.cbs.aarofat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REJECTCODE")
public class Error {
	@Id
	@GeneratedValue
	@Column(name="CODE")
	private String code;
	@Column(name="DESCRIPTION")
	private String description;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Error [code=" + code + ", description=" + description + "]";
	}
}
