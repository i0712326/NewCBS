package bcel.cardcenter.cbs.utility.report;
import java.io.Serializable;
import java.sql.Date;
public class Report implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String merchantId   = null;
	private String merchantName = null;
	private String cardNumber   = null;
	private String type         = null;
	private double amount       = 0.0;
	private String account      = null;
	private String code         = null;
	private String tnxCode		= null;
	private String currCode     = "840";
	private Date date           = null;
	private String status       = "Y";
	private Date importDate     = null;
	private double visa         = 0.0;
	private double master       = 0.0;
	private double jcb          = 0.0;
	private String proStatus    = null;
	private int visaTnx         = 0;
	private int masterTnx       = 0;
	private int jcbTnx          = 0;
	private int tnx             = 0;
	private String branchId     = null;
	private String branchName   = null;
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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
	public int getTnx() {
		return tnx;
	}
	public void setTnx(int tnx) {
		this.tnx = tnx;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getTnxCode() {
		return tnxCode;
	}
	public void setTnxCode(String tnxCode) {
		this.tnxCode = tnxCode;
	}
	public String getCurrCode() {
		return currCode;
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getImportDate() {
		return importDate;
	}
	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}
	
	public double getVisa() {
		return visa;
	}
	public void setVisa(double visa) {
		this.visa = visa;
	}
	public double getMaster() {
		return master;
	}
	public void setMaster(double master) {
		this.master = master;
	}
	public double getJcb() {
		return jcb;
	}
	public void setJcb(double jcb) {
		this.jcb = jcb;
	}
	public String getProStatus() {
		return proStatus;
	}
	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}
	
	public int getVisaTnx() {
		return visaTnx;
	}
	public void setVisaTnx(int visaTnx) {
		this.visaTnx = visaTnx;
	}
	public int getMasterTnx() {
		return masterTnx;
	}
	public void setMasterTnx(int masterTnx) {
		this.masterTnx = masterTnx;
	}
	public int getJcbTnx() {
		return jcbTnx;
	}
	public void setJcbTnx(int jcbTnx) {
		this.jcbTnx = jcbTnx;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Override
	public String toString() {
		return "Report [merchantId=" + merchantId + ", merchantName="
				+ merchantName + ", cardNumber=" + cardNumber + ", type="
				+ type + ", amount=" + amount + ", account=" + account
				+ ", code=" + code + ", tnxCode=" + tnxCode + ", currCode="
				+ currCode + ", date=" + date + ", status=" + status
				+ ", importDate=" + importDate + ", visa=" + visa + ", master="
				+ master + ", jcb=" + jcb + ", proStatus=" + proStatus + "]";
	}
}
