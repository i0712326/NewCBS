package bcel.cardcenter.cbs.varofat.action;

import bcel.cardcenter.cbs.varofat.entity.VisaSettle;
import bcel.cardcenter.cbs.varofat.service.VisaAtmUtility;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;
public class HistoryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// input parameter
	private Date date;
	private String cate;
	private String retr;
	private String card;
	//page information
	private int page;
	private int rows;
	private int total;
	private int records;
	//out put data
	private String result;
	private String message;
	private List<VisaSettle> visas;
	// business logic
	private VisaAtmUtility visaAtmUtility;
	public void setVisaAtmUtility(VisaAtmUtility visaAtmUtility){
		this.visaAtmUtility = visaAtmUtility;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getRetr() {
		return retr;
	}
	public void setRetr(String retr) {
		this.retr = retr;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
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
	public List<VisaSettle> getVisas() {
		return visas;
	}
	public void setVisas(List<VisaSettle> visas) {
		this.visas = visas;
	}
	public String getVisaList() throws Exception{
		try{
			java.sql.Date sDate = new java.sql.Date(date.getTime());
			setVisas(visaAtmUtility.getVisaAtm(sDate, cate, retr, card, page, rows));
			setRecords(visaAtmUtility.recordCount(sDate, cate, retr, card));
			setTotal((records+rows-1)/rows);
			setResult(SUCCESS);
			setMessage("O.K");
			return SUCCESS;
		}
		catch(Exception ex){
			setResult(ERROR);
			setMessage(ex.getLocalizedMessage());
			return ERROR;
		}
		
	}
	
}
