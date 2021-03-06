package bcel.cardcenter.cbs.carofat.dao;

import java.util.List;
import java.sql.Date;
import bcel.cardcenter.cbs.carofat.entity.CupTxnState;

public interface CupTxnStateDao {
	public void saveOrUpdateAll(List<CupTxnState> cups);
	public List<CupTxnState> getCupTxnStates(Date date, String card, String trace);
	public boolean confirmCupTxnStateBatch();
	public List<CupTxnState> getTxnStates(Date date, int first, int max);
	public int getRows();
	public boolean deleteAll(Date date);
	public List<CupTxnState> getTmpStates();
	public List<CupTxnState> getTmpStates(int page, int rows);
}
