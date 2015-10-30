package bcel.cardcenter.cbs.varofat.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="VISA_STATEMENT")
public class VisaState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="TRANXDATE")
	private Date date;
	@Column(name="TRANXTIME")
	private String tranxTime;
	@Column(name="TERMINALNAME")
	private String terminalName;
	@Id
	@Column(name="CARDNUMBER")
	private String cardnumber;
	@Id
	@Column(name="TERMINALID")
	private String terminalId;
	@Id
	@Column(name="TRACENO")
	private String traceNo;
	@Column(name="AMOUNT")
	private String amount;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTranxTime() {
		return tranxTime;
	}
	public void setTranxTime(String tranxTime) {
		this.tranxTime = tranxTime;
	}
	public String getTerminalName() {
		return terminalName;
	}
	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
