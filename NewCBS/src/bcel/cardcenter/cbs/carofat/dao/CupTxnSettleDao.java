package bcel.cardcenter.cbs.carofat.dao;

import java.sql.Date;
import java.util.List;

import bcel.cardcenter.cbs.carofat.entity.CupTxnSettle;

public interface CupTxnSettleDao {
	public void saveOrUpdateAll(List<CupTxnSettle> cupTxns);
	public List<CupTxnSettle> getCupTxnSettles(Date date, String card, String trace);
	public boolean confirmCupTxnSettleBatch();
	public List<CupTxnSettle> getTxnSettles(Date date, int first, int max);
	public int getRows();
	
	public boolean deleteAll(Date date);
	public List<CupTxnSettle> getTmpSettles();
	public List<CupTxnSettle> getTmpSettles(int page, int rows);
	
}
