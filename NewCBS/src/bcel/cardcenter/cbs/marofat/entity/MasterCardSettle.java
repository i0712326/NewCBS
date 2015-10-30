package bcel.cardcenter.cbs.marofat.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="MASTERCARDENTITY")
public class MasterCardSettle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="TXNTYPE")
	private String type;
	@Id
	@Column(name="REFERNO")
	private String refer;
	@Column(name="INSTINO")
	private String insti;
	@Column(name="TXNDATE")
	private Date   txnDate;
	@Column(name="TXNTIME")
	private String txnTime;
	@Id
	@Column(name="CARDNO")
	private String card;
	@Column(name="PROCES")
	private String process;
	@Id
	@Column(name="TERMID")
	private String terminalId;
	@Column(name="RESCODE")
	private String res;
	@Column(name="CARDTYPE")
	private String cardType;
	@Column(name="EXCEPCODE")
	private String excepCode;
	@Column(name="REACODE")
	private String rea;
	@Column(name="APPRCODE")
	private String apprcode;
	@Column(name="TXNCURR")
	private String txnCurr;
	@Column(name="TXNAMT")
	private double txnAmt;
	@Column(name="TXNCAT")
	private String txnGroup;
	@Column(name="TXNFEE")
	private double txnFee;
	@Column(name="TXNFEC")
	private String txnFeeCat;
	@Column(name="SETCURR")
	private String settleCurr;
	@Column(name="SETAMT")
	private double settleAmt;
	@Column(name="SETCAT")
	private String settleGroup;
	@Column(name="SETFEE")
	private double fee;
	@Column(name="FEECURR")
	private String feeCurr;
	@Column(name="FEECAT")
	private String feeGroup;
	@Column(name="ERRAMT")
	private double errAmt;
	@Column(name="ERRCAT")
	private String errCat;
	@Column(name="ERRSET")
	private double errSet;
	@Column(name="ERRSEC")
	private String errSeC;
	@Column(name="STATUS")
	private String status = "Y";
	@Column(name="SETTLEDATE")
	private Date setDate;
	@Column(name="PROCESSDATE")
	private Date proDate;
	@Column(name="PROSTATUS")
	private String proStatus = "N";
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}
	public String getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getApprcode() {
		return apprcode;
	}
	public void setApprcode(String apprcode) {
		this.apprcode = apprcode;
	}
	public String getTxnCurr() {
		return txnCurr;
	}
	public void setTxnCurr(String txnCurr) {
		this.txnCurr = txnCurr;
	}
	public double getTxnAmt() {
		return txnAmt;
	}
	public void setTxnAmt(double txnAmt) {
		this.txnAmt = txnAmt;
	}
	public String getTxnGroup() {
		return txnGroup;
	}
	public void setTxnGroup(String txnGroup) {
		this.txnGroup = txnGroup;
	}
	public double getTxnFee() {
		return txnFee;
	}
	public void setTxnFee(double txnFee) {
		this.txnFee = txnFee;
	}
	public String getSettleCurr() {
		return settleCurr;
	}
	public void setSettleCurr(String settleCurr) {
		this.settleCurr = settleCurr;
	}
	public double getSettleAmt() {
		return settleAmt;
	}
	public void setSettleAmt(double settleAmt) {
		this.settleAmt = settleAmt;
	}
	public String getSettleGroup() {
		return settleGroup;
	}
	public void setSettleGroup(String settleGroup) {
		this.settleGroup = settleGroup;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public String getFeeCurr() {
		return feeCurr;
	}
	public void setFeeCurr(String feeCurr) {
		this.feeCurr = feeCurr;
	}
	public String getFeeGroup() {
		return feeGroup;
	}
	public void setFeeGroup(String feeGroup) {
		this.feeGroup = feeGroup;
	}
	public String getRefer() {
		return refer;
	}
	public void setRefer(String refer) {
		this.refer = refer;
	}
	public String getInsti() {
		return insti;
	}
	public void setInsti(String insti) {
		this.insti = insti;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getExcepCode() {
		return excepCode;
	}
	public void setExcepCode(String excepCode) {
		this.excepCode = excepCode;
	}
	public String getTxnFeeCat() {
		return txnFeeCat;
	}
	public void setTxnFeeCat(String txnFeeCat) {
		this.txnFeeCat = txnFeeCat;
	}
	public double getErrAmt() {
		return errAmt;
	}
	public void setErrAmt(double errAmt) {
		this.errAmt = errAmt;
	}
	public String getErrCat() {
		return errCat;
	}
	public void setErrCat(String errCat) {
		this.errCat = errCat;
	}
	public double getErrSet() {
		return errSet;
	}
	public void setErrSet(double errSet) {
		this.errSet = errSet;
	}
	public String getErrSeC() {
		return errSeC;
	}
	public void setErrSeC(String errSeC) {
		this.errSeC = errSeC;
	}
	public String getRea() {
		return rea;
	}
	public void setRea(String rea) {
		this.rea = rea;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getSetDate() {
		return setDate;
	}
	public void setSetDate(Date setDate) {
		this.setDate = setDate;
	}
	public Date getProDate() {
		return proDate;
	}
	public void setProDate(Date proDate) {
		this.proDate = proDate;
	}
	public String getProStatus() {
		return proStatus;
	}
	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}
	@Override
	public String toString() {
		return "MasterCardSettle [type=" + type + ", refer=" + refer
				+ ", insti=" + insti + ", txnDate=" + txnDate + ", txnTime="
				+ txnTime + ", card=" + card + ", process=" + process
				+ ", terminalId=" + terminalId + ", res=" + res + ", cardType="
				+ cardType + ", excepCode=" + excepCode + ", rea=" + rea
				+ ", apprcode=" + apprcode + ", txnCurr=" + txnCurr
				+ ", txnAmt=" + txnAmt + ", txnGroup=" + txnGroup + ", txnFee="
				+ txnFee + ", txnFeeCat=" + txnFeeCat + ", settleCurr="
				+ settleCurr + ", settleAmt=" + settleAmt + ", settleGroup="
				+ settleGroup + ", fee=" + fee + ", feeCurr=" + feeCurr
				+ ", feeGroup=" + feeGroup + ", errAmt=" + errAmt + ", errCat="
				+ errCat + ", errSet=" + errSet + ", errSeC=" + errSeC
				+ ", status=" + status + ", setDate=" + setDate + ", proDate="
				+ proDate + ", proCode=" + proStatus + "]";
	}
}
