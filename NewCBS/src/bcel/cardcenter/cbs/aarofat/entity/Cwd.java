package bcel.cardcenter.cbs.aarofat.entity;
import java.io.Serializable;
import java.sql.*;

public class Cwd implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Attribute
	
	private String transId = null;
	private String sessionId = null;
	private Date date        = null;
	private String atmId     = null;
	private String cardNumber=null;
	private String account=null;
	private double amount=0.0;
	private String tsq=null;
	private int glWithdraw = 0;
	private int state=-1;
	private int cardEject=0;
	private int acceptTime=0;
	private int t1=0;
	private int t2=0;
	private int t3=0;
	private int t4=0;
	private String status="0000";
	private String rejectCode="0000";
	
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	
	public int getGlWithdraw() {
		return glWithdraw;
	}
	public void setGlWithdraw(int glWithdraw) {
		this.glWithdraw = glWithdraw;
	}
	public int getCardEject() {
		return cardEject;
	}
	public void setCardEject(int cardEject) {
		this.cardEject = cardEject;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAtmId() {
		return atmId;
	}
	public void setAtmId(String atmId) {
		this.atmId = atmId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTsq() {
		return tsq;
	}
	public void setTsq(String tsq) {
		this.tsq = tsq;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(int acceptTime) {
		this.acceptTime = acceptTime;
	}
	public int getT1() {
		return t1;
	}
	public void setT1(int t1) {
		this.t1 = t1;
	}
	public int getT2() {
		return t2;
	}
	public void setT2(int t2) {
		this.t2 = t2;
	}
	public int getT3() {
		return t3;
	}
	public void setT3(int t3) {
		this.t3 = t3;
	}
	public int getT4() {
		return t4;
	}
	public void setT4(int t4) {
		this.t4 = t4;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRejectCode() {
		return rejectCode;
	}
	public void setRejectCode(String rejectCode) {
		this.rejectCode = rejectCode;
	}
	@Override
	public String toString() {
		return "Cwd [transId=" + transId + ", sessionId=" + sessionId
				+ ", date=" + date + ", atmId=" + atmId + ", cardNumber="
				+ cardNumber + ", account=" + account + ", amount=" + amount
				+ ", tsq=" + tsq + ", glWithdraw=" + glWithdraw + ", state="
				+ state + ", cardEject=" + cardEject + ", acceptTime="
				+ acceptTime + ", t1=" + t1 + ", t2=" + t2 + ", t3=" + t3
				+ ", t4=" + t4 + ", status=" + status + ", rejectCode="
				+ rejectCode + "]";
	}
	
}
