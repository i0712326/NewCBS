package bcel.cardcenter.cbs.carofat.action.reconcile;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import jxl.common.Logger;

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

public class CarofatReconcileAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CarofatReconcileAction.class);
	private Date date;
	private CupTxnUtil cupTxnUtil;
	// output data
	private List<CupTxnSettle> settles;
	private List<CupTxnState> states;
	
	private List<CupSettle> cupSettles;
	private List<CupState> cupStates;
	// data table parameter
	private int rows;
	private int page;
	private int total;
	private int records;
	
	// result and message
	private String result;
	private String message;
	
	public void setCupTxnUtil(CupTxnUtil cupTxnUtil){
		this.cupTxnUtil = cupTxnUtil;
	}
	public void setDate(Date date){
		this.date = date;
	}
	public Date getDate(){
		return date;
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
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
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
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	// reconcile
	public String reconcile() throws Exception {
		try{
			long time = date.getTime();
			java.sql.Date sdate = new java.sql.Date(time);
			cupTxnUtil.reconcile(sdate);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception while try to reconcile action", ex);
			setResult(ERROR);
			setMessage("0001");
			return ERROR;
		}
	}
	// get temporary statement
	public String getTempState(){
		String option = "state";
		try{
			this.setStates(this.cupTxnUtil.getTmpStates(option, page-1, rows));
			this.setRecords(cupTxnUtil.getSize(option));
			this.setTotal((records + rows -1)/rows);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while get temporary data", ex);
			setResult(ERROR);
			setMessage("0004");
			return ERROR;
		}
	}
	// get temporary settlement
	public String getTempSettle(){
		String option = "settle";
		try{
			this.setSettles(cupTxnUtil.getTmpSettles(option, page-1, rows));
			this.setRecords(cupTxnUtil.getSize(option));
			this.setTotal((records+rows-1)/rows);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to get temporary data",ex);
			setResult(ERROR);
			setMessage("0005");
			return ERROR;
		}
	}
	// confirm reconcile
	public String confirm() throws Exception{
		try{
			long time = date.getTime();
			java.sql.Date sdate = new java.sql.Date(time);
			cupTxnUtil.reconcileConfirm(sdate);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while confirm reconcile action",ex);
			setResult(ERROR);
			setMessage("0001");
			return ERROR;
		}
	}
	// get production settlement
	public String getReconSettles(){
		String option = "settle";
		try{
			this.setCupSettles(cupTxnUtil.getSettles(option, page-1, rows));
			this.setRecords(cupTxnUtil.getRows(option));
			this.setTotal((records+rows-1)/rows);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("",ex);
			setResult(SUCCESS);
			setMessage("0003");
			return ERROR;
		}
	}
	// get production statement
	public String getReconStates(){
		String option ="state";
		try{
			this.setCupStates(cupTxnUtil.getStates(option, page-1, rows));
			this.setRecords(cupTxnUtil.getRows(option));
			this.setTotal((records+rows-1)/rows);
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occurwhile try to fetch state production",ex);
			setResult(ERROR);
			setMessage("0005");
			return ERROR;
		}
	}
	public String cancel(){
		try{
			cupTxnUtil.cancel();
			setResult(SUCCESS);
			setMessage("0000");
			return SUCCESS;
		}
		catch(Exception ex){
			logger.debug("Exception occur while cancel reconcile action", ex);
			setResult(ERROR);
			setMessage("0002");
			return ERROR;
		}
	}
	
	// download file action
	
	private final static String template="cupReportTemplate.jrxml";
	private String option;
	private InputStream fileInputStream;
	private String contentDisposition;
	private Long contentLength;
	
	private CupTxnReportExportion cupTxnReportExportion;
	private Parameter param;
	
	public String getOption(){
		return option;
	}
	public void setOption(String option){
		this.option = option;
	}
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
	public Parameter getParam() {
		return param;
	}
	public void setParam(Parameter param) {
		this.param = param;
	}
	public void setCupTxnReportExportion(CupTxnReportExportion cupTxnReportExportion) {
		this.cupTxnReportExportion = cupTxnReportExportion;
	}
	
	public String downloadTmpState(){
		try{
			String dest = ServletActionContext.getServletContext().getRealPath("output/cupTxn");
			String templatePath = ServletActionContext.getServletContext().getRealPath("format_report");
			java.sql.Date sdate = new java.sql.Date(date.getTime());
			String fileName = "statemens_on_"+sdate+"."+param.getFormat();
			templatePath+="/"+template;
			dest+="/"+fileName;
			
			// Retrieve data
			List<CupState> list = cupTxnUtil.getCupStates(option);
			if(list==null)
				throw new Exception("Retrieve record is null");
			
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
			String fileName = "settlements_on_"+sdate+"."+param.getFormat();
			templatePath+="/"+template;
			dest+="/"+fileName;
			
			// Retrieve data
			List<CupSettle> list = cupTxnUtil.getCupSettes(option);
			if(list==null)
				throw new Exception("Exception occur while try to retrieve date");
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
				throw new Exception("Exception occur while try to export report");
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
	
}
