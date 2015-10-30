package bcel.cardcenter.cbs.carofat.utility;

import java.sql.Date;
import java.util.List;

import bcel.cardcenter.cbs.carofat.entity.CupSettle;
import bcel.cardcenter.cbs.carofat.entity.CupState;
import bcel.cardcenter.cbs.carofat.entity.CupTxnSettle;
import bcel.cardcenter.cbs.carofat.entity.CupTxnState;
import bcel.cardcenter.cbs.utility.report.Report;

public interface CupTxnUtil {
	/* Import */
	public void upload(int record, String option, Date date) throws Exception;
	public void confirm(String option) throws Exception;
	public List<CupTxnSettle> getTmpSettles(String option, Date date, int first, int max) throws Exception;
	public List<CupTxnState> getTmpStates(String option, Date date, int first, int max) throws Exception;
	public int getSize(String option);
	public boolean deleteAll(String option, Date date);
	
	public List<CupSettle> getSettles(String option, Date date, int first, int max) throws Exception;
	public List<CupState> getStates(String option,Date date, int first, int max) throws Exception;
	public List<CupSettle> getSettles(String option, int first, int max) throws Exception;
	public List<CupState> getStates(String option, int first, int max) throws Exception;
	
	public int getRows(String option, Date date);
	public int getRows(String option);
	
	/* Reconcile */
	
	public void reconcile(Date date) throws Exception;
	public void reconcileConfirm(Date date) throws Exception;
	public void cancel() throws Exception;
	public List<CupSettle> getCupSettes(String option) throws Exception;
	public List<CupState> getCupStates(String option) throws Exception;
	
	/* adjustment + history */
	public List<CupSettle> getSettles(String option, Date date, String card, String trace, int first, int max);
	public List<CupState> getStates(String option, Date date, String card, String trace, int first, int max);
	public int getRows(String option, Date date, String card, String trace);
	
	/* history */
	public boolean updateCup(String option, String xmlData) throws Exception;
	public List<CupTxnState> getTmpStates(String option) throws Exception;
	public List<CupTxnSettle> getTmpSettles(String option) throws Exception;
	// to report list
	public List<Report> toCupStateReports(List<CupTxnState> list);
	public List<Report> toCupSettleReports(List<CupTxnSettle> list);
	public List<Report> toStateReports(List<CupState> list);
	public List<Report> toSettleReports(List<CupSettle> list);
	
	public List<CupSettle> getSettles(String option, java.sql.Date sdate);
	public List<CupState> getStates(String option, java.sql.Date sdate);
	
	public List<CupTxnState> getTmpStates(String option, int page, int rows);
	public List<CupTxnSettle> getTmpSettles(String option, int page, int rows);
	
}
