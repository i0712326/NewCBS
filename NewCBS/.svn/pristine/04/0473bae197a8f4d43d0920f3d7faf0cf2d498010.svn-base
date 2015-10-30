package bcel.cardcenter.cbs.aarofat.reconcile.action;

import java.util.Iterator;
import java.util.List;

import bcel.cardcenter.cbs.aarofat.entity.ErrorTxn;
import bcel.cardcenter.cbs.utility.atm.ErrorTxnUtility;

import com.opensymphony.xwork2.ActionSupport;

public class RecordErrorTxnAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dataXml;
	private ErrorTxnUtility errorTxnUtility;
	private List<ErrorTxn> errorTxns;
	private int record;
	
	public void setErrorTxnUtility(ErrorTxnUtility errorTxnUtility){
		this.errorTxnUtility = errorTxnUtility;
	}
	public String getDataXml() {
		return dataXml;
	}
	public void setDataXml(String dataXml) {
		this.dataXml = dataXml;
	}
	public int getRecord() {
		return record;
	}
	public void setRecord(int record) {
		this.record = record;
	}
	public String execute() throws Exception{
		errorTxns = errorTxnUtility.parseXml(dataXml);
		Iterator<ErrorTxn> iter = errorTxns.iterator();
		record = 0;
		while(iter.hasNext()){
			ErrorTxn errorTxn = iter.next();
			int count = errorTxnUtility.save(errorTxn);
			record+=count;
		}
		return SUCCESS;
	}
}
