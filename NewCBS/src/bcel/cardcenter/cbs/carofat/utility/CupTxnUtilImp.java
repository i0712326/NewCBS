package bcel.cardcenter.cbs.carofat.utility;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import bcel.cardcenter.cbs.carofat.dao.CupReconcileDao;
import bcel.cardcenter.cbs.carofat.entity.CupSettle;
import bcel.cardcenter.cbs.carofat.entity.CupState;
import bcel.cardcenter.cbs.carofat.entity.CupTxnSettle;
import bcel.cardcenter.cbs.carofat.entity.CupTxnState;
import bcel.cardcenter.cbs.utility.report.CupReport;
import bcel.cardcenter.cbs.utility.report.Report;

import jxl.common.Logger;


public class CupTxnUtilImp implements CupTxnUtil {
	private static final Logger logger = Logger.getLogger(CupTxnUtilImp.class);
	private CupTxnStatementState cupTxnStatementState;
	private CupTxnSettlementState cupTxnSettlementState;
	
	private CupStatementState cupStatementState;
	private CupSettlementState cupSettlementState;

	public void setCupTxnStatementState(CupTxnStatementState cupTxnStatementState) {
		this.cupTxnStatementState = cupTxnStatementState;
	}
	public void setCupTxnSettlementState(CupTxnSettlementState cupTxnSettlementState) {
		this.cupTxnSettlementState = cupTxnSettlementState;
	}
	public CupTxnStateManager getCupTxnStateManager(String option){
		if(option.equals("settle"))
			return cupTxnSettlementState;
		else
			return cupTxnStatementState;
	}
	
	public void setCupStatementState(CupStatementState cupStatementState){
		this.cupStatementState = cupStatementState;
	}
	public void setCupSettlementState(CupSettlementState cupSettlementState){
		this.cupSettlementState = cupSettlementState;
	}
	public CupStateManager getCupStateManager(String option){
		if(option.equals("settle"))
			return cupSettlementState;
		else
			return cupStatementState;
	}
	
	@Override
	public void upload(int record, String option,Date date) throws Exception{
		try {
			getCupTxnStateManager(option).process(record,date);
		} catch (Exception e) {
			logger.debug("Exception occured while try to process data",e);
			throw new Exception("Process Error Could not process specific file.");
		}
	}
	@Override
	public void confirm(String option) throws Exception {
		try{
			getCupTxnStateManager(option).confirmUpload();
		}
		catch(Exception e){
			logger.debug("Exception occur while try to confirm update data");
			throw new Exception(e);
		}
	}
	@Override
	public List<CupTxnSettle> getTmpSettles(String option, Date date, int first, int max) throws Exception {
		try{
			return getCupTxnStateManager(option).getTxnSettles(date, first, max);
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to get data from settle",ex);
			throw new Exception(ex);
		}
	}
	@Override
	public List<CupTxnState> getTmpStates(String option, Date date, int first, int max) throws Exception {
		try{
			return getCupTxnStateManager(option).getTxnStates(date, first, max);
		}
		catch(Exception e){
			logger.debug("Exception occur while try to get date from state",e);
			throw new Exception(e);
		}
	}
	@Override
	public int getSize(String option) {
		return getCupTxnStateManager(option).getRows();
	}
	@Override
	public boolean deleteAll(String option, Date date){
		return getCupTxnStateManager(option).deleteAll(date);
	}
	
	// reconcile cup remote on us
	private CupReconcileDao cupReconcileDao;
	public void setCupReconcileDao(CupReconcileDao cupReconcileDao){
		this.cupReconcileDao = cupReconcileDao;
	}
	@Override
	public void reconcile(Date date) throws Exception{
		try{
			cupReconcileDao.reconcile(date);
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to reconcile cup", ex);
			throw new Exception(ex);
		}
	}
	@Override
	public void reconcileConfirm(Date date) throws Exception{
		try{
			cupReconcileDao.confirmReconcile(date);
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to confirm reconcile cup", ex);
			throw new Exception(ex);
		}
	}
	//
	@Override
	public List<CupSettle> getSettles(String option, Date date, int page, int max)
			throws Exception {
		return getCupStateManager(option).getSettles(date, page, max);
	}
	@Override
	public List<CupState> getStates(String option, Date date, int page, int max)
			throws Exception {
		return getCupStateManager(option).getStates(date, page, max);
	}
	@Override
	public int getRows(String option, Date date) {
		return getCupStateManager(option).getRows(date);
	}
	@Override
	public void cancel() throws Exception {
		cupReconcileDao.cancelReconcile();
	}
	@Override
	public List<CupSettle> getSettles(String option, int first, int max)
			throws Exception {
		return getCupStateManager(option).getSettles(first, max);
	}
	@Override
	public List<CupState> getStates(String option, int first, int max)
			throws Exception {
		return getCupStateManager(option).getStates(first,max);
	}
	@Override
	public int getRows(String option) {
		return getCupStateManager(option).getRows();
	}
	@Override
	public List<CupSettle> getSettles(String option, Date date, String card,
			String trace, int first, int max) {
		return getCupStateManager(option).getSettles(date, card, trace, first, max);
	}
	@Override
	public List<CupState> getStates(String option, Date date, String card,
			String trace, int first, int max) {
		return getCupStateManager(option).getStates(date, card, trace, first, max);
	}
	@Override
	public int getRows(String option, Date date, String card, String trace) {
		return getCupStateManager(option).getRows(date, card, trace);
	}
	@Override
	public List<CupTxnState> getTmpStates(String option, int page, int rows) {
		return getCupTxnStateManager(option).getTmpStates(page,rows);
	}
	@Override
	public List<CupTxnSettle> getTmpSettles(String option, int page, int rows) {
		return getCupTxnStateManager(option).getTmpSettles(page,rows);
	}
	@Override
	public boolean updateCup(String option, String xmlData) throws Exception {
		try{
			getCupStateManager(option).update(xmlData);
			return true;
		}
		catch(Exception e){
			throw new Exception(e);
		}
	}
	@Override
	public List<CupTxnState> getTmpStates(String option) throws Exception {
		return getCupTxnStateManager(option).getTmpStates();
	}
	@Override
	public List<CupTxnSettle> getTmpSettles(String option) throws Exception {
		return getCupTxnStateManager(option).getTmpSettles();
	}
	@Override
	public List<CupSettle> getSettles(String option, Date sdate) {
		return getCupStateManager(option).getSettles(sdate);
	}
	@Override
	public List<CupState> getStates(String option, Date sdate) {
		return getCupStateManager(option).getStates(sdate);
	}
	@Override
	public List<Report> toCupStateReports(List<CupTxnState> list) {
		List<Report> cupReports = new ArrayList<Report>();
		for(CupTxnState state : list){
			Date date = state.getDate();
			String card = state.getCard();
			String trace = state.getTrace();
			String time = state.getTime();
			double amount = state.getAmount();
			double fee = state.getFee();
			double net = state.getNet();
			String terminal = state.getTerminalId();
			String refer = state.getRefer();
			String type = state.getType();
			String status = state.getStatus();
			
			CupReport report = new CupReport();
			
			report.setDate(date);
			report.setCard(card);
			report.setTrace(trace);
			report.setTime(time);
			report.setAmount(amount);
			report.setFee(fee);
			report.setNet(net);
			report.setTerminal(terminal);
			report.setRefer(refer);
			report.setType(type);
			report.setStatus(status);
			cupReports.add(report);
		}
		
		return cupReports;
	}
	@Override
	public List<Report> toCupSettleReports(List<CupTxnSettle> list) {
		List<Report> cupReports = new ArrayList<Report>();
		for(CupTxnSettle state : list){
			Date date = state.getDate();
			String card = state.getCard();
			String trace = state.getTrace();
			String time = state.getTime();
			double amount = state.getAmount();
			double fee = state.getFee();
			double net = state.getNet();
			String terminal = state.getTerminalId();
			String refer = state.getRefer();
			String type = state.getType();
			String status = state.getStatus();
			
			CupReport report = new CupReport();
			
			report.setDate(date);
			report.setCard(card);
			report.setTrace(trace);
			report.setTime(time);
			report.setAmount(amount);
			report.setFee(fee);
			report.setNet(net);
			report.setTerminal(terminal);
			report.setRefer(refer);
			report.setType(type);
			report.setStatus(status);
			cupReports.add(report);
		}
		
		return cupReports;
	}
	@Override
	public List<CupSettle> getCupSettes(String option) throws Exception {
		return getCupStateManager(option).getSettles();
	}
	@Override
	public List<CupState> getCupStates(String option) throws Exception {
		return getCupStateManager(option).getStates();
	}
	@Override
	public List<Report> toStateReports(List<CupState> list) {
		List<Report> cupReports = new ArrayList<Report>();
		for(CupState state : list){
			Date date 		= state.getDate();
			String card 	= state.getCard();
			String trace 	= state.getTrace();
			String time 	= state.getTime();
			double amount 	= state.getAmount();
			double fee 		= state.getFee();
			double net 		= state.getNet();
			String terminal = state.getTerminalId();
			String refer 	= state.getRefer();
			String type 	= state.getType();
			String status 	= state.getStatus();
			
			CupReport report = new CupReport();
			
			report.setDate(date);
			report.setCard(card);
			report.setTrace(trace);
			report.setTime(time);
			report.setAmount(amount);
			report.setFee(fee);
			report.setNet(net);
			report.setTerminal(terminal);
			report.setRefer(refer);
			report.setType(type);
			report.setStatus(status);
			cupReports.add(report);
		}
		
		return cupReports;
	}
	@Override
	public List<Report> toSettleReports(List<CupSettle> list) {
		List<Report> cupReports = new ArrayList<Report>();
		for(CupSettle state : list){
			Date date 		= state.getDate();
			String card 	= state.getCard();
			String trace 	= state.getTrace();
			String time 	= state.getTime();
			double amount 	= state.getAmount();
			double fee 		= state.getFee();
			double net 		= state.getNet();
			String terminal = state.getTerminalId();
			String refer 	= state.getRefer();
			String type 	= state.getType();
			String status 	= state.getStatus();
			
			CupReport report = new CupReport();
			
			report.setDate(date);
			report.setCard(card);
			report.setTrace(trace);
			report.setTime(time);
			report.setAmount(amount);
			report.setFee(fee);
			report.setNet(net);
			report.setTerminal(terminal);
			report.setRefer(refer);
			report.setType(type);
			report.setStatus(status);
			cupReports.add(report);
		}
		
		return cupReports;
	}
	
}
