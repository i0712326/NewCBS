package bcel.cardcenter.cbs.carofat.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import bcel.cardcenter.cbs.utility.report.Report;

@Entity
@Table(name="CUPTXN_SETTLE")
public class CupSettle extends Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="TXNDATE")
	private Date date;
	@Id
	@Column(name="CARD")
	private String card;
	@Column(name="AMOUNT")
	private double amount;
	@Column(name="FEE")
	private double fee;
	@Column(name="NET")
	private double net;
	@Column(name="APPRCODE")
	private String apprCode;
	@Id
	@Column(name="TRACE")
	private String trace;
	@Column(name="TERMINALID")
	private String terminalId;
	@Column(name="MERCHANT")
	private String merchant;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="COUNTRY")
	private String country;
	@Column(name="TXNTYPE")
	private String type="CWD";
	@Column(name="STATUS")
	private String status="N";
	@Column(name="PROCESSDATE")
	private Date processDate;
	@Column(name="IMPORTDATE")
	private Date importDate;
	@Id
	@Column(name="REFERNO")
	private String refer;
	@Column(name="TXNTIME")
	private String time;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
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
	public double getNet() {
		return net;
	}
	public void setNet(double net) {
		this.net = net;
	}
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}
	public String getTrace() {
		return trace;
	}
	public void setTrace(String trace) {
		this.trace = trace;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	@Override
	public String toString() {
		return "CupTxnSettle [date=" + date + ", card=" + card + ", amount="
				+ amount + ", fee=" + fee + ", net=" + net + ", apprCode="
				+ apprCode + ", trace=" + trace + ", terminalId=" + terminalId
				+ ", merchant=" + merchant + ", address=" + address
				+ ", country=" + country + ", txnType=" + type + ", status="
				+ status + ", processDate=" + processDate + ", importDate="
				+ importDate + ", refer=" + refer + ", time=" + time + "]";
	}
}
