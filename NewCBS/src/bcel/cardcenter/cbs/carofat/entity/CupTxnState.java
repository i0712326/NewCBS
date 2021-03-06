package bcel.cardcenter.cbs.carofat.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import bcel.cardcenter.cbs.utility.report.Report;

@Entity
@Table(name = "TMPCUPSTATE")
public class CupTxnState extends Report implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "TXNDATE")
	private Date date;
	@Id
	@Column(name = "TXNTIME")
	private String time;
	@Id
	@Column(name = "CARD")
	private String card;
	@Id
	@Column(name = "TRACE")
	private String trace;
	@Id
	@Column(name = "TXNTYPE")
	private String type;
	@Column(name = "TXNAMT")
	private double amount;
	@Column(name = "TXNFEE")
	private double fee;
	@Column(name = "PROCESSDATE")
	private Date processDate;
	@Column(name = "IMPORTDATE")
	private Date importDate;
	@Column(name = "STATUS")
	private String status="Y";
	@Column(name="TXNBIN")
	private String bin;
	@Column(name="NETAMT")
	private double net;
	@Column(name="REFERNO")
	private String refer;
	@Column(name="TERMINALID")
	private String terminalId;
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public void setBin(String bin){
		this.bin = bin;
	}
	
	public String getBin(){
		return bin;
	}
	
	public void setNet(double net){
		this.net = net;
	}
	
	public double getNet(){
		return net;
	}
	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Override
	public String toString() {
		return "CupTxnState [date=" + date + ", time=" + time + ", card="
				+ card + ", trace=" + trace + ", type=" + type + ", amount="
				+ amount + ", fee=" + fee + ", processDate=" + processDate
				+ ", importDate=" + importDate + ", status=" + status
				+ ", bin=" + bin + ", net=" + net + ", refer=" + refer
				+ ", terminalId=" + terminalId + "]";
	}
	
}
