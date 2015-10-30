package bcel.cardcenter.cbs.carofat.action.history;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import bcel.cardcenter.cbs.carofat.entity.CupSettle;
import bcel.cardcenter.cbs.carofat.entity.CupState;
import bcel.cardcenter.cbs.carofat.utility.CupTxnUtil;

import com.opensymphony.xwork2.ActionSupport;

public class CarofatHistoryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CarofatHistoryAction.class);
	private Date date;
	private String card;
	private String trace;
	
	private String result;
	private String message;
	
	private int page;
	private int rows;
	private int total;
	private int records;
	
	private CupTxnUtil cupTxnUtil;
	
	private List<CupSettle> CupSettles;
	private List<CupState> CupStates;
	
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
	
	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}
	
	// action control

	public List<CupSettle> getCupSettles() {
		return CupSettles;
	}

	public void setCupSettles(List<CupSettle> cupSettles) {
		CupSettles = cupSettles;
	}

	public List<CupState> getCupStates() {
		return CupStates;
	}

	public void setCupStates(List<CupState> cupStates) {
		CupStates = cupStates;
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

	public void setCupTxnUtil(CupTxnUtil cupTxnUtil) {
		this.cupTxnUtil = cupTxnUtil;
	}
	
	// action for query
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public String getstates(){
		String option = "state";
		try{
			long time = date.getTime();
			java.sql.Date sdate = new java.sql.Date(time);
			this.setCupStates(cupTxnUtil.getStates(option, sdate, card, trace, page-1, rows));
			this.setRecords(cupTxnUtil.getRows(option, sdate, card, trace));
			this.setTotal((records+ rows-1)/rows);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Excepiton occur while try to get datat",ex);
			setResult(ERROR);
			setMessage("0001");
			return ERROR;
		}
	}
	
	public String getsettles(){
		String option = "settle";
		try{
			long time = date.getTime();
			java.sql.Date sdate = new java.sql.Date(time);
			this.setCupSettles(cupTxnUtil.getSettles(option, sdate, card, trace, page-1, rows));
			this.setRecords(cupTxnUtil.getRows(option, sdate, card, trace));
			this.setTotal((records+rows-1)/rows);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to get data",ex);
			setResult(ERROR);
			setMessage("0002");
			return ERROR;
		}
	}
}
