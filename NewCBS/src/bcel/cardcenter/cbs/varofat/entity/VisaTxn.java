package bcel.cardcenter.cbs.varofat.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="VISATXN")
public class VisaTxn implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="CARD")
	private String card;
	@Id
	@Column(name="RRN")
	private String refer;
	@Column(name="TXNTIME")
	private String time;
	@Column(name="TXNDATE")
	private Date   date;
	@Column(name="TERMI")
	private String termId;
	@Column(name="TERMNAME")
	private String termName;
	@Column(name="AMOUNT")
	private double amount;
	@Id
	@Column(name="STAN")
	private String stan;
	@Id
	@Column(name="TXTYPE")
	private String type = "CWD";
	@Column(name="STATUS")
	private String status="Y";
	@Column(name="PROCDATE")
	private Date proDate= new Date(new java.util.Date().getTime());
	@Column(name="IMPORTDATE")
	private Date importDate = new Date(new java.util.Date().getTime());
	@Column(name="PROCSTAT")
	private String procStat="N";
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getRefer() {
		return refer;
	}
	public void setRefer(String refer) {
		this.refer = refer;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStan() {
		return stan;
	}
	public void setStan(String stan) {
		this.stan = stan;
	}
	public void setTermName(String termName){
		this.termName = termName;
	}
	public String getTermName(){
		return termName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getProDate() {
		return proDate;
	}
	public void setProDate(Date proDate) {
		this.proDate = proDate;
	}
	public Date getImportDate() {
		return importDate;
	}
	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}
	public String getProcStat() {
		return procStat;
	}
	public void setProcStat(String procStat) {
		this.procStat = procStat;
	}
	@Override
	public String toString() {
		return "'" + card + ", '" + refer + ", " + time + ", " + date + ", '"
				+ termId + ", " +termName+", "+ amount + ", '" + stan;
	}
}
