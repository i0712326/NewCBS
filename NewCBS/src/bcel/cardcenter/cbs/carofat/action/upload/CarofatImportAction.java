package bcel.cardcenter.cbs.carofat.action.upload;


import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import bcel.cardcenter.cbs.carofat.entity.CupSettle;
import bcel.cardcenter.cbs.carofat.entity.CupState;
import bcel.cardcenter.cbs.carofat.entity.CupTxnSettle;
import bcel.cardcenter.cbs.carofat.entity.CupTxnState;
import bcel.cardcenter.cbs.carofat.utility.CupTxnUtil;
import bcel.cardcenter.cbs.utility.report.CupTxnReportExportion;
import bcel.cardcenter.cbs.utility.report.Parameter;
import bcel.cardcenter.cbs.utility.report.Report;

import com.opensymphony.xwork2.ActionSupport;
public class CarofatImportAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CarofatImportAction.class);
	private int record;
	private Date date;
	private String option;
	// result
	private String result;
	private String message;
	
	// View set parameter
	private int rows;
	private int page;
	private int total;
	private int records;
	// Business Logic
	private CupTxnUtil cupTxnUtil;
	// output
	private List<CupTxnSettle> settles;
	private List<CupTxnState> states;
	
	private List<CupSettle> cupSettles;
	private List<CupState> cupStates;
	
	// process result
	public void setResult(String result){
		this.result = result;
	}
	public String getResult(){
		return result;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return message;
	}
	// page information
	public int getRecord() {
		return record;
	}
	public void setRecord(int record) {
		this.record = record;
	}
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
	// View Initial Parameter
	public int getRows(){
		return rows;
	}
	public void setRows(int rows){
		this.rows = rows;
	}
	public int getPage(){
		return page;
	}
	public void setPage(int page){
		this.page = page;
	}
	public int getRecords(){
		return records;
	}
	public void setRecords(int records){
		this.records = records;
	}
	public int getTotal(){
		return total;
	}
	public void setTotal(int total){
		this.total = total;
	}
	// Business Logic Injection
	public void setCupTxnUtil(CupTxnUtil cupTxnUtil){
		this.cupTxnUtil = cupTxnUtil;
	}
	// Out put retrieve
	public List<CupTxnSettle> getSettles() {
		return settles;
	}
	public void setSettles(List<CupTxnSettle> settles) {
		this.settles = settles;
	}
	public List<CupTxnState> getStates() {
		return states;
	}
	public void setStates(List<CupTxnState> states) {
		this.states = states;
	}
	public List<CupSettle> getCupSettles() {
		return cupSettles;
	}
	public void setCupSettles(List<CupSettle> cupSettles) {
		this.cupSettles = cupSettles;
	}
	public List<CupState> getCupStates() {
		return cupStates;
	}
	public void setCupStates(List<CupState> cupStates) {
		this.cupStates = cupStates;
	}
	// action interface
	public String uploadFile(){
		try{
			long time = date.getTime();
			java.sql.Date idate = new java.sql.Date(time);
			cupTxnUtil.upload(record,option, idate);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to upload cup file", ex);
			setResult(ERROR);
			setMessage("0001");
			return ERROR;
		}
	}
	
	public String confirmUpload(){
		try{
			cupTxnUtil.confirm(option);
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to confirm upload data",ex);
			return ERROR;
		}
	}
	
	public String getCupTxnSettles(){
		try{
			long time = date.getTime();
			java.sql.Date idate = new java.sql.Date(time);
			this.setSettles(cupTxnUtil.getTmpSettles(option, idate, page-1, rows));
			setRecords(cupTxnUtil.getSize(option));
			setTotal((records + rows - 1)/rows);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to get data from Settlement", ex);
			setResult(ERROR);
			setMessage("0002");
			return ERROR;
		}
	}
	
	public String cancelCupTxn(){
		try{
			long time = date.getTime();
			java.sql.Date sdate = new java.sql.Date(time);
			boolean ret = cupTxnUtil.deleteAll(option, sdate);
			if(ret){
				setResult(SUCCESS);
				setMessage("0000");
				return SUCCESS;
			}
			setResult(ERROR);
			setMessage("0003");
			return ERROR;
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to cancel persistance",ex);
			setResult(ERROR);
			setMessage("0004");
			return ERROR;
		}
	}
	
	public String getCupTxnStates(){
		try{
			long time = date.getTime();
			java.sql.Date idate = new java.sql.Date(time);
			// data detail
			this.setStates(cupTxnUtil.getTmpStates(option, idate, page-1, rows));
			// page detail
			setRecords(cupTxnUtil.getSize(option));
			setTotal((records + rows - 1)/rows);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while retrieve data from statement",ex);
			setResult(ERROR);
			setMessage("0004");
			return ERROR;
		}
	}
	
	public String retreiveCupStates(){
		try{
			long time = date.getTime();
			java.sql.Date sdate = new java.sql.Date(time);
			this.setCupStates(cupTxnUtil.getStates(option, sdate, page-1, rows));
			this.setRecords(cupTxnUtil.getRows(option, sdate));
			this.setTotal((records+rows -1)/rows);
			this.setResult(SUCCESS);
			this.setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to get data from state production",ex);
			setResult(ERROR);
			setMessage("0004");
			return ERROR;
		}
	}
	public String retreiveCupSettles(){
		try{
			long time = date.getTime();
			java.sql.Date sdate = new java.sql.Date(time);
			this.setCupSettles(cupTxnUtil.getSettles(option, sdate, page-1, rows));
			this.setRecords(cupTxnUtil.getRows(option, sdate));
			this.setTotal((records+rows-1)/rows);
			this.setResult(SUCCESS);
			this.setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while retreive data from settle production",ex);
			setResult(ERROR);
			setMessage("0005");
			return ERROR;
		}
	}
	
	/* download data to report*/
	private final static String template="cupReportTemplate.jrxml";
	private InputStream fileInputStream;
	private String contentDisposition;
	private Long contentLength;
	
	private CupTxnReportExportion cupTxnReportExportion;
	private Parameter param;
	
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	public String getContentDisposition() {
		return contentDisposition;
	}
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	public Long getContentLength() {
		return contentLength;
	}
	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}
	public void setCupTxnReportExportion(CupTxnReportExportion cupTxnReportExportion){
		this.cupTxnReportExportion = cupTxnReportExportion;
	}
	public void setParam(Parameter param) {
		this.param = param;
	}
	public String downloadTmpState(){
		try{
			String dest = ServletActionContext.getServletContext().getRealPath("output/cupTxn");
			String templatePath = ServletActionContext.getServletContext().getRealPath("format_report");
			java.sql.Date sdate = new java.sql.Date(date.getTime());
			String fileName = "import_statements_on_"+sdate+"."+param.getFormat();
			templatePath+="/"+template;
			dest+="/"+fileName;
			
			// Retrieve data
			List<CupState> list = cupTxnUtil.getStates(option,sdate);
			if(list==null)
				throw new Exception("Exception occur while try to retrieve data");
			List<Report> cupReports = cupTxnUtil.toStateReports(list);
			
			// set Parameter
			param.setType(option);
			param.setImportDate(sdate);
			param.setDestPath(dest);
			param.setFormatPath(templatePath);
			
			// Write Down report
			cupTxnReportExportion.setParam(param);
			cupTxnReportExportion.setParamMap();
			cupTxnReportExportion.setReports(cupReports);
			
			boolean ret = cupTxnReportExportion.exportReport();
			
			if(!ret)
				throw new Exception("Exception occur while try to export file");
			
			setResult(SUCCESS);
			setMessage(fileName);
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to download file", ex);
			setResult(ERROR);
			setMessage("0001");
			return ERROR;
		}
	}
	
	public String downloadTmpSettle(){
		try{
			String dest = ServletActionContext.getServletContext().getRealPath("output/cupTxn");
			String templatePath = ServletActionContext.getServletContext().getRealPath("format_report");
			java.sql.Date sdate = new java.sql.Date(date.getTime());
			String fileName = "import_settlements_on_"+sdate+"."+param.getFormat();
			templatePath+="/"+template;
			dest+="/"+fileName;
			
			// Retrieve data
			List<CupSettle> list = cupTxnUtil.getSettles(option,sdate);
			if(list == null)
				throw new Exception("Exception occur while try to retrieve data");
			List<Report> cupReports = cupTxnUtil.toSettleReports(list);
			
			// set Parameter
			param.setType(option);
			param.setImportDate(sdate);
			param.setDestPath(dest);
			param.setFormatPath(templatePath);
			
			// Write Down report
			cupTxnReportExportion.setParam(param);
			cupTxnReportExportion.setParamMap();
			cupTxnReportExportion.setReports(cupReports);
			
			boolean ret = cupTxnReportExportion.exportReport();
			if(!ret)
				throw new Exception("Exception occur while try to export file");
			setResult(SUCCESS);
			setMessage(fileName);
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to download file", ex);
			setResult(ERROR);
			setMessage("0001");
			return SUCCESS;
		}
	}
}
