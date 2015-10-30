package bcel.cardcenter.cbs.aarofat.entity;

import java.sql.Date;
import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
			name="GETERRORTXN",
			query="from ErrorTxn exo where exo.date between :start and :end"
	)
})

@Entity
@Table(name="TEST_REPORT")
public class ErrorTxn implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ATMID")
	private String atmId;
	@Id
	@Column(name="TSQ")
	private String tsq;
	@Id
	@Column(name="RDATE")
	private Date date;
	@Column(name="ACCOUNTNUMBER")
	private String accountNumber=null;
	@Column(name="NOTICE")
	private String notice=null;
	@Column(name="CARDNUMBER")
	private String cardNumber=null;
	@Column(name="AMOUNT")
	private double amount=0.0;
	@Column(name="STATUS")
	private String status = "UNPAID";
	@Column(name="RTYPE")
	private String type = null;
	
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
	public String getAtmId(){
		return atmId;
	}
	public void setAtmId(String atmId){
		this.atmId=atmId;
	}
	public String getTsq(){
		return tsq;
	}
	public void setTsq(String tsq){
		this.tsq=tsq;
	}
	public String getAccountNumber(){
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber){
		this.accountNumber=accountNumber;
	}
	public String getNotice(){
		return notice;
	}
	public void setNotice(String notice){
		this.notice=notice;
	}
	public Date getDate(){
		return date;
	}
	public void setDate(Date date){
		this.date=date;
	}
	public String getCardNumber(){
		return cardNumber;
	}
	public void setCardNumber(String cardNumber){
		this.cardNumber=cardNumber;
	}
	public double getAmount(){
		return amount;
	}
	public void setAmount(double amount){
		this.amount=amount;
	}
	
	@Override
	public String toString() {
		return "Report [atmId=" + atmId + ", tsq=" + tsq + ", accountNumber="
				+ accountNumber + ", notice=" + notice + ", date=" + date
				+ ", cardNumber=" + cardNumber + ", amount=" + amount
				+ ", status=" + status + ", type=" + type + "]";
	}
	
}
