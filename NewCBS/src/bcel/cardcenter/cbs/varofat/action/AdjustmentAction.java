package bcel.cardcenter.cbs.varofat.action;

import java.util.Date;
import java.util.List;

import bcel.cardcenter.cbs.varofat.entity.VisaSettle;
import bcel.cardcenter.cbs.varofat.service.VisaAtmUtility;

import com.opensymphony.xwork2.ActionSupport;

public class AdjustmentAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Business Logic Injection
	private VisaAtmUtility visaAtmUtility;
	//Parameter
	private Date date;
	private String type;
	private String ref;
	private String card;
	private String xmlData;
	
	// parameter for page
	private int page;
	private int rows;
	private int total;
	// output result
	private String result;
	private String message;
	// data list
	private int record;
	private List<VisaSettle> visaAtms;
	
	public void setVisaAtmUtility(VisaAtmUtility visaAtmUtility){
		this.visaAtmUtility = visaAtmUtility;
	}
	public void setDate(Date date){
		this.date = date;
	}
	public Date getDate(){
		return date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public void setVisaAtms(List<VisaSettle> visaAtms){
		this.visaAtms = visaAtms;
	}
	public List<VisaSettle> getVisaAtms(){
		return this.visaAtms;
	}
	public void setRecord(int record){
		this.record = record;
	}
	public int getRecord(){
		return this.record;
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
	
	public String getVisaAtmList() throws Exception{
		try{
			java.sql.Date sDate = new java.sql.Date(date.getTime());
			setVisaAtms(visaAtmUtility.getVisaAtm(sDate, type, ref, card, page, rows));
			setRecord(visaAtmUtility.recordCount(sDate, type, ref, card));
			setTotal((record+rows-1)/rows);
			setResult(SUCCESS);
			setMessage("O.K");
			return SUCCESS;
		}
		catch(Exception ex){
			setResult(ERROR);
			setMessage(ex.getMessage());
			return ERROR;
		}
		
	}
	
	public String getXmlData() {
		return xmlData;
	}
	public void setXmlData(String xmlData) {
		this.xmlData = xmlData;
	}
	public String updateStatus() throws Exception{
		try{
			visaAtmUtility.updateVisaAtm(xmlData);
			return SUCCESS;
		}
		catch(Exception ex){
			
			return ERROR;
		}
	}

}
