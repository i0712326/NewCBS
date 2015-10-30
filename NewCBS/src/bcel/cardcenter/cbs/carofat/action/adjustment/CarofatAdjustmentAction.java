package bcel.cardcenter.cbs.carofat.action.adjustment;

import java.util.Date;
import java.util.List;

import jxl.common.Logger;

import bcel.cardcenter.cbs.carofat.entity.CupSettle;
import bcel.cardcenter.cbs.carofat.entity.CupState;
import bcel.cardcenter.cbs.carofat.utility.CupTxnUtil;

import com.opensymphony.xwork2.ActionSupport;

public class CarofatAdjustmentAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CarofatAdjustmentAction.class);
	private Date date;
	private String option;
	private String card;
	private String trace;
	
	// business service
	private CupTxnUtil cupTxnUtil;
	
	// output data list
	private List<CupSettle> settles;
	private List<CupState> states;
	
	// page setting
	private int page;
	private int total;
	private int rows;
	private int records;
	
	// adjustment xml input
	private String xmlData;
	
	// result setting
	private String result;
	private String message;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
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

	public CupTxnUtil getCupTxnUtil() {
		return cupTxnUtil;
	}

	public void setCupTxnUtil(CupTxnUtil cupTxnUtil) {
		this.cupTxnUtil = cupTxnUtil;
	}

	public List<CupSettle> getSettles() {
		return settles;
	}

	public void setSettles(List<CupSettle> settles) {
		this.settles = settles;
	}

	public List<CupState> getStates() {
		return states;
	}

	public void setStates(List<CupState> states) {
		this.states = states;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}
	
	// XML data
	
	public String getXmlData() {
		return xmlData;
	}

	public void setXmlData(String xmlData) {
		this.xmlData = xmlData;
	}
	
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	// action part
	
	/* Retrieve data for change */
		
	public String getdatasettles(){
		try{
			long time = date.getTime();
			java.sql.Date sdate = new java.sql.Date(time);
			setSettles(cupTxnUtil.getSettles(option, sdate, card, trace, page-1, rows));
			setRecords(cupTxnUtil.getRows(option, sdate, card, trace));
			setTotal((records+rows-1)/rows);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Excepiton occur while try update data",ex);
			setResult(SUCCESS);
			setMessage("0001");
			return ERROR;
		}
	}
	
	public String getdatastates(){
		try{
			long time = date.getTime();
			java.sql.Date sdate = new java.sql.Date(time);
			setStates(cupTxnUtil.getStates(option, sdate, card, trace, page-1, rows));
			setRecords(cupTxnUtil.getRows(option, sdate, card, trace));
			setTotal((records+rows-1)/rows);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to get state data", ex);
			setResult(ERROR);
			setMessage("0001");
			return ERROR;
		}
	}
	
	// update date from input XML
	
	public String updatedata(){
		try{
			cupTxnUtil.updateCup(option, xmlData);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Excepiton occur while try update data",ex);
			setResult(SUCCESS);
			setMessage("0001");
			return ERROR;
		}
	}
}
