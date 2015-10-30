package bcel.cardcenter.cbs.aarofat.reconcile.action;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


//import org.apache.struts2.interceptor.SessionAware;

import bcel.cardcenter.cbs.aarofat.entity.ErrorTxn;
import bcel.cardcenter.cbs.utility.atm.CwdProcessUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ReconcileFileAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// input parameter
	private String atmId;
	private Date start;
	private Date end;
	private List<File> files;
	
	// output parameter
	private int smRecord;
	private int ejRecord;
	private double smAmount;
	private double ejAmount;
	private List<ErrorTxn> smReports;
	private List<ErrorTxn> ejReports;
	
	// business logic
	private CwdProcessUtil cwdProcessUtil;
	
	public String getAtmId() {
		return atmId;
	}

	public void setAtmId(String atmId) {
		this.atmId = atmId;
	}
	
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}
	
	// inject business logic
	public void setCwdProcessUtil(CwdProcessUtil cwdProcessUtil){
		this.cwdProcessUtil=cwdProcessUtil;
	}
	
	// setter and getter for output
	
	public double getSmAmount() {
		return smAmount;
	}

	public int getSmRecord() {
		return smRecord;
	}

	public void setSmRecord(int smRecord) {
		this.smRecord = smRecord;
	}

	public int getEjRecord() {
		return ejRecord;
	}

	public void setEjRecord(int ejRecord) {
		this.ejRecord = ejRecord;
	}

	public void setSmAmount(double smAmount) {
		this.smAmount = smAmount;
	}

	public double getEjAmount() {
		return ejAmount;
	}

	public void setEjAmount(double ejAmount) {
		this.ejAmount = ejAmount;
	}

	public List<ErrorTxn> getSmReports() {
		return smReports;
	}

	public void setSmReports(List<ErrorTxn> smReports) {
		this.smReports = smReports;
	}

	public List<ErrorTxn> getEjReports() {
		return ejReports;
	}

	public void setEjReports(List<ErrorTxn> ejReports) {
		this.ejReports = ejReports;
	}

	public String execute() throws Exception {
		// initialize parameter
		cwdProcessUtil.setAtmId(atmId);
		cwdProcessUtil.setBegin(new java.sql.Date(start.getTime()));
		cwdProcessUtil.setEnd(new java.sql.Date(end.getTime()));
		cwdProcessUtil.setFiles(files);
		
		// process data
		cwdProcessUtil.cwdProcessUtil();
		
		// retrieve process result
		setEjReports(cwdProcessUtil.getAccountDebit());
		setSmReports(cwdProcessUtil.getNoGlDebit());
		
		// set amount for each report
		setEjAmount(getAmount(getEjReports()));
		setSmAmount(getAmount(getSmReports()));
		
		// set record number
		setEjRecord(getEjReports().size());
		setSmRecord(getSmReports().size());
		
		return SUCCESS;
	}
	
	private double getAmount(List<ErrorTxn> errorTxns){
		double sum = 0;
		Iterator<ErrorTxn> iter = errorTxns.iterator();
		while(iter.hasNext()){
			ErrorTxn errorTxn = iter.next();
			sum+=errorTxn.getAmount();
		}
		return sum;
	}
}
