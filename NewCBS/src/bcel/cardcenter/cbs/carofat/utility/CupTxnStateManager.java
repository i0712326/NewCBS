package bcel.cardcenter.cbs.carofat.utility;
import java.sql.Date;
import java.util.List;

import bcel.cardcenter.cbs.carofat.entity.CupTxnSettle;
import bcel.cardcenter.cbs.carofat.entity.CupTxnState;

public interface CupTxnStateManager {
	public String process(int record, Date date) throws Exception;
	public boolean confirmUpload() throws Exception;
	public List<CupTxnState> getTxnStates(Date date, int first, int max);
	public List<CupTxnSettle> getTxnSettles(Date date, int first, int max);
	public int getRows();
	public boolean deleteAll(Date date);
	public List<CupTxnState> getTmpStates();
	public List<CupTxnSettle> getTmpSettles();
	
	public List<CupTxnState> getTmpStates(int page, int rows);
	public List<CupTxnSettle> getTmpSettles(int page, int rows);
}
